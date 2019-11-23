package com.zhuoer.employeeAndCustomerInfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.aJsonAPI.Power;
import com.zhuoer.employeeAndCustomerInfo.entity.EmployeeInfo;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class EmploeeDao {
	
	public static EmployeeInfo getEmployeeById(int id) {
		String sql = "select * from employeeinfo where id=?";
		PreparedStatement ps;
		EmployeeInfo ei = new EmployeeInfo();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ei.setNo(rs.getString("no"));
				ei.setId(rs.getInt("id"));
				ei.setName(rs.getString("name"));
				ei.setSex(rs.getString("sex"));
				ei.setBirthday(rs.getString("birthday"));
				ei.setCardID(rs.getString("cardid"));
				ei.setLeavedate(rs.getString("leavedate"));
				ei.setContacts(rs.getString("contacts"));
				ei.setPhone(rs.getString("phone"));
				ei.setState(rs.getString("state"));
				ei.setSchrecord(rs.getString("schrecord"));
				ei.setRecord(rs.getString("record"));
				ei.setCertificate(rs.getString("certificate"));
				ei.setDepartment(rs.getString("department"));
				ei.setArea(rs.getString("area"));
				ei.setPath(rs.getString("path"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ei;
	}

	public static int getEmployeeCount(String no, String name) {
		String sql = "";
		if(name.equals("admin")) {
			sql = "select count(*) from employeeinfo where no like ?";
		} else {
			
			sql = "select count(*) from employeeinfo where no like ? and department in (select department from employeeinfo where no = '"+name+"') ";
		}
		int count = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, "%"+no+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				count =  rs.getInt("count(*)");
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public static List<EmployeeInfo> getEmployee(String no, String page, String name) {
		String sql = "";
		if(!name.equals("admin")) {
			sql = "select * from employeeinfo where no like ? and department in (select department from employeeinfo where no = '"+name+"') order by id desc limit ?,?";
		} else {
			sql = "select * from employeeinfo where no like ? order by id desc limit ?,?";
		}
		PreparedStatement ps = null;
		int p = Integer.parseInt(page);
		int begin = 0, end = 0;
		begin = (p-1)*15;
		end = 15;
		ResultSet rs = null;
		List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, "%"+no+"%");
			ps.setInt(2, begin);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				EmployeeInfo ei = new EmployeeInfo();
				ei.setId(rs.getInt("id"));
				ei.setNo(rs.getString("no"));
				ei.setName(rs.getString("name"));
				ei.setSex(rs.getString("sex"));
				ei.setBirthday(rs.getString("birthday"));
				ei.setCardID(rs.getString("cardid"));
				ei.setLeavedate(rs.getString("leavedate"));
				ei.setContacts(rs.getString("contacts"));
				ei.setPhone(rs.getString("phone"));
				ei.setState(rs.getString("state"));
				ei.setSchrecord(rs.getString("schrecord"));
				ei.setPath(rs.getString("path"));
				ei.setRecord(rs.getString("record"));
				ei.setCertificate(rs.getString("certificate"));
				ei.setDepartment(rs.getString("department"));
				ei.setArea(rs.getString("area"));
				ei.setFactorytime(rs.getString("factorytime"));
				list.add(ei);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean isUniqueEmployeeAndCustomer(String no) {
		String employee = "select * from employeeinfo where no = ?";
		String customer = "select * from customerinfo where no = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int ecount = 0;
		int ccount = 0;
		try {
			ps = new DataBaseAccess().getPreparedStatement(employee);
			ps.setString(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				ecount++;
			}
			rs.close();
			ps.close();
			ps = new DataBaseAccess().getPreparedStatement(customer);
			ps.setString(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				ccount++;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ecount > 0 || ccount > 0) {
			return false;
		}
		return true;
	}
	
	public static boolean isUniqueDepartmentId(String department) {
		String sql="select * from employeeinfo where department=?";
		PreparedStatement ps;
		ResultSet rs;
		int count = 0;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, department);
			rs = ps.executeQuery();
			while(rs.next()) {
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count>0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean insertEmployee(EmployeeInfo ei, String no) {
		
		String sql = "insert into employeeinfo(no,name,sex,birthday,cardid,leavedate,"
				+ "contacts,phone,path,state,schrecord,certificate,department,area,record,factorytime) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		if(isUniqueEmployeeAndCustomer(ei.getNo())) {
			try {
				ps = new DataBaseAccess().getPreparedStatement(sql);
				ps.setString(1, ei.getNo());
				ps.setString(2, ei.getName());
				ps.setString(3, ei.getSex());
				ps.setString(4, ei.getBirthday());
				ps.setString(5, ei.getCardID());
				ps.setString(6, ei.getLeavedate());
				ps.setString(7,ei.getContacts());
				ps.setString(8,ei.getPhone());
				ps.setString(9, ei.getPath());
				ps.setString(10,ei.getState());
				ps.setString(11, ei.getSchrecord());
				ps.setString(12, ei.getCertificate());
				ps.setString(13, ei.getDepartment());
				ps.setString(14, ei.getArea());
				ps.setString(15, ei.getRecord());
				ps.setString(16, ei.getFactorytime());
				ps.execute();
				ps.close();
				Power.addUserPower(ei.getNo());
				System.out.println("添加员工");
				OpLogInfoTools.insertOpLogInfo(no, "员工管理=>员工信息=>添加操作");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		else {
			return false;
		}
		return true;
	}
	
	public static boolean updateEmployee(EmployeeInfo ei, String no) {
		String sql = "update employeeinfo set no=?,name=?,sex=?,birthday=?,cardid=?,leavedate=?,"
				+ "contacts=?,state=?,schrecord=?,certificate=?,department=?,area=?,record=?,factorytime=? where id=? ";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, ei.getNo());
			ps.setString(2, ei.getName());
			ps.setString(3, ei.getSex());
			ps.setString(4, ei.getBirthday());
			ps.setString(5, ei.getCardID());
			ps.setString(6, ei.getLeavedate());
			ps.setString(7,ei.getContacts());
			ps.setString(8, ei.getState());
			ps.setString(9, ei.getSchrecord());
			ps.setString(10, ei.getCertificate());
			ps.setString(11, ei.getDepartment());
			ps.setString(12, ei.getArea());
			ps.setString(13, ei.getRecord());
			ps.setString(14, ei.getFactorytime());
			ps.setInt(15,ei.getId());
			ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "员工管理=>员工信息=>更新操作:"+ei.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean deleteEmployee(String id, String no) {
		String sql ="delete from employeeinfo where id =?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, id);
			ps.execute();
			ps.close();
			Power.deletePower(id);
			OpLogInfoTools.insertOpLogInfo(no, "员工管理=>员工信息=>删除操作:"+id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean updateImage(String path, String id) {
		String sql = "update employeeinfo set path=? where id=?";
		PreparedStatement ps;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, path);
			ps.setInt(2, Integer.valueOf(id));
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
