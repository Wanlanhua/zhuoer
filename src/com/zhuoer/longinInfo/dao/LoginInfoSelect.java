package com.zhuoer.longinInfo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.longinInfo.entity.LoginInfo;

public class LoginInfoSelect {

	public List<LoginInfo> select(String name,int page, String no) {
		int start = (page-1)*15, end = 15;
		String sql = "";
		if(no.equals("admin")) {
			sql = "select a.* from logininfo a, employeeinfo b where a.name like '%"+name+"%' and a.name = b.no order by id desc limit "+start+","+end;
		} else {
			sql = "select a.* from logininfo a, employeeinfo b where a.name like '%"+name+"%' and a.name = b.no and b.department in (select department from employeeinfo where no = '"+no+"') order by id desc limit "+start+","+end;
		}
		ResultSet rs;
		List<LoginInfo> lList = new ArrayList<LoginInfo>();
		try {
			rs = new DataBaseAccess().query(sql);
			if(rs!=null) 
				while(rs.next()) {
					LoginInfo li = new LoginInfo();
					li.setId(rs.getInt("id"));
					li.setName(rs.getString("name"));
					li.setRole(rs.getString("role"));
					li.setPassword(rs.getString("password"));
					li.setMark(rs.getString("mark"));
					lList.add(li);
				}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lList;
	}
	
}
