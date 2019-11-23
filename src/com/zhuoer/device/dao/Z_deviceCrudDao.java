package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.device.entity.DeviceInfo;
import com.zhuoer.device.util.DBUtil;

public class Z_deviceCrudDao {
	/*
	 * 表deviceInfo添加数据*/
	
	public static boolean insertDeviceInfo(String sql, List<Object> params) {
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
	
	/*
	 *表deviceInfo查询数据 */
	
	
	public static List<DeviceInfo> selectDevice(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		List<DeviceInfo> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				DeviceInfo deviceinfo=new DeviceInfo();
				deviceinfo.setId(rs.getInt("id"));
				deviceinfo.setCustomerNo(rs.getString("customerNo"));
				deviceinfo.setArea(rs.getString("area"));
				deviceinfo.setNo(rs.getString("no"));
				deviceinfo.setName(rs.getString("name"));
				deviceinfo.setType("type");
				deviceinfo.setAddress(rs.getString("address"));
				deviceinfo.setCreateDate(rs.getDate("createDate"));
				deviceinfo.setStamp(rs.getString("stamp"));
				deviceinfo.setMark(rs.getString("mark"));
				list.add(deviceinfo);
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

	
	public static boolean deleteDevice(String sql, List<Object> params) {
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

	
	public static boolean updateDevice(String sql, List<Object> params) {
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
	 * 设备号查重
	 * @param sql
	 * @param params
	 * @return false 表示重复 true 表示未重复
	 */
	public static boolean selcetRepeat(String sql,Object...params) {
		
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			if (rs.next()) {
				if (rs.getInt("count(*)")>0) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			DBUtil.closeDB();
		}
		return true;
	}
}
