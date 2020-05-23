package com.victor.ranch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.victor.ranch.util.Loger;
import com.victor.ranch.util.ToastUtils;
import com.victor.zxing.library.interfaces.OnQrScanListener;
import com.victor.zxing.library.module.ZxingScanHelper;
import com.victor.zxing.library.zxing.view.ScannerView;

import butterknife.Bind;
import butterknife.OnClick;

public class ZXingScanCodeActivity extends BaseActivity implements OnQrScanListener{

    @Bind(R.id.scanner_view)
    ScannerView mScannerView;

    private ZxingScanHelper mZxingScanHelper;

    private String scanResult;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_zxing_scan_code;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize () {

        mScannerView.setLaserFrameBoundColor(getResources().getColor(R.color.color_E5A14B));//扫描框四角颜色
        mScannerView.setLaserColor(getResources().getColor(R.color.color_FED000));//设置扫描线颜色

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermission(new String[]{Manifest.permission.CAMERA});
            }
        }
    }

    private void openBrowser ( Uri uri) {
        try {
            Intent intent = new Intent();
            intent.setData(uri);//Url 就是你要打开的网址
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent); //启动浏览器
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mZxingScanHelper = new ZxingScanHelper(this,mScannerView,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mZxingScanHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mZxingScanHelper.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZxingScanHelper.onDestroy();
    }

    public void handleResult(String resultString) {
        scanResult = resultString;
        if (resultString.equals("")) {
            ToastUtils.showShort(R.string.scan_failed);
        } else {
            Loger.e(TAG,"resultString=============>" + resultString);
            openBrowser(Uri.parse("https://www.baidu.com/"));
        }
    }

    @OnClick(R.id.tv_skip)
    public void onInputCodeClick (View view) {
        finish();
    }

    @Override
    public void OnQrScan(String resultString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        handleResult(resultString);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mZxingScanHelper.onActivityResult(requestCode,resultCode,data);
    }
}
