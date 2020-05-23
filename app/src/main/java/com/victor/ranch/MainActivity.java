package com.victor.ranch;


import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tv_schema)
    TextView mTvSchema;

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
}
