package com.zhuoer.device.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuoer.device.util.DBUtil;

public class DeviceFittingDao {

	private static final int String = 0;
	public static List<String> selectType1(String sql, Object...params) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				list.add(rs.getString("type1"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeDB();
		}
		return list;
	}
	public static List<String> selectType2(String sql, Object...params) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				list.add(rs.getString("type2"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeDB();
		}
		return list;
	}
	public static List<String> selectDeviceFittingName(String sql, Object...params) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				list.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeDB();
		}
		return list;
	}
	public static Map<String,String> selectDeviceFittingNameToMap(String sql, Object...params) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		try {
			ResultSet rs = DBUtil.exeQuery(sql, params);
			while (rs.next()) {
				map.put(rs.getString("no"), rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.closeDB();
		}
		return map;
	}
}
