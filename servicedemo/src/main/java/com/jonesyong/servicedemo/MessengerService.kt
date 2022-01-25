package com.jonesyong.servicedemo

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log


/**
 * @Author JonesYang
 * @Date 2022-01-22
 * @Description 使用 Messenger 进行跨进程通信
 */
class MessengerService : Service() {

    companion object {
        private const val TAG = "MessengerService"
        const val msgSayHello = 1
    }

    private val mMessenger: Messenger = Messenger(MessengerHandler())

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "Service onBind")
        return mMessenger.binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service onCreate")
    }

    inner class MessengerHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                msgSayHello -> {
                    Log.d(TAG, "Thanks,Service had receiver message from client!")
                    val messenger: Messenger = msg.replyTo
                    val bundle = Bundle()
                    bundle.putString("reply", "ok~,I had receiver message from you! ")
                    val message = Message.obtain()
                    message.what = msgSayHello
                    message.data = bundle
                    messenger.send(message)
                }
                else -> {
                    super.handleMessage(msg)
                }
            }
        }
    }


}