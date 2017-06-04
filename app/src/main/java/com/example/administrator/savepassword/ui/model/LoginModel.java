package com.example.administrator.savepassword.ui.model;

import android.content.Context;

import com.example.administrator.savepassword.ui.bean.User;
import com.example.administrator.savepassword.ui.dao.ORMDao;
import com.example.administrator.savepassword.ui.itface.LoginInterface;
import com.example.administrator.savepassword.ui.utils.SPUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 登录页面的数据处理层
 */

public class LoginModel implements LoginInterface.LoginModel {

    @Override
    public void onLogin(String phone, String pwd, LoginInterface.LoginView loginView) {

        ORMDao<User> ormDao = new ORMDao<>((Context) loginView);
        HashMap<String, String> mapData = new HashMap<>();
        mapData.put("phone", phone);
        mapData.put("userPassword", pwd);
        List<User> users = ormDao.queryData(mapData, User.class);
        if (users == null || users.size() <= 0) {
            loginView.onLoginError("账号或密码错误");
            return;
        }

        //持久化用户登录信息
        SPUtils spUtils = new SPUtils();
        spUtils.saveData(spUtils.USER_FILE, (Context) loginView, spUtils.USER_PHONE_KEY, phone);

        loginView.onLoginSuccess();
    }
}
