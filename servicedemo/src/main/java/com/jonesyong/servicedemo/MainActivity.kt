package com.jonesyong.servicedemo

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Messenger
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.jonesyong.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LocalService"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var localService: LocalService
    private lateinit var startService: Button
    private lateinit var stopService: Button
    private lateinit var bindService: Button
    private lateinit var unBindService: Button
    private lateinit var getBindData: Button
    private lateinit var showTextView: TextView
    private lateinit var messengerButton: Button

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "Service onServiceConnected")
            val binder: LocalService.LocalBinder = service as LocalService.LocalBinder
            localService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "Service onServiceDisconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initEvent()
    }

    private fun initEvent() {
        startService.setOnClickListener {
            val intent = Intent(this, LocalService::class.java)
            startService(intent)
        }
        stopService.setOnClickListener {
            val intent = Intent("com.jonesyong.servicedemo.LocalService")
            intent.setPackage(packageName)
            stopService(intent)
        }
        bindService.setOnClickListener {
            val intent = Intent("com.jonesyong.servicedemo.LocalService")
            intent.setPackage(packageName)
            bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
        }
        unBindService.setOnClickListener {
            unbindService(serviceConnection)
        }

        // 获取数据、展示数据
        getBindData.setOnClickListener {
            showTextView.text = localService.getCount().toString()
            Log.d(TAG, localService.getCount().toString())
        }

        // 进入启动远程服务的界面
        messengerButton.setOnClickListener {
            startActivity(Intent(this, MessengerActivity::class.java))
        }
    }

    private fun initView() {
        startService = binding.startService
        stopService = binding.stopService
        bindService = binding.bindService
        unBindService = binding.unbindService
        getBindData = binding.obtainText
        showTextView = binding.showText
        messengerButton = binding.remoteMessenger
    }
}
