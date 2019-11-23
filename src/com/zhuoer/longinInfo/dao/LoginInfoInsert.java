package com.zhuoer.longinInfo.dao;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.longinInfo.entity.LoginInfo;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class LoginInfoInsert {

	public boolean insert(LoginInfo li, String no) {
		String sql = "insert into logininfo (name,role,password,mark) values('" +
				li.getName()+"','"+li.getRole()+"','"+li.getPassword()+"','"+li.getMark()+"')";
		try {
			new DataBaseAccess().update(sql);
			OpLogInfoTools.insertOpLogInfo(no, "员工管理=>用户信息=>添加操作");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
