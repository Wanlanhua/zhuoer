package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zhuoer.device.entity.DeviceFittingInfo;
import com.zhuoer.device.util.DBUtil;

public class Z_DeviceFittingInfoCrudDao {
	/**
	 * DeviceFittingInfo表添加
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	
	public static boolean insertDeviceFittingInfo(String sql, List<Object> params) {
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
	 * 配件号查重
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
	
	/**
	 * DeviceFittingInfo表查询
	 * @param sql 
	 * @param params
	 * @return list 封装了DeviceFittingInfo对象的list
	 */
	
	
	public static List<DeviceFittingInfo> selectDeviceFittingInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		List<DeviceFittingInfo> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				DeviceFittingInfo devicefittinginfo=new DeviceFittingInfo();
				devicefittinginfo.setId(rs.getInt("id"));
				devicefittinginfo.setCustomerNo(rs.getString("customerNo"));
				devicefittinginfo.setNo(rs.getString("no"));
				devicefittinginfo.setType1(rs.getString("type1"));
				devicefittinginfo.setType2(rs.getString("type2"));
				devicefittinginfo.setType(rs.getString("type"));
				devicefittinginfo.setName(rs.getString("name"));
				devicefittinginfo.setQty(rs.getString("qty"));
				devicefittinginfo.setManufactor(rs.getString("manufactor"));
				devicefittinginfo.setStamp(rs.getString("stamp"));
				devicefittinginfo.setMark(rs.getString("mark"));
				list.add(devicefittinginfo);
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
	 * DeviceFittingInfo表删除
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
		}
		return flag;
	}

	
	/**
	 * DeviceFittingInfo表更新
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	public static boolean updateDeviceFittingInfo(String sql, List<Object> params) {
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
