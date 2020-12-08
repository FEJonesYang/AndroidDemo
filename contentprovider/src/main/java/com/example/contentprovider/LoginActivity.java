package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    private EditText mEditText_UserName;
    private EditText mEditText_UserPassword;
    private Button mButton_logon;
    private Button mButton_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initView() {
        //账号
        mEditText_UserName = findViewById(R.id.et_name);
        //密码
        mEditText_UserPassword = findViewById(R.id.et_password);
        //登陆
        mButton_logon = findViewById(R.id.btn_logon);
        //注册
        mButton_login = findViewById(R.id.btn_login);
    }

    private void initEvent() {

    }

    public boolean createPeopleToDatabase() {

        return false;
    }
}