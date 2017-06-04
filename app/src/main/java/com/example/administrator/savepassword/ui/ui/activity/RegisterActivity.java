package com.example.administrator.savepassword.ui.ui.activity;

import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.savepassword.R;
import com.example.administrator.savepassword.ui.base.BaseActivity;
import com.example.administrator.savepassword.ui.itface.RegisterInterface;
import com.example.administrator.savepassword.ui.presenter.RegisterPresenter;

import butterknife.BindView;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements RegisterInterface.RegisterView, View.OnClickListener {

    @BindView(R.id.et_phone)
    TextInputEditText mEtPhone;
    @BindView(R.id.btn_phone_auth)
    Button mBtnPhoneAuth;
    @BindView(R.id.et_phone_auth)
    TextInputEditText mEtPhoneAuth;
    @BindView(R.id.et_pwd)
    TextInputEditText mEtPwd;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.id_tool_bar)
    Toolbar mIdToolBar;
    private RegisterPresenter mPresenter;

    private Handler mHandler = new Handler();

    @Override
    public int getLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        super.init();
        initView();
        initClick();
        initToolBar();

        SMSSDK.initSDK(this, "1e634791cf72e", "06ec235a8ebfafdc3fd62ad23b238e3f");
        SMSSDK.registerEventHandler(mPresenter.mEh); //注册短信回调
    }

    private void initToolBar() {
        //用Toolbar替换原来的ActionBar
        setSupportActionBar(mIdToolBar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("注册账号");
        //显示返回按钮
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 处理ActionBarDrawerToggle的点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //标题栏返回按钮
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //初始化 p 层对象
    private void initView() {
        mPresenter = new RegisterPresenter(this);
    }

    //点击事件调用
    private void initClick() {
        mRegister.setOnClickListener(this);
        mBtnPhoneAuth.setOnClickListener(this);
    }

    @Override
    public String getPhone() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public String getPhoneAuth() {
        return mEtPhoneAuth.getText().toString().trim();
    }

    @Override
    public String geRegisterpwd() {
        return mEtPwd.getText().toString().trim();
    }

    @Override
    public void onRegisterSuccess() {
        showToast("注册成功");
        finish();
    }

    @Override
    public void onRegisterError(String text) {
        showToast(text);
    }

    @Override
    public void onChangeUI(final String text, final boolean isEnable) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mBtnPhoneAuth.setText(text);
                mBtnPhoneAuth.setEnabled(isEnable);
            }
        });
    }


    //当点击注册 和 发送短信
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                mPresenter.register();
                break;
            case R.id.btn_phone_auth:
                mPresenter.sendSms();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁回调监听接口
        SMSSDK.unregisterAllEventHandler();
    }

    public void showToast(final String text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
