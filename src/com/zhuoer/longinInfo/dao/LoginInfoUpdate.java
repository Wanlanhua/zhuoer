package com.zhuoer.longinInfo.dao;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class LoginInfoUpdate {

	public boolean update(int id, String password, String no) {
		String sql = "update logininfo set password =" +password+" where id ="+id;
		try {
			new DataBaseAccess().update(sql);
			OpLogInfoTools.insertOpLogInfo(no, "员工管理=>用户信息=>更新操作:"+id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
