package com.example.administrator.savepassword.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * sp 保存数据
 */

public class SPUtils {
    public static final String USER_FILE = "user";
    public static final String USER_PHONE_KEY = "phone";

    /**
     * 保存信息到本地持久化
     * @param context
     * @param key
     * @param date
     */
    public void saveData(String file, Context context, String key, String date){
        SharedPreferences sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        sp.edit().putString(key, date).commit();
    }

    public String getData(String file, Context context, String key, String date){
        SharedPreferences sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        return sp.getString(key, date);
    }

}
