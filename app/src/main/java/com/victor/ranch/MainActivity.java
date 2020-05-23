package com.victor.ranch;


import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tv_schema)
    TextView mTvSchema;

    @Bind(R.id.tv_scan)
    TextView mTvScan;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resetLayoutByUsableHeight = true;
        super.onCreate(savedInstanceState);
        initialize();

    }

    private void initialize () {

        //必须设置否则scheme无法跳转
        mTvSchema.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick(R.id.tv_scan)
    public void OnScanClick (View v) {
        ZXingScanCodeActivity.intentStart(this,ZXingScanCodeActivity.class);
    }
}
