package com.example.administrator.savepassword.ui.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 保存密码账户信息密码
 */
@DatabaseTable(tableName = "user_zh")
public class Password {
    @DatabaseField(generatedId = true)
    private int user_id;
    @DatabaseField(columnName = "user_name")
    private String user_name;
    @DatabaseField(columnName = "user_pwd")
    private String user_pwd;
    @DatabaseField(columnName = "user_email")
    private String user_email;
    @DatabaseField(columnName = "user_phone")
    private String user_phone;
    @DatabaseField(columnName = "user_title")
    private String user_title;

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}
