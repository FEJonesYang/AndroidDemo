package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contentprovider.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton_logon;
    private Button mButton_login;
    private EditText mEditText_UserName;
    private EditText mEditText_UserPassword;

    //暂时存储用户信息

    private DBHelper mOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        //初始化视图
        initView();
        //初始化事件
        initEvent();
    }


    private void initEvent() {
        //设置点击事件
        mButton_login.setOnClickListener(this);
        mButton_logon.setOnClickListener(this);
        //创建数据库
        mOpenHelper = new DBHelper(this);
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

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_logon:
                if (queryPeopleInDatabase()) {
                    intent = new Intent(this, LogonActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn_login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 查询数据是否存在数据，默认返回 false
     *
     * @return false
     */
    public boolean queryPeopleInDatabase() {
        String name = mEditText_UserName.getText().toString();
        String password = mEditText_UserPassword.getText().toString();
        return mOpenHelper.queryPeopleInfoById(name, password);
    }
}