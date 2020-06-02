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
 * File: PostLogActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class PostLogActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.spinner_farm)
    Spinner mSpinnerFarm;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_post_log;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resetLayoutByUsableHeight = true;
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize () {
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.farm_list);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        mSpinnerFarm.setAdapter(adapter);
        mSpinnerFarm.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Loger.e(TAG,"onItemSelected-position = " + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
