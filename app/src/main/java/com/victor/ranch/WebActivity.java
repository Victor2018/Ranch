package com.victor.ranch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.victor.ranch.ui.widget.ProgressWebView;
import java.lang.reflect.InvocationTargetException;

public class WebActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = "WebActivity";
    private static final String WEB_TITLE_KEY = "WEB_TITLE_KEY";
    private static final String WEB_URL_KEY = "WEB_URL_KEY";

    private ImageView mIvBack;
    private TextView mTvTitle;
    private ProgressWebView mWebView;

    public static void intentStart(Context context, String url) {
        if (context == null) return;
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WEB_TITLE_KEY,"");
        intent.putExtra(WEB_URL_KEY,url);
        context.startActivity(intent);
    }
    public static void intentStart(Context context, String title, String url) {
        if (context == null) return;
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WEB_TITLE_KEY,title);
        intent.putExtra(WEB_URL_KEY,url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initData(getIntent());
    }

    private void initialize () {

        mIvBack = findViewById(R.id.iv_back);
        mTvTitle = findViewById(R.id.tv_title);
        mWebView = findViewById(R.id.webview);

        mIvBack.setOnClickListener(this);
    }

    private void initData (Intent intent) {
        String title = intent.getStringExtra(WEB_TITLE_KEY);
        String url = intent.getStringExtra(WEB_URL_KEY);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        Log.e(TAG,"url = " + url);
        mTvTitle.setText(title);
        mWebView.loadUrl(url);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (mWebView.canGoBack()){
                    mWebView.goBack();
                    return;
                }
                finish();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            try {
                mWebView.getClass().getMethod("onPause").invoke(mWebView,  (Object[])null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            try {
                mWebView.resumeTimers();
                mWebView.getClass().getMethod("onResume").invoke(mWebView,  (Object[])null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.clearHistory();
            mWebView.clearCache(true);
            mWebView.loadUrl("about:blank"); // clearView() should be changed to loadUrl("about:blank"), since clearView() is deprecated now
            mWebView.freeMemory();
            mWebView.pauseTimers();
            mWebView = null; // Note that mWebView.destroy() and mWebView = null do the exact same thing
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initData(intent);
    }

}
