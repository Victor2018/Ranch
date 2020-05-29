package com.victor.ranch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.victor.ranch.ContactUsActivity;
import com.victor.ranch.MessageCenterActivity;
import com.victor.ranch.R;
import com.victor.ranch.SettingActivity;
import com.victor.ranch.ui.dialog.CustomerServiceDialog;

import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: MyFragment
 * Author: Victor
 * Date: 2020/5/27 上午 09:00
 * Description:
 * -----------------------------------------------------------------
 */
public class MeFragment extends BaseFragment {
    public static MeFragment newInstance () {
        MeFragment fragment = new MeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constant.INTENT_DATA_KEY,data);
//        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.frag_me;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.tv_customer_service)
    public void onCustomerServiceClick(View view) {
        CustomerServiceDialog customerServiceDialog = new CustomerServiceDialog(getActivity());
        customerServiceDialog.show();
    }

    @OnClick(R.id.tv_contact_us)
    public void onContactUsClick(View view) {
        ContactUsActivity.intentStart(getActivity(), ContactUsActivity.class);
    }

    @OnClick(R.id.tv_message_center)
    public void onMessageCenterClick(View view) {
        MessageCenterActivity.intentStart(getActivity(),MessageCenterActivity.class);
    }
    @OnClick(R.id.tv_setting)
    public void onSettingClick(View view) {
        SettingActivity.intentStart(getActivity(),SettingActivity.class);
    }

    @Override
    public boolean handleBackEvent() {
        return false;
    }

    @Override
    public void freshFragData() {

    }
}
