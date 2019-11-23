package com.zhuoer.device.util;
import java.sql.*;
import java.util.List;

public class DBUtil {
	// 表示定义数据库的用户名
	private static String USERNAME = "root";
	// 定义数据库的密码
//	private static String PASSWORD = "123456";
	private static String PASSWORD = "3183328947";
	// 定义数据库的驱动信息
	private static String DRIVER = "com.mysql.jdbc.Driver";
	// 定义访问数据库的地址
	private static String URL = "jdbc:mysql://localhost:3306/zhuoer_database?useUnicode=true&characterEncoding=UTF-8";
	private static Connection con = null;
	// 定义sql语句的执行对象
	private static PreparedStatement pstmt = null;
	//返回集合rs
	private static ResultSet rs = null;
	//构造方法
	public DBUtil() {
		
	}
	static {
		try {
			getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获得数据库的连接
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public static void getConnection() throws SQLException {
		if(con==null||con.isClosed()) {
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 完成对数据库的表的添加删除和修改的操作
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static boolean executeUpdate(String sql, Object... params)
			throws SQLException {
		if (con==null||con.isClosed()) {
			getConnection();
		}
		boolean flag = false;
		int result = -1;  // 表示当用户执行添加删除和修改的时候所影响数据库的行数
		pstmt = con.prepareStatement(sql);
		if (params != null) {
			int index = 1;
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(index++, i);
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}
	
	/**
	 * 完成对数据库的表的添加删除和修改的操作
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static boolean executeUpdate(String sql, List<Object> params)
			throws SQLException {
		
		if (con==null||con.isClosed()) {
			getConnection();
		}
		boolean flag = false;
		int result = -1;  // 表示当用户执行添加删除和修改的时候所影响数据库的行数
		
		pstmt = (PreparedStatement) con.prepareStatement(sql);
		
		if (params != null && !params.isEmpty()) {
			int index = 1;
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		pstmt.close();
		return flag;
	}
	
	public static ResultSet exeQuery(String sql,List<Object> params) throws SQLException {
		ResultSet rs;
		int index=1;
		if (con==null||con.isClosed()) {
			getConnection();
		}
		pstmt = (PreparedStatement) con.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		rs = pstmt.executeQuery();
		return rs;
	}
	
	/**
	 * 查询数据
	 * @param sql
	 * @param params
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet exeQuery(String sql,Object... params) throws SQLException {
		if (con==null||con.isClosed()) {
			getConnection();
		}
		int index=1;
		pstmt = con.prepareStatement(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(index++, params[i]);
			}
		}
		if(pstmt != null && sql != null&& con!=null){
//			if (pstmt.isClosed()) {
//				System.out.println("close");
//			}
			rs = pstmt.executeQuery();
		}else {
			getConnection();
			rs = pstmt.executeQuery();
		}
		return rs;
	}
	/**
	 * sql语句查询返回rs
	 * @param sql 
	 * @return ResultSet
	 * @throws SQLException 
	 */
	public static ResultSet exeQuery(String sql) throws SQLException {
		if (con==null||con.isClosed()) {
			getConnection();
		}
		try {
			Statement stmt=con.createStatement();//创建一个Statement对象
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//执行查询，把查询结果赋值给结果集对象
		return rs;
	}
	/**
	 * 工具类，把数据库内表数据总条数返回
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	
	public static int selectPage(String sql,Object... params) throws SQLException {
		int page = 0;
		if (con==null||con.isClosed()) {
			getConnection();
		}
		rs = exeQuery(sql, params);
		if(rs.next())
		{
			page = rs.getInt("count(*)");
		}
		rs.close();
		return page;
	}
	
	/**
	 * 关闭数据库连接
	 */
	public static void closeDB() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		if (con != null) {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
