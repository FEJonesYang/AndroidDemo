package com.jonesyong.servicedemo

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * @Author JonesYang
 * @Date 2022-01-22
 * @Description 本地服务，通过扩展 Binder 类进行进程间的通信
 */
class LocalService : Service() {

    companion object {
        private const val TAG = "LocalService"
    }

    private val binder = LocalBinder()
    private var count: Int = 0
    private var quit: Boolean = false

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "Service onBind")
        return binder // 绑定服务会返回该对象
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service onCreate")
        Thread {
            while (!quit) {
                Thread.sleep(1000)
                count++
            }
        }.start()
    }

    fun getCount(): Int {
        return count
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        Log.d(TAG, "Service unbindService")
    }

    override fun onDestroy() {
        super.onDestroy()
        this.quit = true
        Log.d(TAG, "Service onDestroy")
    }

    inner class LocalBinder : Binder() {
        // 提供一个获取当前服务对象的方法，以便和服务端的进行通信
        fun getService(): LocalService {
            return this@LocalService
        }
    }
}