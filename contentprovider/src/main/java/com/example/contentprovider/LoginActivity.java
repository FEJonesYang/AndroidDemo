package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contentprovider.db.DBHelper;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText_UserName;
    private EditText mEditText_UserPassword;
    private Button mButton_login;
    private DBHelper mDBHelper;
    private Button mButton_back_logon;


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
        mButton_back_logon = findViewById(R.id.btn_back_logon);
        //注册
        mButton_login = findViewById(R.id.btn_login);
    }

    private void initEvent() {
        mDBHelper = new DBHelper(this);
        mButton_back_logon.setOnClickListener(this);
        mButton_login.setOnClickListener(this);
    }

    /**
     * 插入用户到数据库
     *
     * @return
     */
    public boolean createPeopleToDatabase() {
        mDBHelper.insertPeopleById(mEditText_UserName.getText().toString(),
                mEditText_UserPassword.getText().toString());
        return true;
    }

    /**
     * 处理点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_back_logon:
                intent = new Intent(this, LaunchActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                boolean flag = queryPeopleInDatabase();
                if (flag) {
                    Toast.makeText(this, "用户已存在，请重新输入 ID ！", Toast.LENGTH_SHORT).show();
                } else if (!flag) {
                    //先把数据存储在数据库中
                    createPeopleToDatabase();
                    //弹出一个对话框，让用户选择是否需要登陆
                    showDialog();
                }
                break;
            default:
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
        return mDBHelper.queryPeopleInfoById(name, password);
    }

    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("是否选择登陆？");
        builder.setMessage("登陆后将会跳转到首页...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(LoginActivity.this, LogonActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}