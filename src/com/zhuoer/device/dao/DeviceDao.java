package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.device.util.DBUtil;

public class DeviceDao {
/**
 * 二级联动dao
 * @param sql 		sql语句
 * @param params 	sql中的占位符
 * @return			返回查询的no name
 */
	public static List<List<String>> selectAreaList(String sql,Object... params) {
		// TODO Auto-generated method stub
		List<List<String>> list = new ArrayList<>();
		List<String> no= new ArrayList<>();
		List<String> name= new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql,params);
			while(rs.next()) {
				no.add(rs.getString("no"));
				name.add(rs.getString("name"));
			}
			list.add(no);
			list.add(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeDB();
		}
		return list;
	}

	public static List<String> selectDeviceNoList(String sql, Object... params) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				list.add(rs.getString("no"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBUtil.closeDB();
		}
		return list;
	}
	public static List<String> selectDeviceNameList(String sql, Object... params) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				list.add(rs.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBUtil.closeDB();
		}
		return list;
	}

}
