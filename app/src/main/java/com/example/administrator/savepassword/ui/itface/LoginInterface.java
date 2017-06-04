package com.example.administrator.savepassword.ui.itface;

/**
 * 登录页面的接口定制
 */

public interface LoginInterface {

    //定义view的接口---注册成功失败
    interface LoginView{
        String getPhone();
        String getpwd();

        public void onLoginSuccess();
        public void onLoginError(String text);
        public void onChangeUI(String text, boolean isEnable);
    }

    //定义model的接口
    interface LoginModel{
        public void onLogin(String phone, String pwd, LoginView loginView);
    }

    //定义presenter的接口
    interface LoginPresenter{
        public void onLoginClick();
    }

}
