package com.zlw.lifequan.bean;

import java.io.Serializable;

/**
 * Created by zlw on 2017/2/22.
 */

public class UserInfo implements Serializable {

    private int user_id;
    private String username;
    private String password;
    private String headphoto;
    private String create_date;
    private String type;
    private String sex;
    private String birthday;
    private String local;

    public UserInfo() {
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo(int user_id, String username, String password, String headphoto, String create_date, String type, String sex, String birthday, String local) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.headphoto = headphoto;
        this.create_date = create_date;
        this.type = type;
        this.sex = sex;
        this.birthday = birthday;
        this.local = local;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", headphoto='" + headphoto + '\'' +
                ", sex='" + sex + '\'' +
                ", create_date='" + create_date + '\'' +
                ", type='" + type + '\'' +
                ", birthday='" + birthday + '\'' +
                ", local='" + local + '\'' +
                '}';
    }
}
