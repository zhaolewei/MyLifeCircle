package com.zlw.lifequan.bean;

import java.io.Serializable;

/**
 * Created by zlw on 2017/2/6.
 */

public class VCircleBean  implements Serializable {

    private int vcircle_id;
    private String title;
    private String content;
    private String create_data;

    //数据资源
    private Type type;
    private String[] raws;//资源路径s

    //用户索引
    private int user_id;


    public VCircleBean() {

    }

    public VCircleBean(int vcircle_id, String title, String content, String create_data, Type type, String[] raws, int user_id) {
        this.vcircle_id = vcircle_id;
        this.title = title;
        this.content = content;
        this.create_data = create_data;
        this.type = type;
        this.raws = raws;
        this.user_id = user_id;
    }

    public VCircleBean(int vcircle_id, String title, String content) {
        this.vcircle_id = vcircle_id;
        this.title = title;
        this.content = content;
    }

    public int getVcircle_id() {
        return vcircle_id;
    }

    public void setVcircle_id(int vcircle_id) {
        this.vcircle_id = vcircle_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_data() {
        return create_data;
    }

    public void setCreate_data(String create_data) {
        this.create_data = create_data;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String[] getRaws() {
        return raws;
    }

    public void setRaws(String[] raws) {
        this.raws = raws;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    enum Type {
        TYPE_IMAGE, TYPE_TEXT, TEXT_GIF, TEXT_VIDEO
    }
}
