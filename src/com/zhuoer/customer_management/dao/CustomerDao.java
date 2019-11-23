package com.zhuoer.customer_management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.aJsonAPI.Power;
import com.zhuoer.customer_management.Bean.CustomerInfo;
import com.zhuoer.employeeAndCustomerInfo.dao.EmploeeDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class CustomerDao {

	public static List<CustomerInfo> getCustomer(String no, String page, String name) {
		String sql = "";
		if(!name.equals("admin")) {
			sql = "select * from customerinfo where no like ? and departmentid in (select department from employeeinfo where no='"+name+"') order by id limit ?,?";
		} else {
			sql = "select * from customerinfo where no like ? order by id limit ?,?";
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		int begin = Integer.valueOf(page)-1;
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, "%"+no+"%");
			ps.setInt(2,begin);
			ps.setInt(3, 15);
			rs = ps.executeQuery();
			while(rs.next()) {
				CustomerInfo customer = new CustomerInfo();
				customer.setId(rs.getInt("id"));
				customer.setNo(rs.getString("no"));
				customer.setName(rs.getString("name"));
				customer.setManagementAddress(rs.getString("managementAddress"));
				customer.setTaxNumber(rs.getString("taxnumber"));
				customer.setBank(rs.getString("bank"));
				customer.setAccount(rs.getString("account"));
				customer.setPhone(rs.getString("phone"));
				customer.setDepartmentid(rs.getString("departmentid"));
				customer.setDepartmentname(rs.getString("departmentname"));
				list.add(customer);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static int getCustomerCount(String no, String name) {
		String sql = "";
		if(!name.equals("admin")) {
			sql = "select count(*) from customerinfo where no like ? and departmentid in (select department from employeeinfo where no='"+name+"')";
		} else {
			sql = "select count(*) from customerinfo where no like ? ";
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, "%"+no+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("count(*)");
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	public static boolean insertCustomer(CustomerInfo customer, String no) {
		String sql = "insert into customerinfo(no,name,managementaddress,"
				+ "taxnumber,bank,account,phone,departmentid,departmentname)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		if(EmploeeDao.isUniqueEmployeeAndCustomer(customer.getNo())) {
			try {
				ps = new DataBaseAccess().getPreparedStatement(sql);
				ps.setString(1, customer.getNo());
				ps.setString(2, customer.getName());
				ps.setString(3, customer.getManagementAddress());
				ps.setString(4, customer.getTaxNumber());
				ps.setString(5, customer.getBank());
				ps.setString(6, customer.getAccount());
				ps.setString(7, customer.getPhone());
				ps.setString(8, customer.getDepartmentid());
				ps.setString(9, customer.getDepartmentname());
				ps.execute();
				ps.close();
				OpLogInfoTools.insertOpLogInfo(no, "项目部门=>区域定义=>插入操作");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		else {
			return false;
		}
		Power.addUserPower(customer.getNo());
		Power.updateCustomerInfo(customer.getNo());
		return true;
	}
	
	public static boolean updateCustomer(CustomerInfo customer, String no) {
		String sql = "update customerinfo set name=?,managementAddress=?,"
				+ "taxnumber=?,bank=?,account=?,phone=?,departmentid=?,departmentname=?"
				+ " where id = ?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getManagementAddress());
			ps.setString(3, customer.getTaxNumber());
			ps.setString(4, customer.getBank());
			ps.setString(5, customer.getAccount());
			ps.setString(6, customer.getPhone());
			ps.setString(7, customer.getDepartmentid());
			ps.setString(8, customer.getDepartmentname());
			ps.setInt(9, customer.getId());
			ps.execute();
			OpLogInfoTools.insertOpLogInfo(no, "项目部门=>区域定义=>更新操作:"+customer.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean deleteCustomer(String id, String no) {
		String sql = "delete from customerinfo where id =?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, id);
			ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "项目部门=>区域定义=>删除操作:"+id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
