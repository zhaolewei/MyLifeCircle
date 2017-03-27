package com.zlw.lifequan.bean;

import java.io.Serializable;

/**
 * Created by zlw on 2017/2/6.
 */

public class VCircleCommentBean implements Serializable {

    private String comment_id;
    private String content;
    private String user_id;
    private UserInfo userInfo;
    private String create_date;

    public VCircleCommentBean() {
    }

    public VCircleCommentBean(String comment_id, String content, String user_id, UserInfo userInfo, String create_date) {
        this.comment_id = comment_id;
        this.content = content;
        this.user_id = user_id;
        this.userInfo = userInfo;
        this.create_date = create_date;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "VCircleCommentBean{" +
                "comment_id='" + comment_id + '\'' +
                ", content='" + content + '\'' +
                ", user_id='" + user_id + '\'' +
                ", userInfo=" + userInfo +
                ", create_date='" + create_date + '\'' +
                '}';
    }
}
