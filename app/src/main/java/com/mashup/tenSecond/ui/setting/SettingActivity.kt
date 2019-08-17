package com.mashup.tenSecond.ui.setting

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toFile
import androidx.lifecycle.ViewModelProviders
import com.mashup.tenSecond.R
import com.mashup.tenSecond.ViewModelFactory
import com.mashup.tenSecond.databinding.ActivitySettingBinding
import com.mashup.tenSecond.ui.base.setGlideImage
import com.namget.diaryLee.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*
import org.koin.android.ext.android.inject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    private val GALLERY = 1
    val viewModelFactory: ViewModelFactory by inject()
    lateinit var settingViewModel: SettingViewModel
    override fun onLayoutId(): Int = R.layout.activity_setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewModel()
    }

    fun init() {
        idChangeButton.setOnClickListener {
            idText.isEnabled = true
            idChangeButton.setVisibility(View.INVISIBLE)
            stateText.isEnabled = false
            stateChangeButton.setVisibility(View.VISIBLE)
        }
        stateChangeButton.setOnClickListener {
            idText.isEnabled = false
            idChangeButton.setVisibility(View.VISIBLE)
            stateText.isEnabled = true
            stateChangeButton.setVisibility(View.INVISIBLE)
        }
        getProfileImageBtn.setOnClickListener {
            showGallary()
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    fun initViewModel() {
        settingViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingViewModel::class.java)
        binding.viewmodel = settingViewModel
        settingViewModel.getProfile()

        settingViewModel.profile.observe(this, androidx.lifecycle.Observer {
            profileImage.setGlideImage(it.profileImage)
        })

    }


    //프로필 이미지
    fun showGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                binding.profileImage.setGlideImage(data.data!!.also {
                    settingViewModel.changeProfile(it)
                })
                try {
//                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
//                    val path = saveImage(bitmap)
//                    Toast.makeText(this@SettingActivity, "!", Toast.LENGTH_SHORT).show()
                } catch (e: IOException) {
                    e.printStackTrace()
//                    Toast.makeText(this@SettingActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    companion object {
        private val IMAGE_DIRECTORY = "/demonuts"
    }

}