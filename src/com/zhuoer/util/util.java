package com.zhuoer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class util {
	private static final String URL="jdbc:mysql://localhost:3306/zhuoer_database?useUnicode=true&characterEncoding=UTF-8";
	private static final String USER ="root";
//	private static final String PASSWORD="3183328947";
	private static final String PASSWORD="g,2?x!8;y";
	private static Connection conn=null;
	static
	{
		 try {
			Class.forName("com.mysql.jdbc.Driver");//��������
				 conn=  DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
	public static Connection getconn()
	{
		return conn;
	}
   public static void closeConn(ResultSet rs,PreparedStatement pstmt,Connection conn)
   
   {
	if(rs!=null)
	{
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(pstmt!=null)
	{
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(conn!=null)
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   }
}
