package com.example.mvpdemo.interfaces;

import com.example.mvpdemo.mode.IpInfo;

/**
 * @author JonesYang
 * @Data 2020-11-23
 * @Function
 */
public interface IpInfoContract {
    interface Presenter {
        void getIpInfo(String s);
    }

    interface View extends BaseView<Presenter> {
        void setInfo(IpInfo info);

        void showLoading();

        void hideLoading();

        void showError();

        boolean isActivity();
    }
}
