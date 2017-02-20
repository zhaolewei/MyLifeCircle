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

	// 更改用户信息
	private String jsondata;

	// 获取用户信息
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
		if (user == null || user.getUsername() == null || user.getPassword() == null) {
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

	/**
	 * 
	//	 * @param name
	//	 * @return
	//	 */
	//	public List<User> getUserList(int user_id) {
	//		org.hibernate.Transaction tx = null;
	//		String hql = "";
	//		try {
	//			Session session = HibernateUtils.getSession();
	//			tx = session.beginTransaction();
	//			hql = "from User where user_id = ?";
	//			Query query = session.createQuery(hql);
	//			query.setParameter(0, user_id);
	//			List<User> list = query.list();
	//			tx.commit();
	//			if (list.size() > 0) {
	//				return list;
	//			} else {
	//				return null;
	//			}
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//			return null;
	//		} finally {
	//			tx = null;
	//		}
	//	}
	//
	//	/**
	//	 * 
	//	 * @param name
	//	 * @return
	//	 */
	//	public User getUserById(int user_id) {
	//		org.hibernate.Transaction tx = null;
	//		String hql = "";
	//		try {
	//			Session session = HibernateUtils.getSession();
	//			tx = session.beginTransaction();
	//			hql = "from User where user_id = ?";
	//			Query query = session.createQuery(hql);
	//			query.setParameter(0, user_id);
	//			List<User> list = query.list();
	//			tx.commit();
	//			if (list.size() > 0) {
	//				return list.get(0);
	//			} else {
	//				return null;
	//			}
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//			return null;
	//		} finally {
	//			tx = null;
	//		}
	//	}
	//
	//	/**
	//	 * 保存用户信息
	//	 * 
	//	 * @return
	//	 * @throws Exception
	//	 */
	//	public String setUserInfo() throws Exception {
	//		UserDao dao = new UserDao();
	//		User user = JsonTools.fromJson(jsondata, new TypeToken<User>() {
	//		}.getType());
	//		dao.setUserinfo(user);
	//		return SUCCESS;
	//	}
	//
	//	/**
	//	 * 保存用户头像
	//	 * 
	//	 * @return
	//	 * @throws Exception
	//	 */
	//	public String setUserPhoto() throws Exception {
	//		UserDao dao = new UserDao();
	//		User user = JsonTools.fromJson(jsondata, new TypeToken<User>() {
	//		}.getType());
	//		dao.setUserPhoto(user);
	//		result = "true ：用户头像保存成功！";
	//
	//		return SUCCESS;
	//	}

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
