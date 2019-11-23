package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zhuoer.device.entity.DevicePartInfo;
import com.zhuoer.device.util.DBUtil;

public class Z_devicePartInfoCrudDao {
	/**
	 * DevicePartInfo表添加
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	
	public static boolean insertDevicePartInfo(String sql, List<Object> params) {
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
	 * DevicePartInfo表查询
	 * @param sql 
	 * @param params
	 * @return  list 封装了DevicePartInfo对象的list
	 */
	
	
	public static List<DevicePartInfo> selectDevicePartInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		List<DevicePartInfo> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				DevicePartInfo devicepartInfo=new DevicePartInfo();
				devicepartInfo.setId(rs.getInt("id"));
				devicepartInfo.setCustomerNo(rs.getString("customerNo"));
				devicepartInfo.setName(rs.getString("name"));
				devicepartInfo.setStamp(rs.getString("stamp"));
				devicepartInfo.setMark(rs.getString("mark"));
				list.add(devicepartInfo);
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
	 * DevicePartInfo表删除
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	public static boolean deleteDevicePartInfo(String sql, List<Object> params) {
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
	 * DevicePartInfo表更新
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	
	public static boolean updateDevicePartInfo(String sql, List<Object> params) {
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
