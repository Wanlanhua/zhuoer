package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zhuoer.device.entity.DandPInfo;
import com.zhuoer.device.util.DBUtil;

public class Z_DandPInfoCrudDao {

	/**
	 * DandPInfo表添加
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	
	public static boolean insertDandPInfo(String sql, List<Object> params) {
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
	 * DandPInfo表查询
	 * @param sql 
	 * @param params
	 * @return  list 封装了DandPInfo对象的list
	 */
	
	public static List<DandPInfo> selectDandPInfo(String sql, List<Object> params) {
		// TODO Auto-generated method stub
		List<DandPInfo> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				DandPInfo dandpinfo=new DandPInfo();
				dandpinfo.setId(rs.getInt("id"));
				dandpinfo.setDeviceNo(rs.getString("deviceNo"));
				dandpinfo.setName(rs.getString("name"));
				dandpinfo.setStamp(rs.getString("stamp"));
				dandpinfo.setMark(rs.getString("mark"));
				list.add(dandpinfo);
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
	 * DandPInfo表删除
	 * @param sql 
	 * @param params
	 * @return false 表示成功 true 表示失败
	 */
	public static boolean deleteDandPInfo(String sql, List<Object> params) {
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
