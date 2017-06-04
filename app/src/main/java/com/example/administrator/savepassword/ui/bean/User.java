package com.example.administrator.savepassword.ui.bean;

/**
 * 用户bean--用户的账号密码.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "p_user")
public class User {
    @DatabaseField(id = true) //此行是特殊的，是id;如果是id = true（手动id);
    // generatedId = true(自动id), 类似auto icrement效果
    private int id;
    @DatabaseField(columnName = "phone")
    private String phone;
    @DatabaseField(columnName = "userPassword")
    private String userPassword;

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
