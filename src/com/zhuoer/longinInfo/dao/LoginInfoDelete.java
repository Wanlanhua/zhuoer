package com.zhuoer.longinInfo.dao;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class LoginInfoDelete {

	public boolean delete(int id, String no) {
		String sql = "delete from logininfo where id =" + id;
		try {
			new DataBaseAccess().update(sql);
			OpLogInfoTools.insertOpLogInfo(no, "员工管理=>用户信息=>删除操作:"+id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
