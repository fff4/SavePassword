package com.example.administrator.savepassword.ui.app;

import android.app.Application;

import com.example.administrator.savepassword.ui.bean.User;

/**
 * compile name: 'SMSSDK-2.1.4', ext: 'aar'
 */

public class PassworldApplication extends Application {

    public static User mUser;

    @Override
    public void onCreate() {
        super.onCreate();
        mUser = new User();
    }
}
