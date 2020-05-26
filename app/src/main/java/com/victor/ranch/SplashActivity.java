package com.victor.ranch;

import android.os.Bundle;
import android.os.Message;

import com.victor.okhttp.library.module.MainHandler;
import com.victor.ranch.util.Constant;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize () {
        MainHandler.get().sendEmptyMessageDelayed(Constant.Msg.ENTER_LOGIN,1000);
    }

    @Override
    public void handleMainMessage(Message message) {
        super.handleMainMessage(message);
        switch (message.what) {
            case Constant.Msg.ENTER_LOGIN:
                LoginActivity.intentStart(this,LoginActivity.class);
                finish();
                break;
        }
    }
}
