package com.zhuoer.newCareDevice.service.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

public class GetJsonShebei {

	public static String getDepartment(String no) {
		ResultSet rs = null;
		DataBaseAccess db = new DataBaseAccess();
		String sql = "";
		String content = "";
		if("admin".equals(no)) {
			sql = "select department from employeeinfo";
		}
		else {
			sql = "select department from employeeinfo where no='"+no+"'";
		}
		try {
			rs = db.query(sql);
			while(rs.next()) {
				content += ","+rs.getString("department");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(content.length()>0) {
			content = content.substring(1, content.length());
		}
		return content;
	}
	
	public static String getJsonShebei(String no) {
		String sql = "select no from deviceinfo where customerNo in("+getDepartment(no)+")";
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				list.add(rs.getString("no"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return GS.ToJson(list);
	}
	
}
