package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zhuoer.device.entity.DandFInfo;
import com.zhuoer.device.util.DBUtil;

public class Z_DandFInfoCrudDao {
	
	/**
	 * DandFInfo表添加
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	public static boolean insertDandFInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag=false;
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
	 * DandFInfo表查询
	 * @param sql 
	 * @param params
	 * @return  list 封装了DeviceInfo对象的list
	 */
	public static List<DandFInfo> selectDandFInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		List<DandFInfo> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				DandFInfo dandfinfo=new DandFInfo();
				dandfinfo.setId(rs.getInt("id"));
				dandfinfo.setDeviceNo(rs.getString("deviceNo"));
				dandfinfo.setNo(rs.getString("no"));
				dandfinfo.setMark(rs.getString("mark"));
				dandfinfo.setPartName(rs.getString("partname"));
				list.add(dandfinfo);
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
	 * DandFInfo表删除
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	public static boolean deleteDandFInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag=false;
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
	public static boolean selectDeviceFittingOnly(String sql, Object... params) {
		boolean flag = false;
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			if (rs.next()) {
				int count = rs.getInt("count(*)");
				if (count>0) {
					flag=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeDB();
		}
		return flag;
	}
}
