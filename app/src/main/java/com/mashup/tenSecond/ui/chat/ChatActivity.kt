package com.mashup.tenSecond.ui.chat

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.ChatContent
import com.mashup.tenSecond.databinding.ActivityChatBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.chat.adapter.ChatAdapter
import com.mashup.tenSecond.util.LogUtil
import com.namget.diaryLee.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.android.ext.android.inject
import java.io.IOException

class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override fun onLayoutId(): Int = R.layout.activity_chat
    val chatList: MutableList<ChatContent> = arrayListOf()
    val chatAdapter: ChatAdapter = ChatAdapter(chatList)
    lateinit var chatRoomViewModel: ChatRoomViewModel
    val chatRoomViewModelFactory: ChatRoomViewModelFactory by inject()

    var player: MediaPlayer? = null
    var recorder: MediaRecorder? = null
    private var fileName: String = "${filesDir.name}/"


    val TAG = "ChatActivity"

    var startPlayingFlag = true
    var startRecordingFlag = true


    val diffCallback = object : DiffUtil.ItemCallback<ChatContent>() {
        override fun areItemsTheSame(oldItem: ChatContent, newItem: ChatContent): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ChatContent, newItem: ChatContent): Boolean =
            oldItem.name == newItem.name
    }


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
            onRecord(startPlayingFlag)
        }
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
            //lottie visible
            //record invisible
            //animateStart()
        }

    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null

        //lottie invisible
        //record visible
        //animateStop()
    }

    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }


}