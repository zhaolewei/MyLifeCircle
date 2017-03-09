package com.zlw.action;

import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import com.zlw.bean.User;
import com.zlw.dao.UserDao2;
import com.zlw.utils.JsonTools;

public class UserAction extends ActionSupport {
	private String username;
	private String password;
	private String result = "flase";
	private String jsondata;
	private int user_id;

	public String islogin() throws Exception {
		UserDao2 dao = new UserDao2();

		if (dao.isLogin(username, password)) {
			result = "true";
		} else {
			result = "flase";
		}
		return SUCCESS;
	}

	public String register() throws Exception {
		UserDao2 dao = new UserDao2();
		System.out.println("jsondata: " + jsondata);
		User user = JsonTools.fromJson(jsondata, new TypeToken<User>() {
		}.getType());
		if (user == null || user.getUsername() == null) {
			result = "flase";
			return SUCCESS;
		}
		if (dao.haveUsername(user.getUsername())) {
			result = "flase";
			return SUCCESS;
		}
		if (dao.regiestUser(user)) {
			result = "true";
		} else {
			result = "flase";
		}
		return SUCCESS;
	}

	public String updataUser() throws Exception {
		UserDao2 dao = new UserDao2();
		User user = JsonTools.fromJson(jsondata, new TypeToken<User>() {
		}.getType());
		if (user == null || user.getUsername() == null || user.getPaw() == null) {
			result = "flase";
			return SUCCESS;
		}
		if (dao.regiestUser(user)) {
			result = "true";
		} else {
			result = "flase";
		}
		return SUCCESS;
	}

	// --------------get()&& set()-------------------
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

}
