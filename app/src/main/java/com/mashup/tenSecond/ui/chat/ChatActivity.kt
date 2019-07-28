package com.mashup.tenSecond.ui.chat

import android.animation.ValueAnimator
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.tenSecond.R
import com.mashup.tenSecond.ViewModelFactory
import com.mashup.tenSecond.data.model.Messages
import com.mashup.tenSecond.databinding.ActivityChatBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.chat.adapter.ChatAdapter
import com.mashup.tenSecond.util.Constant
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.getCurrentDate
import com.namget.diaryLee.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.android.ext.android.inject
import java.io.IOException
import java.util.*

class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override fun onLayoutId(): Int = R.layout.activity_chat
    val chatList: MutableList<Messages.Message> = arrayListOf()
    lateinit var chatAdapter: ChatAdapter
    lateinit var chatRoomViewModel: ChatRoomViewModel
    val viewModelFactory: ViewModelFactory by inject()

    var player: MediaPlayer? = null
    var recorder: MediaRecorder? = null
    lateinit var fileName: String

    val TAG = "ChatActivity"

    var startPlayingFlag = true
    var startRecordingFlag = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRecyclerView()
        initViewModel()
        initRecorder()
    }

    private fun getIntentData(): Int {
        var id = 0
        if (intent.hasExtra(Constant.CHAT_ROOM_ID)) {
            id = intent.getIntExtra(Constant.CHAT_ROOM_ID, 0)
        }
        return id
    }

    private fun setRecyclerView() {
        chatAdapter  = ChatAdapter(chatList)
        chatRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.itemAnimator = DefaultItemAnimator()
            this.addItemDecoration(SimpleDividerItemDecoration(context))
            this.adapter = chatAdapter
        }
    }

    private fun initViewModel() {
        chatRoomViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ChatRoomViewModel::class.java)
        val id = getIntentData()
        observeData()
        chatRoomViewModel.getChatRoom(id)
    }

    private fun observeData() {
        chatRoomViewModel.chatMessage.observe(this, androidx.lifecycle.Observer {
            chatAdapter.addList(it)
        })
    }

    private fun initRecorder() {
        recordBtn.setOnClickListener {
            changeRecordState()
        }


        animationView.setOnClickListener {
            changeRecordState()
        }
        animationView.repeatCount = ValueAnimator.INFINITE
        animationView.playAnimation()

    }

    private fun changeRecordState() {
        onRecord(startRecordingFlag)
        if (startPlayingFlag) {
            bottomLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.mintColorLight))
        } else {
            bottomLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
        }
        startRecordingFlag = !startRecordingFlag
    }


    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }

    private fun startRecording() {
        fileName = "${getFilesDir().getAbsolutePath()}/${Calendar.getInstance().getCurrentDate()}.mp4"
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)

            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                LogUtil.e(TAG, "prepare() failed", e)
            }

            start()
            recordBtn.visibility = View.INVISIBLE
            animationView.visibility = View.VISIBLE
        }

    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
        recordBtn.visibility = View.VISIBLE
        animationView.visibility = View.INVISIBLE
    }

    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }


}