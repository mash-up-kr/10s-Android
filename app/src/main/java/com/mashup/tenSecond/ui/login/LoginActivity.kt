package com.mashup.tenSecond.ui.login

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.iid.FirebaseInstanceId
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.UserInstance
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.databinding.ActivityLoginBinding
import com.mashup.tenSecond.ui.main.MainActivity
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.toastMakeToast
import com.namget.diaryLee.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject


class LoginActivity : BaseActivity<ActivityLoginBinding>(), GoogleApiClient.OnConnectionFailedListener {

    override fun onLayoutId(): Int = R.layout.activity_login
    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN = 100
    val repository: Repository by inject()

    lateinit var mAuth: FirebaseAuth
    lateinit var mGoogleApiClient: GoogleApiClient
    var currentUser: FirebaseUser? = null
    private var userEmail = ""
    private var userIdToken = ""
    private var userPhotoUrl = ""
    private var userName = ""
    private var authType = ""
    private val TAG = "LoginActivity"
    private val splashHandler = Handler()
    lateinit var splashRunnable: Runnable
    private var isLoginSuccess = false
    private var isSplashShowing = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
        init()
        hideSplash()
    }

    private fun hideSplash() {
        splashRunnable = Runnable {
            isSplashShowing = false
            if (UserInstance.loadUserToken(this).isEmpty()) {
                loginLayout.visibility = View.VISIBLE
                splashLayout.visibility = View.GONE
            }
            if (isLoginSuccess) {
                startMainActivity()
            } else {
                loginLayout.visibility = View.VISIBLE
                splashLayout.visibility = View.GONE
            }
        }
        splashHandler.postDelayed(splashRunnable, 2000)
    }

    private fun checkPermission() {
        val permissionlistener = object : PermissionListener {
            override fun onPermissionGranted() {
                getFCMId()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                finish()
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("권한을 거부하시면 서비스 이용이 불가합니다. 세팅 > 권한 에서 켜주세요")
            .setPermissions(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .check()
    }

    private fun getFCMId() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token
                LogUtil.e("LoginActivity", "token : ${task.result}")
                googleLogin()
            })
    }

    private fun init() {
        googleLogin.setOnClickListener {
            signIn()
        }
    }

    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkUser(): Boolean {
        val user = UserInstance.loadUserProfile(this)
        return user.userName.isEmpty() &&
                user.userEmail.isEmpty() &&
                user.PhotoUrl.isEmpty()
    }

    private fun updateUI(firebaseUser: FirebaseUser?) {
        if (firebaseUser == null) {
            return
        }
        UserInstance.loadUserProfile(this)
        //임시
        if (UserInstance.loadUserToken(this).isEmpty()) {
            addDisposable(repository.joinUser(userEmail, userName, authType, userPhotoUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        UserInstance.saveUserToken(this, it.accessToken)
                        isLoginSuccess = true
                        startMainActivity()
                    },
                    {
                        LogUtil.e("TAG,", "서버 요청 실패 ${it}")
                        this.toastMakeToast("서버 요청 실패")
                    }
                )
            )
        } else {
            //auth 인증
            addDisposable(repository.getUserAuthentication()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        UserInstance.saveUserToken(this, it.accessToken)
                        isLoginSuccess = true
                        if (!isSplashShowing)
                            startMainActivity()
                    },
                    {
                        LogUtil.e("TAG,", "서버 요청 실패 ${it}")
                        this.toastMakeToast("서버 요청 실패")
                    }
                )
            )
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        LogUtil.e(TAG, "firebaseAuthWithGoogle userName:" + acct.displayName)
        LogUtil.e(TAG, "firebaseAuthWithGoogle userEmail:" + acct.email)
        LogUtil.e(TAG, "firebaseAuthWithGoogle userPhotoUrl:" + acct.photoUrl)

        authType = "google"
        userName = acct.displayName!!
        userEmail = acct.email!!
        userIdToken = acct.idToken!!
        userPhotoUrl = acct.photoUrl!!.toString()
        UserInstance.saveUserProfile(this, userName, userEmail, "", userPhotoUrl)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    LogUtil.e(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user!!)
                } else {
                    LogUtil.e(TAG, "signInWithCredential:fail")
                    this.toastMakeToast("구글 로그인 연결 실패")
                    // If sign in fails, display a message to the user.
                    updateUI(null)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        splashHandler.removeCallbacks(splashRunnable)
    }

    private fun googleLogin() {
        auth = FirebaseAuth.getInstance()
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
        mAuth = FirebaseAuth.getInstance()

        //자동 로그인
        currentUser = mAuth.getCurrentUser()
        if (currentUser != null) {
            updateUI(currentUser)
        }

    }

    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {

                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account!!)
                    LogUtil.e(TAG, "Google sign in Success")
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    LogUtil.e(TAG, "Google sign in failed", e)
                }
            } else if (resultCode == RESULT_CANCELED) {
                LogUtil.e(TAG, "Google sign CALCELED")
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        this.toastMakeToast("구글 로그인 연결 실패")
    }
}