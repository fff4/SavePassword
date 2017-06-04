package com.example.administrator.savepassword.ui.model;

import android.content.Context;

import com.example.administrator.savepassword.ui.app.PassworldApplication;
import com.example.administrator.savepassword.ui.bean.User;
import com.example.administrator.savepassword.ui.dao.ORMDao;
import com.example.administrator.savepassword.ui.itface.RegisterInterface;

import java.util.HashMap;
import java.util.List;

/**
 * 注册页面的model层
 */

public class RegistModel implements RegisterInterface.RegisterModel {

    //注册功能细节实现
    @Override
    public void onRegist(String phone, String pwd, RegisterInterface.RegisterView registerView) {

        //注册账号，登录保存账号
        //保存账号密码到内存
        User mUser = PassworldApplication.mUser;
        mUser.setUserPassword(pwd);
        mUser.setPhone(phone);

        ORMDao<User> ormDao = new ORMDao<>((Context) registerView);
        //先查询
        HashMap<String, String> byphone = new HashMap<String, String>();
        byphone.put("phone", phone);
        List<User> users = ormDao.queryData(byphone, User.class);
        if (users != null && users.size() > 0) {
            registerView.onRegisterError("账户已存在");
            return;
        }

        //保存数据到数据库
        boolean isAdd = ormDao.addData(mUser, User.class);

        if (isAdd) {
            registerView.onRegisterSuccess();
        } else {
            registerView.onRegisterError("注册失败");
        }
    }

}
