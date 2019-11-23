package com.zhuoer.newCareDevice.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

public class GetPageSize {

	public static int getPageSize(String departmentid, String shebei, String tableName) {
		String sql = "select count(*) from "+tableName+" where shebei in (select no from deviceinfo where customerno="+departmentid
				+" and no like '%"+shebei+"%')";
		ResultSet rs = null;
		int count = 0;
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public static int getPageSize(String shebei, String tableName, String departmentid, String starttime, String endtime) {
		starttime = starttime.trim()+" 00:00:00";
		endtime = endtime.trim() + " 23:59:59";
		String sql = "select count(*) from "+tableName+" where shebei in (select no from deviceinfo where customerno='"+departmentid
				+"' and no like '%"+shebei+"%') and datetime >='"+starttime+"' and datetime<='"+endtime+"'";
		ResultSet rs = null;
		int count = 0;
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				count = rs.getInt("count(*)");
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
}
