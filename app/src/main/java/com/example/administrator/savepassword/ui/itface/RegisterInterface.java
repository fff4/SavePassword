package com.example.administrator.savepassword.ui.itface;

/**
 * 注册页面的接口
 */

public interface RegisterInterface {

    //定义view的接口---注册成功失败
    interface RegisterView{
        String getPhone();
        String getPhoneAuth();
        String geRegisterpwd();

        public void onRegisterSuccess();
        public void onRegisterError(String text);
        public void onChangeUI(String text, boolean isEnable);
    }

    //定义model的接口
    interface RegisterModel{
        public void onRegist(String phone, String pwd, RegisterView registerView);
//        public void onSendSms();
    }

    //定义presenter的接口
    interface RegisterPresenter{
        public void onSendAuth();
        public void onRegisterClick();
    }

}
