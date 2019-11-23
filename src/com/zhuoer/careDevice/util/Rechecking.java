package com.zhuoer.careDevice.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

public class Rechecking {

	
	public static boolean getRecheck(String no) {
		
		String sql = "select c.no,e.no from customerinfo c, employeeinfo e where c.no =? or e.no=?";
		
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, no);
			ps.setString(2, no);
			rs = ps.executeQuery();
			int rows = rs.getRow();
			if(rows != 0) {
				ps.close();
				rs.close();
				return false;
			}
			else {
				ps.close();
				rs.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
