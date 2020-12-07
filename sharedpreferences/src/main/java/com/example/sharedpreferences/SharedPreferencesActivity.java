package com.example.sharedpreferences;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author JonesYang
 * @Data 2020-12-07
 * @Function
 */
public class SharedPreferencesActivity extends AppCompatActivity {
    private static final String UserInfo = "userInfo";
    private static final String TAG = "SharedPreferencesActivity";
    private Button mSave_btn;
    private Button mDisplay_btn;
    private EditText mEt_id;
    private EditText mEt_name;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        saveUserInfo();
        getUserInfo();
        removeUserInfo();
        moreUsefulInfo();
    }


    /**
     * 保存用户信息
     */
    public void saveUserInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserInfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserName", "JonesYang");
        editor.putString("School", "CQUPT");
        editor.apply();
    }

    /**
     * 获取用户信息
     */
    @SuppressLint("LongLogTag")
    public void getUserInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserInfo, MODE_PRIVATE);
        String name = sharedPreferences.getString("UserName", null);
        String school = sharedPreferences.getString("School", null);
        Log.d(TAG, "The user name is --> " + name + ",and school is --> " + school + ".");
    }

    /**
     * 删除用户信息
     */
    public void removeUserInfo() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(UserInfo, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("UserName");
        editor.commit();
    }





    private void initView() {
        mSave_btn = findViewById(R.id.save_btn);
        mDisplay_btn = findViewById(R.id.display_btn);
        mEt_id = findViewById(R.id.product_id);
        mEt_name = findViewById(R.id.product_name);
        mImageView = findViewById(R.id.picture);
    }


    /**
     * 使用 SharedPreferences 加载类的信息和图像
     * <p>
     * 如果要用 SharedPreferences 存取复杂的数据类型（类，图像等），
     * 就需要对这些数据进行编码。通常会将复杂类型的数据转换成Base64编码，
     * 然后将转换后的数据以字符串的形式保存在XML文件中。
     * <p>
     * 作者：食梦兽
     * 链接：https://www.jianshu.com/p/ae2c7004179d
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public void moreUsefulInfo() {
        //save
        mSave_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                try {
                    //保存对象
                    Log.e(TAG,"开始保存对象...");
                    Product product = new Product();
                    product.setId(mEt_id.getText().toString().trim());
                    product.setName(mEt_name.getText().toString().trim());
                    SharedPreferences sharedPreferences = getSharedPreferences("base64", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //使用 ByteArrayOutputString
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(product);
                    String base64Product = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    editor.putString("product", base64Product);

                    //保存图片
                    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                    ((BitmapDrawable) getResources().getDrawable(R.mipmap.play_pause))
                            .getBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream1);
                    String img = Base64.encodeToString(byteArrayOutputStream1.toByteArray(), Base64.DEFAULT);
                    editor.putString("productImg", img);
                    editor.apply();

                    //关闭流
                    objectOutputStream.close();
                    byteArrayOutputStream.close();
                    byteArrayOutputStream1.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e(TAG,"保存成功!");
            }
        });
        //show
        mDisplay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //获取对象
                    SharedPreferences sharedPreferences = getSharedPreferences("base64",MODE_PRIVATE);
                    String productString = sharedPreferences.getString("product",null);
                    byte[] base64Product = Base64.decode(productString,Base64.DEFAULT);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64Product);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Product product = (Product) objectInputStream.readObject();
                    mEt_id.setText(product.getId());
                    mEt_name.setText(product.getName());

                    //获取图片
                    String imgString = sharedPreferences.getString("productImg",null);
                    byte[] imgByte = Base64.decode(imgString,Base64.DEFAULT);
                    ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(imgByte);
                    mImageView.setImageDrawable(Drawable.createFromStream(byteArrayInputStream1,"imagByte"));
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}
