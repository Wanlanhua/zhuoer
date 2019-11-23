package com.zhuoer.qmaintance.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTools {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/show?useUnicode=true&characterEncoding=UTF-8";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASS = "3183328947";
//	private static final String PASS = "g,2?x!8;y";
	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
