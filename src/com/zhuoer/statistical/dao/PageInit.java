package com.zhuoer.statistical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import com.zhuoer.DataBaseAccess.DataBaseAccess;

public class PageInit {


	public static Map<String, List> init(String department) {
		//设备编号
		String deviceSql = "select no from deviceinfo where customerno ='"+department+"'";
		//配件编号
		String devicefittingSql = "select no from devicefittinginfo where customerno ='"+department+"'";
		//部位
		String devicepartSql = "select name from devicepartinfo where customerno = '"+department+"'";
		//区域
		String areaSql = "select no,name from areainfo where departmentid = '"+department+"'";
		//作业人员
		String employeeSql = "select no,name from employeeinfo where department = '"+department+"'";
		DataBaseAccess db = new DataBaseAccess();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Map<String, List> allMap = new HashMap<>();
		try {
			ps = (PreparedStatement) db.getPreparedStatement(deviceSql);
			rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString("no"));
			}
			allMap.put("device",list);
			rs.close();
			ps.close();
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = (PreparedStatement) db.getPreparedStatement(devicefittingSql);
			rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString("no"));
			}
			rs.close();
			ps.close();
			allMap.put("devicefitting", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = (PreparedStatement) db.getPreparedStatement(devicepartSql);
			rs = ps.executeQuery();
			List<String> list = new ArrayList<>();
			while(rs.next()) {
				list.add(rs.getString("name"));
			}
			rs.close();
			ps.close();
			allMap.put("devicepart", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = (PreparedStatement) db.getPreparedStatement(areaSql);
			Map<String, String> map = new LinkedHashMap<>();
			rs = ps.executeQuery();
			while(rs.next()) {
				map.put(rs.getString("no"), rs.getString("name"));
			}
			List<Map> list = new ArrayList<Map>();
			list.add(map);
			allMap.put("area", list);
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = (PreparedStatement) db.getPreparedStatement(employeeSql);
			Map<String, String> map = new LinkedHashMap<>();
			rs = ps.executeQuery();
			while(rs.next()) {
				map.put(rs.getString("no"), rs.getString("name"));
			}
			List<Map> list = new ArrayList<Map>();
			list.add(map);
			allMap.put("employee", list);
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allMap;
	}
	
}
