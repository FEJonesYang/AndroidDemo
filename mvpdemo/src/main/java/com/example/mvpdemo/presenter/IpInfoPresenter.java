package com.example.mvpdemo.presenter;

import android.content.pm.ActivityInfo;
import android.view.Display;
import android.view.Window;

import com.example.mvpdemo.interfaces.IpInfoContract;
import com.example.mvpdemo.interfaces.LoadTaskCallback;
import com.example.mvpdemo.interfaces.NetTask;
import com.example.mvpdemo.mode.IpInfo;

/**
 * @author JonesYang
 * @Data 2020-11-23
 * @Function
 */
public class IpInfoPresenter implements LoadTaskCallback<IpInfo>, IpInfoContract.Presenter {

    private NetTask mNetTask;
    private IpInfoContract.View addTaskView;

    public IpInfoPresenter(IpInfoContract.View addTaskView, NetTask netTask) {
        this.addTaskView = addTaskView;
        this.mNetTask = netTask;
    }

    @Override
    public void onSuccess(IpInfo ipInfo) {
        if (addTaskView.isActivity()){
            addTaskView.setInfo(ipInfo);
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFailed() {
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void getIpInfo(String s) {
        mNetTask.execute(s,this);
    }
}
