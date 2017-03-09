package com.zlw.config;

import java.sql.*;

public class Conn {

	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/db_mylife";

	public static final String DBUSER = "root";
	public static final String DBPASS = "root";

	Connection conn = null;

	public Connection getconn() {// 获取链接
		try {
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
