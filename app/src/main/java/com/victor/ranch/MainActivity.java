package com.victor.ranch;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.RadioGroup;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.victor.ranch.ui.adapter.BaseFragmentPagerAdapter;
import com.victor.ranch.ui.fragment.HomeFragment;
import com.victor.ranch.ui.fragment.MeFragment;
import com.victor.ranch.ui.fragment.WorkbenchFragment;
import com.victor.ranch.ui.widget.NoScrollViewPager;
import com.victor.ranch.util.Loger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @Bind(R.id.home_vpager)
    NoScrollViewPager mViewPager;

    @Bind(R.id.rg_bottom_nav)
    RadioGroup mRgBottomNav;

    private BaseFragmentPagerAdapter homeViewPagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

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
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(WorkbenchFragment.newInstance());
        fragmentList.add(MeFragment.newInstance());

        homeViewPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
        homeViewPagerAdapter.setFrags(fragmentList);

        mViewPager.setAdapter(homeViewPagerAdapter);

        mRgBottomNav.setOnCheckedChangeListener(this);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Loger.e(TAG,"onActivityResult-resultCode = " + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    LocalMedia media = selectList.get(selectList.size() - 1);
                    String url = media.getCutPath();
                    if (TextUtils.isEmpty(url)) {
                        url = media.getPath();
                    }
                    Loger.e(TAG,"onActivityResult-url = " + url);
                    PictureSelector.obtainMultipleResult(data).clear();
                    break;
            }
        }
    }

    public void selectImg () {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(9)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(190, 140)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                mViewPager.setCurrentItem(0,false);
                break;
            case R.id.rb_workbench:
                mViewPager.setCurrentItem(1,false);
                break;
            case R.id.rb_mine:
                mViewPager.setCurrentItem(2,false);
//                TestActivity.intentStart(this,TestActivity.class);
                break;
        }
    }
}
