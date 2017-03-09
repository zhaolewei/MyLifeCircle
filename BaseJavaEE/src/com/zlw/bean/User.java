package com.zlw.bean;

public class User {
	private Integer user_id; // 用户Id
	private String username;// 用户名
	private String paw;// 密码
	private String state;// 用户状态
	private String date;// 用户创建时间
	private String address;// 收货地址
	private String phonenum; // 电话
	private String email; // 邮箱
	private String user_photo;// 头像地址

	// private Set<Discuss> discussSet = new HashSet<Discuss>();; // 用户评论
	//
	// private Set<Square> suqareSet = new HashSet<Square>(); // 个人广场信息

	// public Set<Discuss> getDiscussSet() {
	// return discussSet;
	// }
	//
	// public void setDiscussSet(Set<Discuss> discussSet) {
	// this.discussSet = discussSet;
	// }

	public User() {
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPaw() {
		return paw;
	}

	public void setPaw(String paw) {
		this.paw = paw;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public User(Integer user_id, String username, String paw, String state, String date, String address,
			String phonenum, String email, String user_photo) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.paw = paw;
		this.state = state;
		this.date = date;
		this.address = address;
		this.phonenum = phonenum;
		this.email = email;
		this.user_photo = user_photo;
	}

	// public Set<Square> getSuqareSet() {
	// return suqareSet;
	// }
	//
	// public void setSuqareSet(Set<Square> suqareSet) {
	// this.suqareSet = suqareSet;
	// }

	// public User(Integer user_id, String username, String password,
	// String state, String date, String address, String phonenum,
	// String email, String user_photo, Set<Discuss> discussSet,
	// Set<Square> suqareSet) {
	// super();
	// this.user_id = user_id;
	// this.username = username;
	// this.password = password;
	// this.state = state;
	// this.date = date;
	// this.address = address;
	// this.phonenum = phonenum;
	// this.email = email;
	// this.user_photo = user_photo;
	// this.discussSet = discussSet;
	// this.suqareSet = suqareSet;
	// }

}
