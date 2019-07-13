package com.mashup.tenSecond.ui.friend

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.Friend
import com.mashup.tenSecond.data.model.UserInstance
import com.mashup.tenSecond.databinding.FragmentFriendlistBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.friend.adapter.FriendListAdapter
import com.mashup.tenSecond.ui.setting.SettingActivity
import com.mashup.tenSecond.util.*
import com.namget.diaryLee.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_friendlist.*
import org.koin.android.ext.android.inject


class FriendListFragment : BaseFragment<FragmentFriendlistBinding>() {

    val TAG = "FriendListFragment"
    override fun onLayoutId(): Int = R.layout.fragment_friendlist
    val friendListViewModelFactory: FriendListViewModelFactory by inject()
    lateinit var friendListViewModel: FriendListViewModel


    val diffCallback = object : DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean =
            oldItem.id == newItem.id
    }

    private val friendListAdapter: FriendListAdapter by lazy {
        FriendListAdapter(diffCallback)
    }
    private lateinit var preferences: SharedPreferences
    private lateinit var liveSharedPreferences: LiveSharedPreferences


    companion object {
        lateinit var friendListFragment: FriendListFragment

        fun newInstance(): FriendListFragment {
            synchronized(FriendListFragment::class) {
                friendListFragment = FriendListFragment()
                val args = Bundle()
                friendListFragment.arguments = args
                return friendListFragment
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecyclerView()
        init()
        initViewModel()
    }

    private fun setRecyclerView() {
        friendListRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.itemAnimator = DefaultItemAnimator()
            this.addItemDecoration(SimpleDividerItemDecoration(context))
            this.adapter = friendListAdapter
        }
    }

    private fun init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        liveSharedPreferences = LiveSharedPreferences(preferences)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initViewModel() {
        friendListViewModel =
            ViewModelProviders.of(this, friendListViewModelFactory).get(FriendListViewModel::class.java)
        friendListViewModel.friendList.observe(this, Observer {
            friendListAdapter.submitList(it)
        })
        binding.fragment = this

        val user = UserInstance.loadUserProfile(context!!)

        liveSharedPreferences.getString(Constant.USER_NAME, user.userName).observe(this, Observer<String> {
            userName.setText(it)
            LogUtil.e(TAG, "name LiveData : $it")
        })

        liveSharedPreferences.getString(Constant.USER_STATUS, user.status).observe(this, Observer<String> {
            userStatus.setText(it)
            LogUtil.e(TAG, "status LiveData : $it")
        })

        liveSharedPreferences.getString(Constant.USER_IMAGE_URL, user.PhotoUrl).observe(this, Observer<String> {
            profileImage.setImageWithGlide(it)
            LogUtil.e(TAG, "imgeUrl LiveData : $it")
        })

        friendListViewModel.listSize.observe(this, Observer {
            friendTitleContent.setText("($it)")
        })

        friendListViewModel.getFriendList()


    }

    fun startSettingActivity(view: View) {
        val intent = Intent(context, SettingActivity::class.java)
        startActivity(intent)
    }

    fun addFriend(view: View) {
        LogUtil.e(TAG, "친구추가 다이얼로그")
        val inputText = EditText(context)
        val builder = AlertDialog.Builder(context)
            .setTitle(resources.getString(com.mashup.tenSecond.R.string.friendAdd))
            .setView(inputText)
            .setNegativeButton(
                resources.getString(com.mashup.tenSecond.R.string.cancel)
            ) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(
                resources.getString(com.mashup.tenSecond.R.string.add)
            ) { dialog, which ->
                val input = inputText.text.toString()
                if (friendListViewModel.isValidEmail(input)) {
                    friendListViewModel.addFriendList(input)
                    dialog.dismiss()
                } else {
                    context?.toastMakeToast("유효한 이메일이 아닙니다.")
                }
            }
        val dialog = builder.create()
        dialog.show()

        val positiveButton: Button = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        inputText.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    positiveButton.isEnabled = friendListViewModel.isValidEmail(s ?: "")
                }
            }
        )
    }


}