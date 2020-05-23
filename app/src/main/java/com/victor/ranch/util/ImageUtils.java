package com.victor.ranch.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.victor.ranch.R;

import java.io.File;

/**
 * 作者: Victor
 * 时间: 2016-12-20
 * 描述:
 * 图片加载工具类基于 glide 4.0.0版本
 * 对第三方图片加载库的一个包装
 * 屏蔽部分差异
 */

public class ImageUtils {

    public static final String TAG = "ImageUtils";
    private int animDuration = 500;

    private static ImageUtils sImageUtils;
    private RequestOptions options;

    public static ImageUtils get() {
        if (sImageUtils == null) {
            sImageUtils = new ImageUtils();
        }
        return sImageUtils;
    }

    public ImageUtils () {
        getRequestOptions(ColorUtil.getDefaultRandomColor());
    }

    /**
     * 加载头像
     *
     * @param imageView
     * @param url
     */
    public void loadAvatar(final Context context,final ImageView imageView, String url) {
        if (imageView == null)
            return;
        if (TextUtils.isEmpty(url)) {
            ColorDrawable colorDrawable = new ColorDrawable(ColorUtil.getDefaultRandomColor());
            imageView.setImageDrawable(colorDrawable);
        } else {
            ImageUtils.get().loadImage(context,imageView, url);
            Glide.with(context)
                    .load(url)
                    .apply(options.placeholder(ColorUtil.getDefaultRandomColor()).error(ColorUtil.getDefaultRandomColor()))
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            if (resource != null) {
                                BitmapDrawable bd = (BitmapDrawable) resource;
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(context.getResources(), bd.getBitmap());
                                circularBitmapDrawable.setCircular(true);
                                imageView.setImageDrawable(circularBitmapDrawable);
                            }
                        }
                    });
        }
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     */
    public Target loadImage(Context context, ImageView imageView, String url) {
        if (imageView == null)
            return null;
        return Glide.with(context)
                .load(url).apply(options.placeholder(ColorUtil.getDefaultRandomColor())
                .error(ColorUtil.getDefaultRandomColor()))
                .transition(new DrawableTransitionOptions().crossFade(animDuration))
                .into(imageView);
    }
    public Target loadImage(Context context,ImageView imageView, String url,int placeDrawable,boolean showPlaceDrawable) {
        if (imageView == null)
            return null;
        if (showPlaceDrawable) {
            return Glide.with(context)
                    .load(url).apply(options.placeholder(placeDrawable)
                            .error(placeDrawable))
                    .transition(new DrawableTransitionOptions().crossFade(animDuration))
                    .into(imageView);
        }
        return Glide.with(context)
                .load(url).apply(options.error(placeDrawable))
                .transition(new DrawableTransitionOptions().crossFade(animDuration))
                .into(imageView);
    }
    public Target loadImage(Context context,ImageView imageView, String url,Drawable placeDrawable) {
        if (imageView == null)
            return null;
        return Glide.with(context)
                .load(url).apply(options.placeholder(placeDrawable)
                .error(placeDrawable))
                .transition(new DrawableTransitionOptions().crossFade(animDuration))
                .into(imageView);
    }
    public Target loadImage(Context context,ImageView imageView, Bitmap bitmap) {
        if (imageView == null)
            return null;
        return Glide.with(context)
                .load(bitmap).apply(options.placeholder(ColorUtil.getDefaultRandomColor())
                .error(ColorUtil.getDefaultRandomColor()))
                .transition(new DrawableTransitionOptions().crossFade(animDuration))
                .into(imageView);
    }

    public Target loadImage(Context context,ImageView imageView, String url, int placeDrawable) {
        if (imageView == null)
            return null;
        return Glide.with(context)
                .load(url).apply(options.placeholder(placeDrawable).fitCenter()
                .error(placeDrawable))
                .transition(new DrawableTransitionOptions().crossFade(animDuration))
                .into(imageView);
    }

    /**
     * 从文件加入
     *
     * @param imageView
     * @param file
     */
    public void loadImage(Context context,ImageView imageView, File file) {
        if (imageView == null)
            return;
        Glide.with(context)
                .load(file)
                .apply(options.placeholder(ColorUtil.getDefaultRandomColor())
                        .error(ColorUtil.getDefaultRandomColor()))
                .transition(new DrawableTransitionOptions().crossFade(animDuration))
                .into(imageView);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param drawableRes
     */
    public void loadImage(Context context,ImageView imageView, int drawableRes) {
        if (imageView == null)
            return;
        loadImage(context,imageView,drawableRes,ColorUtil.getDefaultRandomColor());
    }


    public void loadImage(Context context,ImageView imageView, int drawableRes, int placeDrawable) {
        if (imageView == null)
            return;
        Glide.with(context).load(drawableRes).apply(options.placeholder(placeDrawable)
                .error(placeDrawable)).transition(new DrawableTransitionOptions()
                .crossFade(animDuration)).into(imageView);
    }

    /**
     * 加载高斯模糊背景图片
     *
     * @param imageView
     * @param url
     */
    public void imageGauss(Context context,final ImageView imageView, String url, final int radius) {
        if (imageView == null) {
            Loger.e(TAG,"imageView == null");
            return;
        }
        Glide.with(context)
                .load(url)
                .apply(options.error(ColorUtil.getDefaultRandomColor()))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (resource != null) {
                            imageGauss(imageView,drawableToBitmap(resource),radius);
                        }
                    }
                });
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);

        return bitmap;
    }
    public void imageGauss(Context context,final ImageView imageView, String url) {
        if (imageView == null) {
            Loger.e(TAG,"imageView == null");
            return;
        }
        imageGauss(context,imageView,url,10);
    }

    public void imageGauss(ImageView imageView, Bitmap bitmap) {
        imageGauss(imageView,bitmap,10);
    }
    public void imageGauss(ImageView imageView, Bitmap bitmap,int radius) {
        imageView.setImageBitmap(ImgBlurUtil.getInstanse().blurImg(bitmap,radius));
//        loadImage(imageView,ImgBlurUtil.getInstanse().blurImg(bitmap,radius));

    }

    public RequestOptions getRequestOptions (int resId) {
        if (options == null) {
            options = new RequestOptions()
                    .centerCrop()
                    .placeholder(resId)
                    .error(resId)
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        return options;
    }



}
