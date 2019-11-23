package com.zhuoer.newCareDevice.dao;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

public class DeleteDao {

	public static boolean baseDao(int id, String tableName) {
		String sql = "delete from "+tableName +" where id="+id;
		try {
			new DataBaseAccess().update(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean deleteDbczjlb(int id) {
		boolean status = baseDao(id, "dbczjlb");
		return status;
	}

	public static boolean deleteJzgqzlljlb(int id) {
		boolean status = baseDao(id, "jzgqzlljlb");
		return status;
	}
	
	public static boolean deletepdsdy(int id) {
		boolean status = baseDao(id, "Pdsdy");
		return status;
	}
	
	public static boolean deletePdsgy(int id) {
		boolean status = baseDao(id, "pdsgy");
		return status;
	}
	
	public static boolean deleteQiti(int id) {
		boolean status = baseDao(id, "qiti");
		return status;
	}
	
	public static boolean deleteQzxjjlb(int id) {
		boolean status = baseDao(id, "qzxjjlb");
		return status;
	}
	
	public static boolean deleteWnsdlq(int id) {
		boolean status = baseDao(id, "wnsdlq");
		return status;
	}
	
	public static boolean deleteKqysjyxjlb(int id) {
		return baseDao(id, "kqysjyxjlb");
	}
	
}
