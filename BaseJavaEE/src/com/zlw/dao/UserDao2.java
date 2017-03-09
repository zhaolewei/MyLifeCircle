package com.zlw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.zlw.bean.User;
import com.zlw.config.Conn;
import com.zlw.utils.DateHelper;
import com.zlw.utils.HibernateUtils;
import com.zlw.utils.JsonTools;

public class UserDao2 {

	Conn c = new Conn();
	Connection conn = c.getconn();

	public String getDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println("data:" + df.format(new Date()));
		return df.format(new Date());
	}

	// 登陆部分===================================================================
	/**
	 * 获取密码 By Id
	 * 
	 * @param id
	 * @return
	 */
	public String getPasswordById(int user_id) {
		String sql_select = "select password from user_info where user_id='" + user_id + "'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		String password = null;
		try {
			pst = conn.prepareStatement(sql_select);
			rs = pst.executeQuery();
			rs.next();
			password = rs.getString("password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}

	/**
	 * 获取id 通过name
	 * 
	 * @param name
	 * @return
	 */
	public int getIdByName(String username) {
		String sql_select = "select user_id from user_info where username='" + username + "'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		int id = 0;
		try {
			pst = conn.prepareStatement(sql_select);
			rs = pst.executeQuery();
			rs.next();
			id = rs.getInt("user_id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 登陆验证
	 * 
	 * @return boolean
	 */
	public boolean isLogin(String username, String password) {
		if (password == null) {
			return false;
		}
		org.hibernate.Transaction tx = null;
		String hql = "";
		try {
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			hql = "from User where username = ? and password =?";
			Query query = session.createQuery(hql);
			query.setParameter(0, username);
			query.setParameter(1, password);
			List<User> list = query.list();
			tx.commit();
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			tx = null;
		}
	}

	/**
	 * 添加新的评论
	 * 
	 * @param name
	 * @return
	 */
	public boolean regiestUser(User user) {
		System.out.println("jsondata: " + JsonTools.toJson(user));
		try {
			user.setDate(DateHelper.getDateStr());
			user.setAddress(null);
			user.setEmail(null);
			user.setPhonenum(null);
			user.setState(null);

			Session session = HibernateUtils.getSession();
			session.save(user);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @return boolean
	 */
	public boolean haveUsername(String username) {
		if (username == null) {
			return false;
		}
		org.hibernate.Transaction tx = null;
		String hql = "";
		try {
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			hql = "from User where username = ? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, username);
			List<User> list = query.list();
			tx.commit();
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			tx = null;
		}
	}

	// 查询部分---------------------

	/**
	 * 查询某个用户 ById
	 * 
	 * @return
	 */
	public User getUserById(int user_id) {
		org.hibernate.Transaction tx = null;
		String hql = "";
		try {
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			hql = "from User where user_id = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, user_id);
			List<User> list = query.list();
			tx.commit();
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			tx = null;
		}
	}

	/**
	 * 查询某个用户 ByUsername
	 * 
	 * @return
	 */
	public User getUserByUsername(String username) {
		org.hibernate.Transaction tx = null;
		String hql = "";
		try {
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			hql = "from User where username = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, username);
			List<User> list = query.list();
			tx.commit();
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			tx = null;
		}
	}

	/**
	 * 验证用户名是否重复-注册时 --true : 数据库中有该名称
	 * 
	 * @return
	 */
	public boolean isName(String username) {
		org.hibernate.Transaction tx = null;
		String hql = "";
		try {
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			hql = "from User where username = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, username);
			List<User> list = query.list();
			tx.commit();
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			tx = null;
		}

	}

	// 数据更新部分--------------------------------------------

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean setPassword(int user_id, String password) {
		String sql_select = "update  user_info set password= '" + password + "' where user_id='" + user_id + "'";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_select);
			pst.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	/**
	 * 修改头像
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean setPhoto(int user_id, String newpath) {
		String sql_select = "update  user_info set user_photo= '" + newpath + "' where user_id='" + user_id + "'";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_select);
			pst.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	/**
	 * 修改基础信息
	 * 
	 */
	public void setUserinfo(User user) {

		String sql_select = "UPDATE `alibaba`.`user_info` SET `username`='" + user.getUsername() + "', `address`='"
				+ user.getAddress() + "', `phonenum`='" + user.getPhonenum() + "', `email`='" + user.getEmail()
				+ "' where user_id='" + user.getUser_id() + "'";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_select);
			pst.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改用户头像信息
	 * 
	 * user_id,photo_url
	 * 
	 */
	public void setUserPhoto(User user) {

		if (user.getUser_id() == 0) {
			return;
		}
		String sql_select = "UPDATE `alibaba`.`user_info` SET `user_photo`='" + user.getUser_photo()
				+ "' where user_id='" + user.getUser_id() + "'";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_select);
			pst.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查询某个用户 ByUsername 权限修改用户名称（username）
	 * 
	 * @return
	 */
	public void saveUserI(User user) {
		try {
			Session session = HibernateUtils.getSession();

			User newUser = this.getUserById(user.getUser_id());
			newUser.setUsername(user.getUsername());
			session.save(newUser);

			session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}
}
