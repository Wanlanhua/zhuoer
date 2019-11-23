package com.zhuoer.newCareDevice.dao;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

public class ShDao {

	public static boolean baseDao(String status, String shenheyijian, String tableName, int id) {
		tableName = tableName.toLowerCase();
		String sql = "update "+tableName+" set status = '"+status+"',shenheyijian = '"+shenheyijian+"' where id = "+id;
		try {
			new DataBaseAccess().update(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean shDbczjlb(String status, String shenheyijian, String id) {
		return baseDao(status, shenheyijian, "dbczjlb", Integer.valueOf(id));
	}
	
	public static boolean shJzgqzlljlb(String status, String shenheyijian, String id) {
		return baseDao(status, shenheyijian, "Jzgqzlljlb", Integer.valueOf(id));
	}
	
	public static boolean shPdsdy(String status, String shenheyijian, String id) {
		return baseDao(status, shenheyijian, "Pdsdy", Integer.valueOf(id));
	}
	
	public static boolean shPdsgy(String status, String shenheyijian, String id) {
		return baseDao(status, shenheyijian, "Pdsgy", Integer.valueOf(id));
	}
	
	public static boolean shQzxjjlb(String status, String shenheyijian, String id) {
		return baseDao(status, shenheyijian, "Qzxjjlb", Integer.valueOf(id));
	}
	
	public static boolean shWnsdlq(String status, String shenheyijian, String id) {
		return baseDao(status, shenheyijian, "Wnsdlq", Integer.valueOf(id));
	}
	
	public static boolean shKqysjyxjlb(String status, String shenheyijian, String id) {
		return baseDao(status, shenheyijian, "kqysjyxjlb", Integer.valueOf(id));
	}
	
	public static void main(String[] args) {
		String test = "sdfHAs";
		System.out.println(test.toLowerCase());
	}
	
}
