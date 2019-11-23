package com.zhuoer.qmaintance.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhuoer.qmaintance.beans.MaintenanceRecords;



/**
 * 保养记录表工具类
 * @author 秦奋
 *
 */
public class MaintenanceRecordsTools {
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
			//把resultset内容传到list
			ResultSetMetaData md = rs.getMetaData();//获取键名
			int columnCount = md.getColumnCount();//获取行的数量
			while (rs.next()) {
				Map rowData = new HashMap();//声明Map
				for (int i = 1; i <= columnCount; i++)
				{
					rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值ֵ
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
	 * 保养记录表查询工具类
	 * @param sql
	 * @return保养记录对象
	 */
	public static ArrayList<MaintenanceRecords> listSQL(String sql) {
		ArrayList<MaintenanceRecords> records = new ArrayList<>();
		try {
			PreparedStatement ps = DBTools.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MaintenanceRecords mri = new MaintenanceRecords();
				int id=rs.getInt("id");
				mri.setId(rs.getInt("id"));
				mri.setDeviceNo(rs.getString("deviceNo"));
				mri.setContent(rs.getString("content"));
				mri.setPart(rs.getString("part"));
				mri.setOil(rs.getString("oil"));
				mri.setType(rs.getString("type"));
				mri.setFill(rs.getString("fill"));
				mri.setMethod(rs.getString("method"));
				mri.setTpye(rs.getString("tpye"));
				mri.setReason(rs.getString("reason"));
				mri.setStandard(rs.getString("standard"));
				mri.setSecurity(rs.getString("security"));
				mri.setTool(rs.getString("tool"));
				mri.setPnumber(rs.getString("pnumber"));
				mri.setMnumber(rs.getString("mnumber"));
				mri.setCycle(rs.getString("cycle"));
				mri.setMaintenanceDate(rs.getDate("maintenanceDate"));
				mri.setMaintenancePerson(rs.getString("maintenancePerson"));
				String state1=rs.getString("state1");
				if(state1.equals("0"))
				{
					state1="未领取";
				}
				if(state1.equals("1"))
				{
					state1="领取";
				}
				mri.setState1(state1);
				mri.setDate1(rs.getString("date1"));
				String state2=rs.getString("state2");
				if(state2.equals("0"))
				{
					state2="未被领取";
				}
				if(state2.equals("1"))
				{
					state2="完成";
				}
				if(state2.equals("2"))
				{
					state2="未完成转计划";
				}
				mri.setState2(state2);
				mri.setDate2(rs.getString("date2"));
				String state=rs.getString("state");
				if(state.equals("0"))
				{
					state="提交";
				}
				if(state.equals("1"))
				{
					state="一级审核通过";
				}
				if(state.equals("2"))
				{
					state="一级审核未通过";
				}
				if(state.equals("3"))
				{
					state="二级审核通过";
				}
				if(state.equals("4"))
				{
					state="二级审核未通过";
				}
				mri.setState(state);
				mri.setAuditOpinion1("auditOpinion1");
				mri.setAuditOpinion2("auditOpinion2");
				records.add(mri);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
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
	 * 添加润滑信息记录
	 * @param mr
	 * @return
	 */
	public static int addLubricationRecordsInfo(MaintenanceRecords mr)
	{
		int i=0;
		String sql="insert into LubricationRecordsInfo(deviceNo,content,part,oil,type,fill,"
				+ "method,tpye,reason,standard,security,tool,pnumber,mnumber,cycle,"
				+ "maintenanceDate,maintenancePerson,state1,date1,state2,person2,date2,state,"
				+ "auditOpinion1,auditOpinion2,stamp,mark,time,auditOpinion4) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=DBTools.getConnection().prepareStatement(sql);
			ps.setString(1,mr.getDeviceNo());
			ps.setString(2,mr.getContent());
			ps.setString(3,mr.getPart());
			ps.setString(4,mr.getOil());
			ps.setString(5,mr.getType());
			ps.setString(6,mr.getFill());
			ps.setString(7,mr.getMethod());
			ps.setString(8,mr.getTpye());
			ps.setString(9,mr.getReason());
			ps.setString(10,mr.getStandard());
			ps.setString(11,mr.getSecurity());
			ps.setString(12,mr.getTool());
			ps.setString(13,mr.getPnumber());
			ps.setString(14,mr.getMnumber());
			ps.setString(15,mr.getCycle());
			ps.setDate(16, mr.getMaintenanceDate());
			ps.setString(17, mr.getMaintenancePerson());
			ps.setString(18, mr.getState1());
			ps.setString(19, mr.getDate1());
			ps.setString(20, mr.getState2());
			ps.setString(21, mr.getPerson2());
			ps.setString(22, mr.getDate2());
			ps.setString(23, "0");
			ps.setString(24, mr.getAuditOpinion1());
			ps.setString(25, mr.getAuditOpinion2());
			ps.setString(26, mr.getStamp());
			ps.setString(27, mr.getMark());
			ps.setString(28, mr.getTime());
			ps.setString(29, mr.getAuditOpinion4());
			//ps.setInt(17, mr.getId());
			//ps.setTimestamp(16, new Timestamp(mr.getMaintenanceDate().getTime()));
			i=ps.executeUpdate();
			ps.close();
			if(i>0)
				return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 添加保养信息记录
	 * @param mr
	 * @return
	 */
	public static int addMaintance(MaintenanceRecords mr)
	{
		int i=0;
		String sql="insert into maintenancerecordsinfo(deviceNo,content,part,oil,type,fill,"
				+ "method,tpye,reason,standard,security,tool,pnumber,mnumber,cycle,"
				+ "maintenanceDate,maintenancePerson,state1,date1,state2,person2,date2,state,"
				+ "auditOpinion1,auditOpinion2,stamp,mark,time,auditOpinion4) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=DBTools.getConnection().prepareStatement(sql);
			ps.setString(1,mr.getDeviceNo());
			ps.setString(2,mr.getContent());
			ps.setString(3,mr.getPart());
			ps.setString(4,mr.getOil());
			ps.setString(5,mr.getType());
			ps.setString(6,mr.getFill());
			ps.setString(7,mr.getMethod());
			ps.setString(8,mr.getTpye());
			ps.setString(9,mr.getReason());
			ps.setString(10,mr.getStandard());
			ps.setString(11,mr.getSecurity());
			ps.setString(12,mr.getTool());
			ps.setString(13,mr.getPnumber());
			ps.setString(14,mr.getMnumber());
			ps.setString(15,mr.getCycle());
			ps.setDate(16, mr.getMaintenanceDate());
			ps.setString(17, mr.getMaintenancePerson());
			ps.setString(18, mr.getState1());
			ps.setString(19, mr.getDate1());
			ps.setString(20, mr.getState2());
			ps.setString(21, mr.getPerson2());
			ps.setString(22, mr.getDate2());
			ps.setString(23, "0");
			ps.setString(24, mr.getAuditOpinion1());
			ps.setString(25, mr.getAuditOpinion2());
			ps.setString(26, mr.getStamp());
			ps.setString(27, mr.getMark());
			ps.setString(28, mr.getTime());
			ps.setString(29, mr.getAuditOpinion4());
			//ps.setInt(17, mr.getId());
			//ps.setTimestamp(16, new Timestamp(mr.getMaintenanceDate().getTime()));
			i=ps.executeUpdate();
			ps.close();
			if(i>0)
				return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 修改操作工具类(增删改)
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static int executeUpdate(String sql, Object... objects) {
		int s=0;
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


}
