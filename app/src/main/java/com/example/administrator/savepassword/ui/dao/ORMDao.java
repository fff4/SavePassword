package com.example.administrator.savepassword.ui.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库操作分装
 */

public class ORMDao<T> {


    private Context mContext;
    private final SQLiteDatabase mDb;
    private final AndroidDatabaseConnection mConnection;
    private final UserOMLiteHelper mMUserOMLiteHelper;

    public ORMDao(Context context) {
        mContext = context;
        mMUserOMLiteHelper = new UserOMLiteHelper(mContext);
        mDb = mMUserOMLiteHelper.getWritableDatabase();
        //操作数据库需要connection，有连接才能进行CRUD
//        AndroidConnectionSource
        mConnection = new AndroidDatabaseConnection(mDb, true);
    }

    /**
     *新增数据操作
     * @param t         带数据的bean对象
     * @param claz      bean的class
     * @return
     */
    public boolean addData(T t, Class<T> claz) {
        //创建保存点--事物的开启与关闭
        Savepoint savepoint = null;
        try {
            //创建保存点
            savepoint = mConnection.setSavePoint("start");
            //创建数据表格类对象，操作表格
            Dao<T, Integer> dao = mMUserOMLiteHelper.getDao(claz);
            //新增数据
            dao.create(t);
            //提交创建的表格数据
            mConnection.commit(savepoint);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mConnection.rollback(savepoint);
                return false;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    /**
     *修改数据操作
     * @param t         带数据的bean对象
     * @param claz      bean的class
     * @return
     */
    public boolean updateData(T t, Class<T> claz) {
        //创建保存点--事物的开启与关闭
        Savepoint savepoint = null;
        try {
            //创建保存点
            savepoint = mConnection.setSavePoint("start");
            //创建数据表格类对象，操作表格
            Dao dao = mMUserOMLiteHelper.getDao(claz);
            //新增数据
            dao.update(t);
            //提交创建的表格数据
            mConnection.commit(savepoint);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mConnection.rollback(savepoint);
                return false;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 查询数据操作
     * @param phone         带数据的bean对象的phone值
     * @param claz      bean的class
     * @return
     */
    public List<T> queryData(HashMap<String, String> phone, Class<T> claz) {
        List<T> uList = null;
        //创建保存点--事物的开启与关闭
        Savepoint savepoint = null;
        try {
            //创建保存点
            savepoint = mConnection.setSavePoint("start");
            //创建数据表格类对象，操作表格
            Dao dao = mMUserOMLiteHelper.getDao(claz);
            //数据
            uList = dao.queryForFieldValues(phone);
            //提交创建的表格数据
            mConnection.commit(savepoint);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mConnection.rollback(savepoint);
                return null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return uList;
    }


    /**
     * 删除数据操作
     * @param t         带数据的bean对象的phone值
     * @param claz      bean的class
     * @return
     */
    public boolean delData(T t, Class<T> claz) {
        //创建保存点--事物的开启与关闭
        Savepoint savepoint = null;
        try {
            //创建保存点
            savepoint = mConnection.setSavePoint("start");
            //创建数据表格类对象，操作表格
            Dao<T, Integer> dao = mMUserOMLiteHelper.getDao(claz);
            //新增数据
            dao.delete(t);
            //提交创建的表格数据
            mConnection.commit(savepoint);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mConnection.rollback(savepoint);
                return false;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 查询所有数据操作
     * @param claz      bean的class
     * @return
     */
    public List<T> queryAll(Class<T> claz) {
        List<T> uList = null;
        //创建保存点--事物的开启与关闭
        Savepoint savepoint = null;
        try {
            //创建保存点
            savepoint = mConnection.setSavePoint("start");
            //创建数据表格类对象，操作表格
            Dao dao = mMUserOMLiteHelper.getDao(claz);
            //数据
            uList = dao.queryForAll();
            //提交创建的表格数据
            mConnection.commit(savepoint);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                mConnection.rollback(savepoint);
                return null;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return uList;
    }

}
