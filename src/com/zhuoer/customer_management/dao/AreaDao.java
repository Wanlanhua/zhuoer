package com.zhuoer.customer_management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.customer_management.Bean.AreaInfo;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class AreaDao {

	public static List<AreaInfo> AreaSelect() {
		String sql = "select * from areainfo";
		List<AreaInfo> list = new ArrayList<AreaInfo>();
		try {
			ResultSet rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				AreaInfo ai = new AreaInfo();
				ai.setId(rs.getInt("id"));
				ai.setName(rs.getString("name"));
				ai.setNo(rs.getString("no"));
				ai.setDepartmentid(rs.getString("departementid"));
				list.add(ai);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<AreaInfo> AreaSelect(String name, String page, String no) {
		List<AreaInfo> list = new ArrayList<AreaInfo>();
		int size = Integer.valueOf(page);
		int start = (size-1) * 15;
		int end = 15;
		String sql = "";
		if (!no.equals("admin")) {
			sql = "select * from areainfo where name like ? and departmentid in (select department from employeeinfo where no='"+no+"') order by id desc limit ?,?";
		} else {
			sql = "select * from areainfo where name like ? order by id desc limit ?,?";
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, "%"+name+"%");
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				AreaInfo ai = new AreaInfo();
				ai.setId(rs.getInt("id"));
				ai.setName(rs.getString("name"));
				ai.setNo(rs.getString("no"));
				ai.setDepartmentid(rs.getString("departmentid"));
				list.add(ai);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static int getAreaCount(String name, String no) {
		String sql = "";
		if(no.equals("admin")) {
			sql = "select count(name) from areainfo where name like ? and departmentid in (select department from employeeinfo where no='"+no+"')";
		} else {
			sql = "select count(name) from areainfo where name like ?";
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt("count(name)");
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public static boolean isUniqueNo(String no) {
		String sql = "select * from areainfo where no = '"+no+"'";
		ResultSet rs = null;
		int count = 0;
		try {
			rs = new DataBaseAccess().query(sql);
			if(rs.next()) {
				count++;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(count == 0) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
	
	public static boolean AreaInsert(AreaInfo ai, String no) {
		if(!isUniqueNo(ai.getNo())) {
			return false;
		}
		String sql ="insert into areainfo (no,name,departmentid) values(?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, ai.getNo());
			ps.setString(2, ai.getName());
			ps.setString(3, ai.getDepartmentid());
			ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "项目部门=>基本信息=>添加操作");
		} catch (Exception e) {
			try {
				if(!ps.isClosed())
					ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean AreaUpdate(AreaInfo ai, String no) {
		String sql = "update areainfo set name=?,departmentid=?,no=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, ai.getName());
			ps.setString(2, ai.getDepartmentid());
			ps.setString(3, ai.getNo());
			ps.setInt(4, ai.getId());
			ps.execute();
			OpLogInfoTools.insertOpLogInfo(no, "项目部门=>基本信息=>更新操作:"+ai.getId());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean AreaDelect(String id, String no) {
		String sql = "delete from areainfo where id ='"+id+"'";
		try {
			new DataBaseAccess().update(sql);
			OpLogInfoTools.insertOpLogInfo(no, "项目部门=>基本信息=>删除操作:"+id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
