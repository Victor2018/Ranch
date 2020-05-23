package com.victor.ranch.module;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.victor.ranch.WebActivity;
import com.victor.ranch.util.Loger;

public class SchemaManager {
    private static String TAG = "SchemaManager";
    private static final String SCHEME_DOMAIN = "activation_code_redemption";

    public static final String DOMAIN_SHARE = "share";


    /**
     * 影片详情
     */
    public static final String DOMAIN_DETAIL = "detail";


    /**
     * 打开外部地址
     */
    public static final String DOMAIN_OPEN_URL = "openurl";


    /**
     * 当前界面传入
     *
     * @param ctx
     * @param uri
     */
    public static void dispatchSchema(Activity ctx, Uri uri) {
        Log.e(TAG,"dispatchSchema()-uri = " + uri);
        if (uri == null)
            return;
        final String domain = uri.getAuthority();
        try {
            switch (domain) {
                case DOMAIN_SHARE:
                    String action = uri.getQueryParameter("action");
                    Log.e(TAG,"dispatchSchema()-action = " + action);
                    break;
                case DOMAIN_OPEN_URL:
                    //打开浏览器
                    schemaWeb(ctx, uri,"");
                    break;
                default:
                    //剩下的需要校验登录状态
                    dispatchSchemeIfLogin(ctx, domain, uri);
                    break;
            }
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录之后才能处理的动作
     *
     * @param ctx
     * @param domain
     * @param uri
     */
    private static void dispatchSchemeIfLogin(Context ctx, String domain, Uri uri) {
        Loger.d("SchemaManager","dispatchSchemeIfLogin-domain = " + domain);
        switch (domain) {
            case DOMAIN_DETAIL:
                schemeDetail(ctx,uri);
                break;
        }
    }


    /**
     * 从App内部点击进入
     *
     * @param uri
     */
    private static void schemeDetail(Context context,Uri uri) {
        String url = uri.getQueryParameter("url");
        if (TextUtils.isEmpty(url))
            return;

//        Intent intent = new Intent(context, MovieDetailActivity.class);
//        intent.putExtra(Constant.INTENT_DATA_KEY,url);
//        context.startActivity(intent);
    }

    private static void schemaWeb(Context context,Uri uri,String title) {
        String url = uri.getQueryParameter("url");
        if (TextUtils.isEmpty(url))
            return;
        WebActivity.intentStart(context,title,url);
    }


}
