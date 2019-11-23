package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zhuoer.device.entity.DandMInfo;
import com.zhuoer.device.util.DBUtil;

public class Z_DandMInfoCrudDao {

	
	/**
	 * DandMInfo表添加
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	
	public static boolean insertDandMInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try {
			flag=DBUtil.executeUpdate(sql,params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeDB();
		}
		return flag;
	}
	
	
	/**
	 * DandMInfo表查询
	 * @param sql 
	 * @param params
	 * @return  list 封装了DeviceInfo对象的list
	 */
	
	
	public static List<DandMInfo> selectDandMInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		List<DandMInfo> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				DandMInfo dandminfo=new DandMInfo();
				dandminfo.setId(rs.getInt("id"));
				dandminfo.setDeviceNo(rs.getString("deviceNo"));
				dandminfo.setName(rs.getString("name"));
				dandminfo.setStamp(rs.getDate("stamp"));
				dandminfo.setMark(rs.getString("mark"));
				list.add(dandminfo);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeDB();
		}
		return list;
	}
	
	
	
	/**
	 * DandMInfo表删除
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	public static boolean deleteDandMInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try {
			flag=DBUtil.executeUpdate(sql,params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeDB();
		}
		return flag;
	}

}
