package com.zhuoer.qmaintance.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuoer.qmaintance.beans.RepairInfo;

public class RepairInfoTools {
	
	/**
	 * 所有查询工具类
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static List executeQuary(String sql, Object... objects) {
		List li=new ArrayList();
		ResultSet rs;
		try {
			PreparedStatement ps=DBTools.getConnection().prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);

				}
			}
			rs= ps.executeQuery();
			
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++)
				{
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				li.add(rowData);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return li;
	}
	/**
	 * 查询总页数
	 * @param sql
	 * @return
	 */
	public static int selectCount(String sql) {
		int count=0;
		try {
			PreparedStatement ps = DBTools.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				count = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	/**
	 * 修改操作工具类(增删改)
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static int executeUpdate(String sql, Object... objects) {
		int s = -1;
		try {
			
			PreparedStatement ps=DBTools.getConnection().prepareStatement(sql);
			if (objects != null)
				for (int i = 0; i < objects.length; i++) {
					
					ps.setObject(i + 1, objects[i]);
				}

			s= ps.executeUpdate();
			ps.close();
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static int addRepairInfo(RepairInfo ri) {
		int i=0;
		String sql="insert into repairinfo(className,deviceNo,person,date,content,source,"
				+ "auditLevel,path1,path2,path3,path4,path5,repairDate,repairDepartment,"
				+ "repairTime,faultPhenomenon,maintenancePerson,state1,date1,state2,person2,"
				+ "date2,state,auditOpinion1,auditOpinion2,stamp,mark,time,faultCategory,reviewer)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		i=RepairInfoTools.executeUpdate(sql,ri.getClassName(),ri.getDeviceNo(),ri.getPerson()
				,ri.getDate(),ri.getContent(),ri.getSource(),ri.getAuditLevel(),ri.getPath1()
				,ri.getPath2(),ri.getPath3(),ri.getPath4(),ri.getPath5(),ri.getRepairDate()
				,ri.getRepairDepartment(),ri.getRepairTime(),ri.getFaultPhenomenon(),ri.getMaintenancePerson()
				,ri.getState1(),ri.getDate1(),ri.getState2(),ri.getPerson2(),ri.getDate2(),"0"
				,ri.getAuditOpinion1(),ri.getAuditOpinion2(),ri.getStamp(),ri.getMark(),ri.getTime()
				,ri.getFaultCategory(),ri.getReviewer());
		if(i>0)
		{
			return i;
		}
		else{
			return i;
		}
		
	}

}
