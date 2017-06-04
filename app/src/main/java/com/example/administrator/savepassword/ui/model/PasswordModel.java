package com.example.administrator.savepassword.ui.model;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.example.administrator.savepassword.ui.bean.Password;
import com.example.administrator.savepassword.ui.dao.ORMDao;
import com.example.administrator.savepassword.ui.itface.PasswordInterface;
import com.example.administrator.savepassword.ui.utils.SMSUtil;

import java.util.List;

/**
 * 数据model层
 */

public class PasswordModel implements PasswordInterface.PasswordModel {
    private static final String TAG = "PasswordModel";
    @Override
    public void onShowData(PasswordInterface.PasswordView passwordView) {

        ORMDao<Password> ormDao = new ORMDao<>((Context) passwordView);
        List<Password> passwords = ormDao.queryAll(Password.class);
        passwordView.onPasswordShow(passwords);
    }

    //新增数据到数据库表
    @Override
    public void onAdd(Password password, PasswordInterface.PasswordView passwordView) {

        if (checkout(password, passwordView)) return;

        ORMDao<Password> ormDao = new ORMDao<>((Context) passwordView);
        boolean isAdd = ormDao.addData(password, Password.class);
        if (isAdd) {
            passwordView.onPasswordSuccess(password);
            return;
        }
        passwordView.onPasswordError("保存失败");
    }

    //修改保存的数据
    @Override
    public void onChange(Password password, PasswordInterface.PasswordView passwordView) {

        if (checkout(password, passwordView)) return;

        ORMDao<Password> ormDao = new ORMDao<>((Context) passwordView);
        boolean isAdd = ormDao.updateData(password, Password.class);
        List<Password> passwords = ormDao.queryAll(Password.class);
        if (isAdd) {
            passwordView.onPasswordChange(passwords);
            return;
        }
        passwordView.onPasswordError("修改失败");
    }

    @Override
    public void onDel(Password password, PasswordInterface.PasswordView passwordView) {
        if (checkout(password, passwordView)) return;

        ORMDao<Password> ormDao = new ORMDao<>((Context) passwordView);
        boolean isDel = ormDao.delData(password, Password.class);
        if (isDel) {
            passwordView.onPasswordDel(password);
            return;
        }
        passwordView.onPasswordError("删除失败");
    }

    /**
     * 校验账号、密码、电话
     * @param password
     * @param passwordView
     * @return
     */
    private boolean checkout(Password password, PasswordInterface.PasswordView passwordView) {
        String user_pwd = password.getUser_pwd();
        String user_title = password.getUser_title();
        if (TextUtils.isEmpty(user_pwd) || TextUtils.isEmpty(user_title)) {
            passwordView.onPasswordError("密码或标题不能为空");
            return true;
        }

        String user_phone = password.getUser_phone();
        if (!TextUtils.isEmpty(user_phone) && !SMSUtil.judgePhoneNums((Activity) passwordView, user_phone)) {
            return true;
        }
        return false;
    }

}
