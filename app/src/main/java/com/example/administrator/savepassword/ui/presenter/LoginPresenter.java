package com.example.administrator.savepassword.ui.presenter;

import com.example.administrator.savepassword.ui.itface.LoginInterface;
import com.example.administrator.savepassword.ui.model.LoginModel;

/**
 * 登录页面的处理层
 */

public class LoginPresenter implements LoginInterface.LoginPresenter {

    private LoginInterface.LoginView mLoginView;
    private LoginInterface.LoginModel mModel;

    public LoginPresenter(LoginInterface.LoginView loginView) {
        mLoginView = loginView;
        mModel = new LoginModel();
    }

    @Override
    public void onLoginClick() {
        mModel.onLogin(mLoginView.getPhone(), mLoginView.getpwd(), mLoginView);
    }
}
