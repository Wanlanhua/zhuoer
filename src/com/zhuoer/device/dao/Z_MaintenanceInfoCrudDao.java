package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zhuoer.device.entity.MaintenanceInfo;
import com.zhuoer.device.util.DBUtil;

public class Z_MaintenanceInfoCrudDao {
	/**
	 * MaintenanceInfo表添加
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	
	public static boolean insertMaintenanceInfo(String sql, List<Object> params) {
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
	 * MaintenanceInfo表查询
	 * @param sql 
	 * @param params
	 * @return  list 封装了MaintenanceInfo对象的list
	 */
	
	
	public static List<MaintenanceInfo> selectMaintenanceInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		List<MaintenanceInfo> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				MaintenanceInfo maintenanceinfo=new MaintenanceInfo();
				maintenanceinfo.setId(rs.getInt("id"));
				maintenanceinfo.setCustomerNo(rs.getString("customerNo"));
				maintenanceinfo.setContent(rs.getString("content"));
				maintenanceinfo.setStamp(rs.getString("stamp"));
				maintenanceinfo.setMark(rs.getString("mark"));
				list.add(maintenanceinfo);
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
	 * MaintenanceInfo表删除
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	public static boolean deleteMaintenanceInfo(String sql, List<Object> params) {
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
	 * MaintenanceInfo表更新
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	public static boolean updateMaintenanceInfo(String sql, List<Object> params) {
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
