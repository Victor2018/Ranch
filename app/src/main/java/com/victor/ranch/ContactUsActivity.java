package com.victor.ranch;


import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CustomerServiceActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class ContactUsActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_contact_us;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }


}
