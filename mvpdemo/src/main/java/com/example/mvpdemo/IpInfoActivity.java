package com.example.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Display;

import com.example.mvpdemo.presenter.IpInfoPresenter;
import com.example.mvpdemo.presenter.IpInfoTask;

public class IpInfoActivity extends AppCompatActivity {

    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IpInfoFragment ipInfoFragment = new IpInfoFragment();
        if (ipInfoFragment != null) {
            ipInfoFragment = IpInfoFragment.newInstance();
            //TODO: Maybe there is wrong...
            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.contentFrame, ipInfoFragment);
            mFragmentTransaction.commit();
        }
        IpInfoTask ipInfoTask = IpInfoTask.getInstance();
        IpInfoPresenter presenter = new IpInfoPresenter(ipInfoFragment,ipInfoTask);
        ipInfoFragment.setPresenter(presenter);
    }
}