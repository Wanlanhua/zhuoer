package com.zhuoer.aJsonAPI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.aJsonAPI.Util.ToJson;

public class GetDepartment {
	/**
	 * 获取部门编号
	 * @author purple
	 * @method getDepartment
	 * @retrun List<String>
	 * @date 2018年7月1日
	 */
	public static List<String> getDepartment(String no) {
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		if(no.equals("admin")) {
			sql = "select distinct department from employeeinfo";
			try {
				ps = new DataBaseAccess().getPreparedStatement(sql);
				rs = ps.executeQuery();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			sql = "select department from employeeinfo where no = ?";
			try {
				ps = new DataBaseAccess().getPreparedStatement(sql);
				ps.setString(1, no);
				rs = ps.executeQuery();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			while(rs.next()) {
				list.add(rs.getString("department"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getEmployeeinfo(List<String> list) {
		List<String> elist = new ArrayList<String>();
		if(list.size()>0) {
			String departmentid = list.get(0);
			PreparedStatement ps;
			String sql = "select no from employeeinfo where department=?";
			try {
				ps = new DataBaseAccess().getPreparedStatement(sql);
				ps.setString(1, departmentid);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					elist.add(rs.getString("no"));
				}
				rs.close();
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ToJson.toJson(elist);
	}
	
}
