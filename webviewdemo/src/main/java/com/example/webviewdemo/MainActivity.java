package com.example.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mWebSettings;
    private TextView mLoading;
    private TextView mBegin_loading;
    private TextView endLoading;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        SettingWebView();
    }

    /**
     * 使用 WebSettings 对 WebView 进行配置
     */
    private void SettingWebView() {
        // 设置能够与 JS 进行交互
        mWebSettings.setJavaScriptEnabled(true);
        // 设置自适应屏幕
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        //缩放操作
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setDisplayZoomControls(true);

        //缓存相关
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }


    private void initEvent() {
        //激活 WebView
        mWebView.onResume();
        mWebView.loadUrl("https://juejin.cn/post/6844903976240939021");

        /**
         * 设置 WebViewClient
         */
        mWebView.setWebViewClient(new WebViewClient() {
            // 不需要调用浏览器，直接在该WebView 展示 网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("开始加载....");
                mBegin_loading.setText("开始加载....");
            }


            //设置结束时加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                endLoading.setText("结束加载...");
            }

            //如果服务器出现错误
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                System.out.println("服务器出现错误...");
            }

            //WebView 不支持使用 https，如果要使用，用该方法进行设置
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 等待证书响应
                handler.proceed();
            }
        });

        /**
         * 设置 WebChromeClient
         */
        mWebView.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("Title is there...");
                mTitle.setText(title);
            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress <= 100) {
                    mLoading.setText(newProgress + "%");
                } else {
                    System.out.println("Progress is wrong...");
                }
            }

        });
    }


    /**
     * 点击返回上一个页面而不是直接退出浏览器
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 销毁 WebView
     */
    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html",
                    "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mWebView = findViewById(R.id.webView1);
        mWebSettings = mWebView.getSettings();
        mLoading = findViewById(R.id.text_Loading);
        mBegin_loading = findViewById(R.id.text_beginLoading);
        endLoading = findViewById(R.id.text_endLoading);
        mTitle = findViewById(R.id.title);
    }


}