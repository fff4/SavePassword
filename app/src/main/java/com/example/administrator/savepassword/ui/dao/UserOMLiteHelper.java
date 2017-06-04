package com.example.administrator.savepassword.ui.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.savepassword.ui.bean.Password;
import com.example.administrator.savepassword.ui.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * 创建数据库
 */

public class UserOMLiteHelper extends OrmLiteSqliteOpenHelper {

    public UserOMLiteHelper(Context context) {
        super(context, "pwdmng.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Password.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
