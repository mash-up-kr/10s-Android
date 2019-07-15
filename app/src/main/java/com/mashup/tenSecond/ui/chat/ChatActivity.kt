package com.mashup.tenSecond.ui.chat

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.ChatContent
import com.mashup.tenSecond.databinding.ActivityChatBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.chat.adapter.ChatAdapter
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.getCurrentDate
import com.namget.diaryLee.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.android.ext.android.inject
import java.io.IOException
import java.util.*

class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override fun onLayoutId(): Int = R.layout.activity_chat
    val chatList: MutableList<ChatContent> = arrayListOf()
    val chatAdapter: ChatAdapter = ChatAdapter(chatList)
    lateinit var chatRoomViewModel: ChatRoomViewModel
    val chatRoomViewModelFactory: ChatRoomViewModelFactory by inject()

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

    private fun setRecyclerView() {
        chatRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.itemAnimator = DefaultItemAnimator()
            this.addItemDecoration(SimpleDividerItemDecoration(context))
            this.adapter = chatAdapter
        }

    }

    fun initViewModel() {
        chatRoomViewModel =
            ViewModelProviders.of(this, chatRoomViewModelFactory).get(ChatRoomViewModel::class.java)
    }

    fun initRecorder() {
        recordBtn.setOnClickListener {
            changeRecordState()
        }

        playBtn.setOnClickListener {
            changePlayState()
        }


        animationView.setOnClickListener {
            changeRecordState()
        }



        animationView.playAnimation()
        animationView.loop(true)
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
    private fun changePlayState(){
        onPlay(startPlayingFlag)
        if (startPlayingFlag) {
            playBtn.setImageResource(R.drawable.ic_pause_white_24dp)
        } else {
            playBtn.setImageResource(R.drawable.ic_play_white_24dp)
        }
        startPlayingFlag = !startPlayingFlag
    }


    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }

    private fun onPlay(start: Boolean) = if (start) {
        startPlaying()
    } else {
        stopPlaying()
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
                this.setOnCompletionListener(MediaPlayer.OnCompletionListener {
                    changePlayState()
                })
            } catch (e: IOException) {
                LogUtil.e(TAG, "prepare() failed", e)
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
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
        recordBtn.visibility = View.INVISIBLE
        animationView.visibility = View.INVISIBLE
        playBtn.visibility = View.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }


}