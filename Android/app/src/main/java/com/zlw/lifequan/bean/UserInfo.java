package com.zlw.lifequan.bean;

import java.io.Serializable;

/**
 * Created by zlw on 2017/2/22.
 */

public class UserInfo  implements Serializable {

    private String username;
    private String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
