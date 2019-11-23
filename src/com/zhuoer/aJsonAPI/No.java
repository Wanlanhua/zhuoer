package com.zhuoer.aJsonAPI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.aJsonAPI.Util.ToJson;
import com.zhuoer.customer_management.Bean.CustomerInfo;

public class No {

	/**
	 * 获得登陆号，用于权限查询
	 * @author purple
	 * @method getNo
	 * @see (一句话描述方法的作用)
	 * @param no
	 * @return
	 * @retrun List<String>
	 * @date 2018年6月16日
	 */
	public static List<String> getNo(String no) {
		String sql = "select * from logininfo where no = ? order by id desc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("name"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取权限表中编号
	 * @author purple
	 * @method getNo
	 * @retrun List<String>
	 * @date 2018年6月23日
	 */
	public static List<String> getNo() {
		String sql = "select distinct(p.no) from powerinfo p,customerinfo c where p.no not in ('admin') and p.no!=c.no order by p.no desc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("no"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<CustomerInfo> getCustomer() {
		String sql = "select name,no from customerinfo";
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		try {
			ResultSet rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				CustomerInfo ci = new CustomerInfo();
				ci.setName(rs.getString("name"));
				ci.setNo(rs.getString("no"));
				list.add(ci);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getJsonNo() {
		String json = ToJson.toJson(getNo());
		return json;
	}
	
	/**
	 * 获取所有部门
	 * @author purple
	 * @see (一句话描述方法的作用)
	 * @retrun void
	 * @date 2018年6月20日
	 */
	public static List<CustomerInfo> getDepartmentId() {
		String sql = "select departmentid,departmentname from customerinfo";
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		try {
			ResultSet rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				String id = rs.getString("departmentid");
				String name = rs.getString("departmentname");
				CustomerInfo ci = new CustomerInfo();
				ci.setDepartmentid(id);
				ci.setDepartmentname(name);
				list.add(ci);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据部门编号获取地区名称
	 * @author purple
	 * @method getAreaByDepartmentId
	 * @retrun String
	 * @date 2018年6月20日
	 */
	public static String getAreaByDepartmentId(String departmentId) {
		String sql = "select name from areainfo where departmentid ='"+departmentId+"'";
		String name = "";
		try {
			ResultSet rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				name += rs.getString("name");
				name +="-";
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * 获取部门编号
	 * @author purple
	 * @method getDepartmentIdAndName
	 * @retrun Map<String,String>
	 * @date 2018年6月23日
	 */
	public static List<CustomerInfo> getDepartmentIdAndName() {
		String sql = "select departmentid,departmentname from customerinfo";
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		try {
			ResultSet rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				String id = rs.getString("departmentid");
				String name = rs.getString("departmentname");
				CustomerInfo ci = new CustomerInfo();
				ci.setDepartmentid(id);
				ci.setDepartmentname(name);
				list.add(ci);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @retrun void
	 * @date 2018年6月23日
	 */
	public static List<String> getAllDepartmentIdAndName() {
		String sql = "select departmentid,departmentname from customerinfo";
		List<String> list = new ArrayList<String>();
		try {
			ResultSet rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				String id = rs.getString("departmentid");
				String name = rs.getString("departmentname");
				list.add(id+" | "+name);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据编号查询部门号和部门名称
	 * @author purple
	 * @method getByNoDepartmentIdAndName
	 * @see (一句话描述方法的作用)
	 * @param no
	 * @return
	 * @retrun String
	 * @date 2018年7月25日
	 */
	public static String getByNoDepartmentIdAndName(String no) {
		PreparedStatement ps = null;
		String csql = "select departmentid from customerinfo where no=?";
		String esql = "select department from employeeinfo where no=?";
		String department = "";
		boolean state = false;
		try {
			ps = new DataBaseAccess().getPreparedStatement(csql);
			ps.setString(1, no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				department = rs.getString("departmentid");
				state = true;
			}
			rs.close();
			ps.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!state) {
			try {
				ps = new DataBaseAccess().getPreparedStatement(esql);
				ps.setString(1, no);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					department = rs.getString("department");
				}
				rs.close();
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String sql = "select departmentid,departmentname from customerinfo where departmentid=?";
		List<String> list = new ArrayList<String>();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, department);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("departmentid");
				String name = rs.getString("departmentname");
				list.add(id+" | "+name);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ToJson.toJson(list);
	}
	
	public static String getAllJsonDepartmentIdAndName() {
		return ToJson.toJson(getAllDepartmentIdAndName());
	}
	
	/**
	 * 
	 * @author purple
	 * @method getDepartmentIdAndName
	 * @see (一句话描述方法的作用)
	 * @param no
	 * @return
	 * @retrun List<CustomerInfo>
	 * @date 2018年7月25日
	 */
	public static List<CustomerInfo> getDepartmentIdAndName(String no) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		if(no.equals("admin")) {
			return getDepartmentIdAndName();
		}
		else {
			PreparedStatement ps = null;
			String csql = "select departmentid from customerinfo where no=?";
			String esql = "select department from employeeinfo where no=?";
			String department = "";
			boolean state = false;
			try {
				ps = new DataBaseAccess().getPreparedStatement(csql);
				ps.setString(1, no);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					department = rs.getString("departmentid");
					state = true;
				}
				rs.close();
				ps.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!state) {
				try {
					ps = new DataBaseAccess().getPreparedStatement(esql);
					ps.setString(1, no);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						department = rs.getString("department");
					}
					rs.close();
					ps.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			String sql = "select departmentid,departmentname from customerinfo where departmentid=?";

			try {
				ps = new DataBaseAccess().getPreparedStatement(sql);
				ps.setString(1, department);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String id = rs.getString("departmentid");
					String name = rs.getString("departmentname");
					CustomerInfo ci = new CustomerInfo();
					ci.setDepartmentid(id);
					ci.setDepartmentname(name);
					list.add(ci);
				}
				rs.close();
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Test
	public void testDepartement() {
		String json = ToJson.toJson(getByNoDepartmentIdAndName("2015011425"));
		System.out.println(json);
	}
	
}
