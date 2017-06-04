package com.example.administrator.savepassword.ui.itface;

import com.example.administrator.savepassword.ui.bean.Password;

import java.util.List;

/**
 * 密码保存接口
 */

public interface PasswordInterface {

    //定义view的接口---注册成功失败
    interface PasswordView{

//        public void getUserTitle();
//        public void getName();
//        public void getPwd();
//        public void getEmail();

        public void onPasswordShow(List<Password> passwords);
        public void onPasswordAdd();
        public void onPasswordError(String text);
        public void onPasswordSuccess(Password password);
        public void onPasswordDel(Password password);
        public void onPasswordChange(List<Password> password);

    }

    //定义model的接口
    interface PasswordModel{
        public void onShowData(PasswordInterface.PasswordView passwordView);
        public void onAdd(Password password, PasswordInterface.PasswordView passwordView);
        public void onChange(Password password, PasswordInterface.PasswordView passwordView);
        public void onDel(Password password, PasswordInterface.PasswordView passwordView);
    }

    //定义presenter的接口
    interface PasswordPresenter{
        public void onShowData();
        public void onAddClick(Password password);
        public void onChangeClick(Password password);
        public void onDelClick(Password password);
    }

}
