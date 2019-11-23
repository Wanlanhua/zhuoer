package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.zhuoer.device.util.DBUtil;

public class GetTotalDao {

	
	/*
	 * 得到数据库中满足条件的总条数
	 * 接收：sql，参数list
	 * 返回 int total  代表数据库中满足条件的总条数
	 * */
	public static int selectTotal(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		int total=0;
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				total=rs.getInt("count(*)");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeDB();
		}
		return total;
	}
}
