package com.zhuoer.careDevice.util;

import java.sql.ResultSet;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

public class GetPageSize {

	public static int getPageSize(String tableName, int role, String customerNo, String qname) {
		String sql = "select count(id) from "+tableName;
		if(role ==0 ) {
			sql += " where customerNo = " + customerNo +" and state = 1";
		}
		ResultSet rs = null;
		int pageSize = 0;
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				pageSize = rs.getInt("count(id)");
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageSize;
	}
	
	public static int getPageSize(String tableName) {
		String sql = "select count(id) from "+tableName;
		ResultSet rs = null;
		int pageSize = 0;
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				pageSize = rs.getInt("count(id)");
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageSize;
	}
	
}
