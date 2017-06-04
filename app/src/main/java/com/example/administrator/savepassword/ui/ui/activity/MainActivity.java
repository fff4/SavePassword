package com.example.administrator.savepassword.ui.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.savepassword.R;
import com.example.administrator.savepassword.ui.base.BaseActivity;
import com.example.administrator.savepassword.ui.itface.LoginInterface;
import com.example.administrator.savepassword.ui.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.administrator.savepassword.R.id.et_pwd_login;

public class MainActivity extends BaseActivity implements LoginInterface.LoginView {

    @BindView(R.id.et_phone_login)
    TextInputEditText mEtPhoneLogin;
    @BindView(R.id.til_mobile)
    TextInputLayout mTilMobile;
    @BindView(et_pwd_login)
    TextInputEditText mEtPwdLogin;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.to_find_pwd)
    TextView mToFindPwd;
    @BindView(R.id.to_register)
    TextView mToRegister;
    @BindView(R.id.id_tool_bar)
    Toolbar mIdToolBar;
    @BindView(R.id.tv_toolbar)
    TextView mTvToolbar;

    private Handler mHandler = new Handler();
    private LoginInterface.LoginPresenter mPresenter = new LoginPresenter(this);

    @OnClick({R.id.login, R.id.to_find_pwd, R.id.to_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mPresenter.onLoginClick();
                break;
            case R.id.to_find_pwd:
                break;
            case R.id.to_register:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                break;
        }
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        initToolBar();
    }

    private void initToolBar() {
        //用Toolbar替换原来的ActionBar
        setSupportActionBar(mIdToolBar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(null);
        mTvToolbar.setVisibility(View.VISIBLE);
        mTvToolbar.setText("登录");
    }

    @Override
    public String getPhone() {
        return mEtPhoneLogin.getText().toString().trim();
    }

    @Override
    public String getpwd() {
        return mEtPwdLogin.getText().toString().trim();
    }

    @Override
    public void onLoginSuccess() {
        finish();
        Intent intent = new Intent(this, PasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginError(String text) {
        showToast(text);
    }

    @Override
    public void onChangeUI(String text, boolean isEnable) {

    }

    public void showToast(final String text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
