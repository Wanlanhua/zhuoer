package com.zhuoer.aJsonAPI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

public class UserPassword {

	public static String getUserPassword(String no) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select password from logininfo where no=?";
		String password = "";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				password = rs.getString("password");
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	public static boolean updateUserPassword(String no, String pwd) {
		String sql = "update logininfo set password=? where name=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, no);
			ps.setString(2, pwd);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
