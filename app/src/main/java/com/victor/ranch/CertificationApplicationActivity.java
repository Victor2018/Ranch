package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.victor.ranch.util.Loger;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CertificationApplicationActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class CertificationApplicationActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_certification_application;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resetLayoutByUsableHeight = true;
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize () {
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }

}
