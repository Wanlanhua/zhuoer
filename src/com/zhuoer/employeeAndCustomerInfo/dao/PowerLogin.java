package com.zhuoer.employeeAndCustomerInfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.employeeAndCustomerInfo.entity.Power;

public class PowerLogin {

	public static boolean isAllowLogin(String no) {
		String sql = "select * from powerinfo where no=? group by no";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Power power = new Power();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				power.setInput(rs.getString("input"));
				power.setModify(rs.getString("modify"));
				power.setAuditing(rs.getString("auditing"));
				power.setAuditing2(rs.getString("auditing2"));
				power.setDel(rs.getString("del"));
				power.setFind(rs.getString("find"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getState(power);
	}
	
	public static boolean getState(Power power) {
		boolean flag = false;
		if("1".equals(power.getAuditing())) {
			flag = true;
			return flag;
		}
		if("1".equals(power.getAuditing2())) {
			flag = true;
			return flag;
		}
		if("1".equals(power.getDel())) {
			flag = true;
			return flag;
		}
		if("1".equals(power.getFind())) {
			flag = true;
			return flag;
		}
		if("1".equals(power.getInput())) {
			flag = true;
			return flag;
		}
		if("1".equals(power.getModify())) {
			flag = true;
			return flag;
		}
		return false;
	}
	
}
