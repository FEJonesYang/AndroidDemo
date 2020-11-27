package com.example.mvpdemo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvpdemo.interfaces.IpInfoContract;
import com.example.mvpdemo.mode.IpData;
import com.example.mvpdemo.mode.IpInfo;

/**
 * @author JonesYang
 * @Data 2020-11-23
 * @Function
 */
public class IpInfoFragment extends Fragment implements IpInfoContract.View {


    private TextView mTv_country;
    private TextView mTv_area;
    private TextView mTv_city;
    private Button mButton;
    private IpInfoContract.Presenter mPresenter;
    private Dialog mDialog;

    public static IpInfoFragment newInstance() {
        return new IpInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ipinfo, container, false);

        mTv_country = view.findViewById(R.id.tv_country);
        mTv_area = view.findViewById(R.id.tv_area);
        mTv_city = view.findViewById(R.id.tv_city);
        mButton = view.findViewById(R.id.bt_ipinfo);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialog = new ProgressDialog(getContext());
        mDialog.setTitle("数据获取中...");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getIpInfo("39.155.184.147");
            }
        });
    }

    @Override
    public void setInfo(IpInfo info) {
        if (info != null && info.getData() != null) {
            IpData ipData = info.getData();
            mTv_country.setText(ipData.getCountry());
            mTv_area.setText(ipData.getArea());
            mTv_city.setText(ipData.getCity());
        }
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(),"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActivity() {
        return false;
    }

    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
