package com.zhuoer.customer_management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.customer_management.Bean.CustomerContactInfo;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class CustomerContactInfoDao {
	
	public static List<CustomerContactInfo> getCustomerContact(String no, String page, String name) {
		String sql = "";
		if(!name.equals("admin")) {
			sql = "select * from customercontactinfo where no like ? and no in (select department from employeeinfo where no='"+name+"') order by id desc limit ?,?";
		} else {
			sql = "select * from customercontactinfo where no like ? order by id desc limit ?,?";
		}
		PreparedStatement ps;
		ResultSet rs;
		int begin = (Integer.valueOf(page)-1)*15;
		int size = 15;
		List<CustomerContactInfo> list = new ArrayList<CustomerContactInfo>();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, "%"+no+"%");
			ps.setInt(2, begin);
			ps.setInt(3, size);
			rs = ps.executeQuery();
			System.out.println(ps.toString());
			while(rs.next()) {
				CustomerContactInfo cci =new CustomerContactInfo();
				cci.setId(rs.getInt("id"));
				cci.setNo(rs.getString("no"));
				cci.setIsEnable(rs.getString("isEnable"));
				cci.setPrimaryContact(rs.getString("primaryContact"));
				cci.setName(rs.getString("name"));
				cci.setAppellation(rs.getString("appellation"));
				cci.setPost(rs.getString("post"));
				cci.setPhone(rs.getString("phone"));
				cci.setTelephone(rs.getString("telephone"));
				cci.setFax(rs.getString("fax"));
				cci.setEmail(rs.getString("email"));
				cci.setQQ(rs.getString("QQ"));
				cci.setWeChat(rs.getString("wechat"));
				cci.setMSN(rs.getString("msn"));
				cci.setSkype(rs.getString("skype"));
				cci.setBirthday(rs.getString("birthday"));
				cci.setAddress(rs.getString("address"));
				list.add(cci);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean isUniqueCustomerContact(String no) {
		String sql = "select id from customerContactinfo where no=?";
		PreparedStatement ps;
		ResultSet rs;
		int count = 0;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, "%"+no+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				count++;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean insertCustomerContact(CustomerContactInfo cci, String no) {
		String sql = "insert into customerContactinfo(no,isenable,primaryContact,name,appellation,"
				+ "post,phone,telephone,fax,email,qq,wechat,msn,skype,birthday,address) values"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		boolean flag = isUniqueCustomerContact(cci.getNo());
		if(!flag) {
			try {
				ps = new DataBaseAccess().getPreparedStatement(sql);
				ps.setString(1, cci.getNo());
				ps.setString(2, cci.getIsEnable());
				ps.setString(3, cci.getPrimaryContact());
				ps.setString(4, cci.getName());
				ps.setString(5, cci.getAppellation());
				ps.setString(6, cci.getPost());
				ps.setString(7, cci.getPhone());
				ps.setString(8, cci.getTelephone());
				ps.setString(9, cci.getFax());
				ps.setString(10, cci.getEmail());
				ps.setString(11, cci.getQQ());
				ps.setString(12, cci.getWeChat());
				ps.setString(13, cci.getMSN());
				ps.setString(14, cci.getSkype());
				ps.setString(15, cci.getBirthday());
				ps.setString(16, cci.getAddress());
				ps.execute();
				ps.close();
				OpLogInfoTools.insertOpLogInfo(no, "项目部门=>联系人=>添加操作");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		else {
			return false;
		}
		return true;
	}
	
	public static boolean updateCustomerContact(CustomerContactInfo cci, String no) {
		String sql = "update customerContactinfo set isEnable=?,primaryContact=?,name=?,appellation=?,post=?,"
				+ "phone=?,telephone=?,fax=?,email=?,qq=?,wechat=?,msn=?,skype=?,birthday=?,address=? where id=?";
		PreparedStatement ps;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, cci.getIsEnable());
			ps.setString(2, cci.getPrimaryContact());
			ps.setString(3, cci.getName());
			ps.setString(4, cci.getAppellation());
			ps.setString(5, cci.getPost());
			ps.setString(6, cci.getPhone());
			ps.setString(7, cci.getTelephone());
			ps.setString(8, cci.getFax());
			ps.setString(9, cci.getEmail());
			ps.setString(10, cci.getQQ());
			ps.setString(11, cci.getWeChat());
			ps.setString(12, cci.getMSN());
			ps.setString(13, cci.getSkype());
			ps.setString(14, cci.getBirthday());
			ps.setString(15, cci.getAddress());
			ps.setInt(16, cci.getId());
			ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "项目部门=>联系人=>更新操作:"+cci.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean deleteCustomerContact(int id, String no) {
		String sql = "delete from customerContactinfo where id=?";
		PreparedStatement ps;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "项目部门=>联系人=>删除操作:"+id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static int getCustomerContactCount(String no, String name) {
		String sql = "";
		if(!name.equals("admin")) {
			sql = "select count(*) from customercontactinfo where no like ? and no in (select department from employeeinfo where no='"+name+"') ";
		} else {
			sql = "select count(*) from customercontactinfo where no like ?";
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
	
}
