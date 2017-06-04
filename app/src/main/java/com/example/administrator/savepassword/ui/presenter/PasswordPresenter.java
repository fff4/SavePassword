package com.example.administrator.savepassword.ui.presenter;

import com.example.administrator.savepassword.ui.bean.Password;
import com.example.administrator.savepassword.ui.itface.PasswordInterface;
import com.example.administrator.savepassword.ui.model.PasswordModel;

/**
 *数据p层.
 */

public class PasswordPresenter implements PasswordInterface.PasswordPresenter {

    private PasswordInterface.PasswordView mPasswordView;
    private PasswordInterface.PasswordModel mPasswordModel;

    public PasswordPresenter(PasswordInterface.PasswordView passwordView) {
        mPasswordView = passwordView;
        mPasswordModel = new PasswordModel();
    }

    @Override
    public void onShowData() {
        mPasswordModel.onShowData(mPasswordView);
    }

    @Override
    public void onAddClick(Password password) {
        mPasswordModel.onAdd(password, mPasswordView);
    }

    @Override
    public void onChangeClick(Password password) {
        mPasswordModel.onChange(password, mPasswordView);
    }

    @Override
    public void onDelClick(Password password) {
        mPasswordModel.onDel(password, mPasswordView);
    }
}
