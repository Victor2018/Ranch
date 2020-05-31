package com.victor.ranch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.AdapterView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.victor.ranch.NewsCenterActivity;
import com.victor.ranch.R;
import com.victor.ranch.WebActivity;
import com.victor.ranch.data.NewsInfo;
import com.victor.ranch.ui.adapter.NewsAdapter;
import com.victor.ranch.ui.widget.LMRecyclerView;
import com.victor.ranch.util.DateUtils;
import com.victor.ranch.util.ResUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HomeFragment
 * Author: Victor
 * Date: 2020/5/27 上午 08:57
 * Description:
 * -----------------------------------------------------------------
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.nsv_content)
    NestedScrollView mNsvContent;

    @Bind(R.id.rv_news)
    LMRecyclerView mRvNews;

    @Bind(R.id.line_chart)
    LineChart mLineChart;

    private NewsAdapter mNewsAdapter;

    public static HomeFragment newInstance () {
        HomeFragment fragment = new HomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constant.INTENT_DATA_KEY,data);
//        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.frag_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        initData();
    }

    private void initialize () {
        //取消recycleview的滑动
//        mRvMessage.setHasFixedSize(true);
//        mRvMessage.setNestedScrollingEnabled(false);

        mNsvContent.post(new Runnable() {
            @Override
            public void run() {
                mNsvContent.smoothScrollTo(0, 0);
            }
        });
        mRvNews.post(new Runnable() {
            @Override
            public void run() {
                mRvNews.smoothScrollToPosition(0);
            }
        });

        mNewsAdapter = new NewsAdapter(getActivity(),this);
        mNewsAdapter.setHeaderVisible(false);
        mNewsAdapter.setFooterVisible(false);
        mNewsAdapter.setEndTextColor(R.color.color_BDBDBD);
        mRvNews.setAdapter(mNewsAdapter);

    }

    private void initLineChart (final List<Integer> list) {

        //是否显示边界
        mLineChart.setDrawBorders(false);
        //取消缩放
        mLineChart.setScaleEnabled(false);
        //取消点击
        mLineChart.setTouchEnabled(false);
        //无数据时显示的文字
        mLineChart.setNoDataText("暂无数据");
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            entries.add(new Entry(DateUtils.randomStamp("2020-01-01","2020-12-30"), (float) list.get(i)));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        lineDataSet.setLabel("交易额（元）");
        //线颜色
        lineDataSet.setColor(ResUtils.getColorRes(R.color.color_03C89B));
        //线宽度
        lineDataSet.setLineWidth(1.6f);
        //不显示圆点
        lineDataSet.setDrawCircles(false);
        //线条平滑
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        //设置折线图填充
////        lineDataSet.setDrawFilled(true);
        LineData data = new LineData(lineDataSet);
        //折线图不显示数值
        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setValueFormatter(new XValueFormatter());
        //设置y轴字体大小颜色
        xAxis.setTextColor(ResUtils.getColorRes(R.color.color_8C8C8C));
        xAxis.setTextSize(12f);
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(list.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(-45);


        //得到Y轴
        YAxis yAxis = mLineChart.getAxisLeft();
        YAxis rightYAxis = mLineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示

        yAxis.setValueFormatter(new YValueFormatter());

        //设置y轴字体大小颜色
        yAxis.setTextColor(ResUtils.getColorRes(R.color.color_8C8C8C));
        yAxis.setTextSize(12f);
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(10);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(Collections.max(list) + 2, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(Collections.max(list) + 1);

        //y轴
        //图例：得到Lengend
        Legend legend = mLineChart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);
        //设置数据
        mLineChart.setData(data);
        //图标刷新
        mLineChart.invalidate();
    }

    private void initData () {
        for (int i=0;i<2;i++) {
            mNewsAdapter.add(new NewsInfo());
        }
        mNewsAdapter.notifyDataSetChanged();


        List<Integer> list = new ArrayList<>();
        for (int i=0;i<20;i++) {
            int num = new Double(Math.random() * 100).intValue();
            list.add(num);
        }

        initLineChart(list);
    }

    @OnClick(R.id.tv_more)
    public void onMoreClick(View view) {
        NewsCenterActivity.intentStart(getActivity(),NewsCenterActivity.class);
    }

    @Override
    public boolean handleBackEvent() {
        return false;
    }

    @Override
    public void freshFragData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WebActivity.intentStart(getContext(),"咨询中心","https://www.baidu.com/");

    }

    class  XValueFormatter extends ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return DateUtils.formatStamp((long)value,"MM.dd");
        }
    }
    class  YValueFormatter extends ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return (int)value + "W";
        }
    }
}
