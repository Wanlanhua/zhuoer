package com.zhuoer.qmaintance.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuoer.qmaintance.beans.MaintenanceRecords;
import com.zhuoer.qmaintance.beans.RepairRecordsInfo;

/**
 * 维修记录表工具类
 * @author Administrator
 *
 */
public class RepairRecordsTools {
	
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
	/**
	 * 添加维修记录信息
	 * @param rr
	 * @return
	 */
	public static int addRepairRecords(RepairRecordsInfo rr)
	{
		int i=0;
		String sql="insert into repairrecordsinfo(deviceNo,repairDate,acceptance,"
				+ "startTime,endTime,acceptancePerson,task,implementationDistinction,"
				+ "finish,finishTime,maintenanceTime,downTime,repairmanNumber,part,"
				+ "contentOfInsurance,riskSourceControl,faultConditions,failureCause,"
				+ "disposalMethod,conclusion,repairman,responsiblePerson,surveyor,oils,"
				+ "oilsType,oilsPrice,oilsNumber,parts,partsType,partsNumber,partsPrice,"
				+ "wait,overtime,overtimePerson,state,auditOpinion1,auditOpinion2,auditLevel,"
				+ "path1,path2,path3,path4,path5,no,stamp,mark) values(?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=DBTools.getConnection().prepareStatement(sql);
			//ps.setInt(1, rr.getId());
			ps.setString(1, rr.getDeviceNo());
			ps.setDate(2, rr.getRepairDate());
			ps.setString(3, rr.getAcceptance());
			ps.setString(4, rr.getStartTime());
			ps.setString(5, rr.getEndTime());
			ps.setString(6, rr.getAcceptancePerson());
			ps.setString(7, rr.getTask());
			ps.setString(8, rr.getImplementationDistinction());
			ps.setString(9, rr.getFinish());
			ps.setString(10, rr.getFinishTime());
			ps.setString(11, rr.getMaintenanceTime());
			ps.setString(12, rr.getDownTime());
			ps.setString(13, rr.getRepairmanNumber());
			ps.setString(14, rr.getPart() == null ? "" : rr.getPart());
			ps.setString(15, rr.getContentOfInsurance());
			ps.setString(16, rr.getRiskSourceControl());
			ps.setString(17, rr.getFaultConditions());
			ps.setString(18, rr.getFailureCause());
			ps.setString(19, rr.getDisposalMethod());
			ps.setString(20, rr.getConclusion());
			ps.setString(21, rr.getRepairman() == null ? "" : rr.getRepairman());
			ps.setString(22, rr.getResponsiblePerson());
			ps.setString(23, rr.getSurveyor());
			ps.setString(24, rr.getOils());
			ps.setString(25, rr.getOilsType());
			ps.setString(26, rr.getOilsPrice());
			ps.setString(27, rr.getOilsNumber());
			ps.setString(28, rr.getParts() == null ? "" : rr.getParts());
			ps.setString(29, rr.getPartsType());
			ps.setString(30, rr.getPartsNumber());
			ps.setString(31, rr.getPartsPrice());
			ps.setString(32, rr.getWait());
			ps.setString(33, rr.getOvertime());
			ps.setString(34, rr.getOvertimePerson());
			ps.setString(35, "0");
			ps.setString(36, rr.getAuditOpinion1());
			ps.setString(37, rr.getAuditOpinion2());
			ps.setString(38, rr.getAuditLevel());
			ps.setString(39, rr.getPath1());
			ps.setString(40, rr.getPath2());
			ps.setString(41, rr.getPath3());
			ps.setString(42, rr.getPath4());
			ps.setString(43, rr.getPath5());
			ps.setString(44, rr.getNo());
			ps.setString(45, rr.getStamp());
			ps.setString(46, rr.getMark());
			i=ps.executeUpdate();
			ps.close();
			if(i>0)
				return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
		
	}

}
