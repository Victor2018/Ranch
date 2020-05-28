package com.victor.ranch;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @Bind(R.id.tv_user_agreement)
    TextView mTvUserAgreement;

    @Bind(R.id.rg_user)
    RadioGroup mRgUser;

    @Bind(R.id.rb_general_user)
    RadioButton mRbGeneralUser;

    @Bind(R.id.rb_farmers)
    RadioButton mRbFarmers;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize () {
        mTvUserAgreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG ); //下划线
        mTvUserAgreement.getPaint().setAntiAlias(true);//抗锯齿

        mRgUser.setOnCheckedChangeListener(this);

        MainActivity.intentStart(this,MainActivity.class);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_general_user:
                break;
            case R.id.rb_farmers:
                break;
        }
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick (View view) {
        MainActivity.intentStart(this,MainActivity.class);
        finish();
    }
}
