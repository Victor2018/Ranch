package com.victor.ranch;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.FarmListAdapter;
import com.victor.ranch.ui.adapter.ProductListAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ProductListActivity
 * Author: Victor
 * Date: 2020/5/25 上午 11:22
 * Description:
 * -----------------------------------------------------------------
 */
public class ProductListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.rv_product)
    LMRecyclerView mRvProduct;

    private ProductListAdapter mProductListAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        mProductListAdapter = new ProductListAdapter(this,this);
        mProductListAdapter.setHeaderVisible(false);
        mProductListAdapter.setFooterVisible(false);
        mProductListAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvProduct.setAdapter(mProductListAdapter);
    }

    private void initData () {
        for (int i=0;i<6;i++) {
            mProductListAdapter.add(new NewsInfo());
        }
        mProductListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_back)
    public void OnBackClick(View v) {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
