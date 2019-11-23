package com.zhuoer.longinInfo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.longinInfo.entity.CustomerInfo;
import com.zhuoer.longinInfo.entity.EmployeeAndCustomerInfo;
import com.zhuoer.longinInfo.entity.EmployeeInfo;

public class getEmployeeInfoDao {

	/**
	 * 获取员工信息
	 * @author purple
	 * @method getEmployees
	 * @see (一句话描述方法的作用)
	 * @return
	 * @retrun List<EmployeeInfo>
	 * @date 2018年5月3日
	 */
	public static List<EmployeeInfo> getEmployees() {
		String sql = "select id,no,name from employeeinfo";
		ResultSet rs;
		List<EmployeeInfo> elist = new ArrayList<EmployeeInfo>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				EmployeeInfo employeeinfo = new EmployeeInfo();
				employeeinfo.setId(rs.getInt("id"));
				employeeinfo.setNo(rs.getString("no"));
				employeeinfo.setName(rs.getString("name"));
				elist.add(employeeinfo);
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elist;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getJsonEmployees
	 * @see (获取员工的json数据)
	 * @return
	 * @retrun String
	 * @date 2018年5月5日
	 */
	public static String getJsonEmployees() {
		List<EmployeeInfo> list = getLoginEmployees();
		Gson gson = new Gson();
		String gsonString = gson.toJson(list);
		return gsonString;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getJsonCustomers
	 * @see (获取客户的json数据)
	 * @return
	 * @retrun String
	 * @date 2018年5月5日
	 */
	public static String getJsonCustomers() {
		List<CustomerInfo> list = getLoginCustomer();
		Gson gson = new Gson();
		String gsonString = gson.toJson(list);
		return gsonString;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getLoginEmployees
	 * @see (获取未添加登陆信息的操作员)
	 * @return
	 * @retrun List<EmployeeInfo>
	 * @date 2018年5月4日
	 */
	public static List<EmployeeInfo> getLoginEmployees() {
		String sql = "select no,name from employeeinfo ";
		ResultSet rs;
		List<String> nList = getLoginIngoDao();
		if(nList.size() != 0) {
			sql +="where no not in(\"" + nList.get(0)+"\"";
			for(int i=1; i<nList.size(); i++) {
				sql += ",\"" + nList.get(i)+"\"";
			}
			sql += ")";
		}
		sql += " order by id desc";
		List<EmployeeInfo> elist = new ArrayList<EmployeeInfo>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				EmployeeInfo employeeinfo = new EmployeeInfo();
				employeeinfo.setNo(rs.getString("no"));
				employeeinfo.setName(rs.getString("name"));
				elist.add(employeeinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elist;
	}
	
	/**
	 * 获取登陆信息
	 * @author purple
	 * @method getLoginIngoDao
	 * @see (一句话描述方法的作用)
	 * @return
	 * @retrun List<String>
	 * @date 2018年5月3日
	 */
	public static List<String> getLoginIngoDao() {
		String sql = "select distinct name from logininfo";
		ResultSet rs;
		List<String> loginName = new ArrayList<String>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				loginName.add(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginName;
	}
	

	/**
	 * 获取客户信息
	 * @author purple
	 * @method getCustomerDao
	 * @see (一句话描述方法的作用)
	 * @return
	 * @retrun List
	 * @date 2018年5月3日
	 */
	public static List<CustomerInfo> getCustomerDao() {
		String sql = "select id,no,name from customerinfo ";
		List<String> nList = getEACI();
		if(nList.size() != 0) {
			sql +="where no not in('" + nList.get(0);
			for(int i=1; i<nList.size(); i++) {
				sql += "','" + nList.get(i);
			}
			sql += "')";
		}
		sql += " order by id desc";
		ResultSet rs;
		List<CustomerInfo> clist = new ArrayList<CustomerInfo>();
		
		try {
			rs = new DataBaseAccess().query(sql);
			if(rs!=null)
				while(rs.next()) {
					CustomerInfo customer = new CustomerInfo();
					customer.setId(rs.getInt("id"));
					customer.setNo(rs.getString("no"));
					customer.setName(rs.getString("name"));
					clist.add(customer);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getLoginCustomer
	 * @see (查询未添加登陆信息的客户)
	 * @return
	 * @retrun List<CustomerInfo>
	 * @date 2018年5月5日
	 */
	public static List<CustomerInfo> getLoginCustomer() {
		String sql = "select no,name from customerinfo ";
		ResultSet rs;
		List<String> nList = getLoginIngoDao();
		if(nList.size() != 0) {
			sql +="where no not in(\"" + nList.get(0)+"\"";
			for(int i=1; i<nList.size(); i++) {
				sql += ",\"" + nList.get(i)+"\"";
			}
			sql += ")";
		}
		List<CustomerInfo> elist = new ArrayList<CustomerInfo>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				CustomerInfo customerinfo = new CustomerInfo();
				customerinfo.setNo(rs.getString("no"));
				customerinfo.setName(rs.getString("name"));
				elist.add(customerinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elist;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getEACI
	 * @see (获取所有客户和操作员绑定信息)
	 * @return
	 * @retrun List<String>
	 * @date 2018年5月5日
	 */
	public static List<String> getEACI() {
		String sql = "select DISTINCT employeeNo from employeeandcustomerinfo";
		ResultSet rs;
		List<String> eList = new ArrayList<String>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				eList.add(rs.getString("employeeNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eList;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getEACI
	 * @see (查询操作员和客户绑定信息，15条一页)
	 * @param page
	 * @return
	 * @retrun List<EmployeeAndCustomerInfo>
	 * @date 2018年5月5日
	 */
	public static List<EmployeeAndCustomerInfo> getEACI(int page) {
		int start = (page-1)*15, end = page*15;
		String sql = "select * from employeeandcustomerinfo order by id desc limit "+start+","+end;
		ResultSet rs;
		List<EmployeeAndCustomerInfo> eaciList = new ArrayList<EmployeeAndCustomerInfo>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				EmployeeAndCustomerInfo employeeAndCustomerInfo = new EmployeeAndCustomerInfo();
				employeeAndCustomerInfo.setId(rs.getInt("id"));
				employeeAndCustomerInfo.setEmployeeNo(rs.getString("employeeNo"));
				employeeAndCustomerInfo.setCustomerNo(rs.getString("customerNo"));
				employeeAndCustomerInfo.setMark(rs.getString("mark"));
				eaciList.add(employeeAndCustomerInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eaciList;
	}
}
