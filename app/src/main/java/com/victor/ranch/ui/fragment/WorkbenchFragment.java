package com.victor.ranch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.victor.ranch.FarmListActivity;
import com.victor.ranch.LiveDeviceActivity;
import com.victor.ranch.LogListActivity;
import com.victor.ranch.PostGoodsActivity;
import com.victor.ranch.PostLogActivity;
import com.victor.ranch.ProductListActivity;
import com.victor.ranch.R;
import com.victor.ranch.data.WorkbenchData;
import com.victor.ranch.data.WorkbenchCellData;
import com.victor.ranch.interfaces.OnWorkbenchCellClickListener;
import com.victor.ranch.ui.adapter.WorkbenchAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;
import com.victor.ranch.util.AssetsFileReaderUtil;
import com.victor.ranch.util.JsonUtils;

import java.util.List;

import butterknife.Bind;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: WorkbenchFragment
 * Author: Victor
 * Date: 2020/5/27 上午 09:00
 * Description:
 * -----------------------------------------------------------------
 */
public class WorkbenchFragment extends BaseFragment implements
        OnWorkbenchCellClickListener {
    private String TAG = "WorkbenchFragment";

    @Bind(R.id.rv_workbench)
    LMRecyclerView mRvWorkbench;

    private WorkbenchAdapter mHomeAdapter;

    public static WorkbenchFragment newInstance () {
        WorkbenchFragment fragment = new WorkbenchFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constant.INTENT_DATA_KEY,data);
//        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.frag_workbench;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize () {
        String data = AssetsFileReaderUtil.getStringFromAssets(getContext(),"workbench_data.json");
        List<WorkbenchData> workbenchData = JsonUtils.parseArray(data, WorkbenchData.class);

        mHomeAdapter = new WorkbenchAdapter(getContext(),this);
        mHomeAdapter.setDatas(workbenchData);
        mRvWorkbench.setAdapter(mHomeAdapter);
    }

    @Override
    public boolean handleBackEvent() {
        return false;
    }

    @Override
    public void freshFragData() {

    }

    @Override
    public void OnWorkbenchCelClick(WorkbenchCellData data) {
        switch (data.id) {
            case 11:

                break;
            case 12:
                FarmListActivity.intentStart(getActivity(),FarmListActivity.class);
                break;
            case 21:
                PostGoodsActivity.intentStart(getActivity(),PostGoodsActivity.class);
                break;
            case 22:
                ProductListActivity.intentStart(getActivity(),ProductListActivity.class);
                break;
            case 31:
                PostLogActivity.intentStart(getActivity(),PostLogActivity.class);
                break;
            case 32:
                LogListActivity.intentStart(getActivity(),LogListActivity.class);
                break;
            case 41:
                LiveDeviceActivity.intentStart(getActivity(),LiveDeviceActivity.class);
                break;
        }
    }
}
