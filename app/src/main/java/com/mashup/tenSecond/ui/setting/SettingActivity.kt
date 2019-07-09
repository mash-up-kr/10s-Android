package com.mashup.tenSecond.ui.setting

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.mashup.tenSecond.R
import com.mashup.tenSecond.databinding.ActivitySettingBinding
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
    val settingViewModelFactory: SettingViewModelFactory by inject()
    lateinit var settingViewModel: SettingViewModel
    override fun onLayoutId(): Int = R.layout.activity_setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewModel()
    }

    fun init() {
        idChangeButton.setOnClickListener {
            binding.idText.setEnabled(true)
            binding.idChangeButton.setVisibility(View.INVISIBLE)
            binding.stateText.setEnabled(false)
            binding.stateChangeButton.setVisibility(View.VISIBLE)
        }
        stateChangeButton.setOnClickListener {
            binding.idText.setEnabled(false)
            binding.idChangeButton.setVisibility(View.VISIBLE)
            binding.stateText.setEnabled(true)
            binding.stateChangeButton.setVisibility(View.INVISIBLE)
        }
        getProfileImageBtn.setOnClickListener {
            showGallary()
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    fun initViewModel() {
        settingViewModel = ViewModelProviders.of(this, settingViewModelFactory).get(SettingViewModel::class.java)
    }


    fun save() {
        //ID 공백일 경우 저장x
        if (binding.idText.toString().isEmpty())
            Toast.makeText(this@SettingActivity, "id를 입력해주세요", Toast.LENGTH_SHORT).show()
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
                val contentURI = data!!.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    Toast.makeText(this@SettingActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
                    binding.profileImage!!.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@SettingActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File((Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee", wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }
        try {
            Log.d("heel", wallpaperDirectory.toString())
            val f = File(
                wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg")
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null
            )
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())
            return f.getAbsolutePath()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/demonuts"
    }

}