package com.jonesyong.servicedemo

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jonesyong.servicedemo.databinding.ActivityMessengerBinding


class MessengerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MessengerActivity"
    }

    private lateinit var bindService: Button
    private lateinit var unbindService: Button
    private lateinit var communicationRemote: Button

    private lateinit var binding: ActivityMessengerBinding
    private lateinit var messenger: Messenger
    private var bond = false
    private var replyMessenger: Messenger = Messenger(ReceiverReplyMsgHandler())

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "Service onServiceConnected")
            messenger = Messenger(service)
            bond = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "Service onServiceDisconnected")
            bond = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessengerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initEvent()
    }

    private fun initView() {
        bindService = binding.bindRemoteService
        unbindService = binding.unbindRemoteService
        communicationRemote = binding.communicateRemote
    }

    private fun sayHelloToService() {
        if (!this.bond) {
            return
        }
        val message = Message.obtain()
        message.replyTo = replyMessenger
        message.what = MessengerService.msgSayHello
        messenger.send(message)
    }

    private fun initEvent() {
        bindService.setOnClickListener {
            bindService(
                Intent(this, MessengerService::class.java),
                serviceConnection,
                Service.BIND_AUTO_CREATE
            )
        }
        unbindService.setOnClickListener {
            unbindService(serviceConnection)
        }
        communicationRemote.setOnClickListener {
            sayHelloToService()
        }
    }

    inner class ReceiverReplyMsgHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MessengerService.msgSayHello -> Log.d(
                    TAG,
                    "receiver message from service:" + msg.data.getString("reply")
                );
            }
        }

    }

}