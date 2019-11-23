package com.zhuoer.statistical.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.statistical.dto.Statistical;
import com.zhuoer.statistical.param.Param;

public class Dao {

	public static List<Statistical> get(Param param) {
		if(param.getJihuastarttime()!=""&&param.getJihuastarttime()!=null) {
			param.setJihuastarttime(param.getJihuastarttime()+" 00:00:00");
		}
		if(param.getJihuaendtime()!=""&&param.getJihuaendtime()!=null) {
			param.setJihuaendtime(param.getJihuaendtime()+" 23:59:59");
		}
		
		StringBuffer sb = new StringBuffer("select a.* from repairrecordsinfo a,deviceinfo b,RepairInfo d where a.deviceno=b.no");
		if(param.getDeviceno()!=""&&param.getDeviceno()!=null) {
			sb.append(" and a.deviceno = b.no and b.no='"+param.getDeviceno()+"'");
		}
		if(param.getArea()!=null&&param.getArea()!="") {
			sb.append(" and b.area like '%"+param.getArea()+"%'");
		}
		if(param.getDevicename()!=""&&param.getDevicename()!=null) {
			sb.append(" and b.name like '%"+param.getDevicename()+"%'");
		}
		if(param.getStarttime()!=""&&param.getStarttime()!=null) {
			sb.append(" and a.repairDate >= '"+param.getStarttime()+"'");
		}
		if(param.getEndtime()!=""&&param.getEndtime()!=null) {
			sb.append(" and a.repairDate <= '"+param.getEndtime()+"'");
		}
		if(param.getTask()!=""&&param.getTask()!=null) {
			sb.append(" and a.task = '"+param.getTask()+"'");
		}
		if(param.getImplementationDistinction()!=""&&param.getImplementationDistinction()!=null) {
			sb.append(" and a.implementationDistinction = '"+param.getImplementationDistinction()+"'");
		}
		if((param.getJihuastarttime()!=""&&param.getJihuastarttime()!=null) || param.getJihuaendtime()!=""&&param.getJihuaendtime()!=null) {
			if(param.getJihuastarttime()!=""&&param.getJihuastarttime()!=null) {
				sb.append(" and d.date2 >= '"+param.getJihuastarttime()+"'");
			}
			if(param.getJihuaendtime()!=""&&param.getJihuaendtime()!=null) {
				sb.append(" and d.date2 <= '"+param.getJihuaendtime()+"'");
			}
			sb.append(" and (d.state2='2' or d.person2!='')");
		}
		
		if(param.getAcceptancePerson()!=""&&param.getAcceptancePerson()!=null) {
			sb.append(" and a.acceptancePerson = '"+param.getAcceptancePerson()+"'");
		}
		if(param.getFinish()!=""&&param.getFinish()!=null) {
			sb.append(" and d.state2 = '"+param.getFinish()+"'");
		}
		if(param.getPart()!=""&&param.getPart()!=null) {
			sb.append(" and a.part like '%"+param.getPart()+"%'");
		}

		if(param.getRepairman()!=""&&param.getRepairman()!=null) {
			sb.append(" and a.repairman like '%"+param.getRepairman()+"%'");
		}
		sb.append(" GROUP BY id");
		String sql = sb.toString();
		ResultSet rs;
		List<Statistical> list = new ArrayList<Statistical>();
		try {
			PreparedStatement ps = (PreparedStatement) new DataBaseAccess().getPreparedStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Statistical obj = new Statistical();
				obj.setDeviceNo(rs.getString("deviceno"));
				obj.setRepairDate(rs.getString("repairDate"));
				obj.setAcceptance(rs.getString("acceptance"));
				obj.setStartTime(rs.getString("startTime"));
				obj.setEndTime(rs.getString("endTime"));
				obj.setAcceptancePerson(rs.getString("acceptancePerson"));
				obj.setTask(rs.getString("task"));
				obj.setImplementationDistinction(rs.getString("implementationDistinction"));
				obj.setFinish(rs.getString("finish"));
				obj.setMaintenanceTime(rs.getString("maintenanceTime"));
				obj.setDownTime(rs.getString("downTime"));
				obj.setRepairmanNumber(rs.getString("repairmanNumber"));
				obj.setPart(rs.getString("part"));
				obj.setFailureCause(rs.getString("failureCause"));
				obj.setDisposalMethod(rs.getString("disposalMethod"));
				obj.setRepairman(rs.getString("repairman"));
				obj.setParts(rs.getString("parts"));
				obj.setMark(rs.getString("mark"));
				list.add(obj);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Statistical> nlist = new ArrayList<Statistical>();
		for(int i=0; i<list.size(); i++) {
			Statistical obj = list.get(i);
			String parts = obj.getParts();
			if(!parts.equals("")&&parts!=null) {
				parts = parts.substring(0, parts.length()-1);
				String[] partsArr = parts.split(",");
				int partsLen = partsArr.length;
				if(partsLen>1) {
					for(int j=0; j<partsLen; j++) {
						String sparts = partsArr[j];
						String[] split = sparts.split("-");
						Statistical o = null;
						try {
							o = (Statistical) obj.clone();
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						partsAdpt(split, o);
						nlist.add(o);
					}
				}
				else {
					String sparts = partsArr[0];
					String[] split = sparts.split("-");
					partsAdpt(split, obj);
					nlist.add(obj);
				}
			}
			else {
				nlist.add(obj);
			}
		}
		// TOTO:
		List<Statistical> finalList = new ArrayList<Statistical>();
		if(param.getPartno()!=""&&param.getPartno()!=null) {
			String partsSql = "select name from DeviceFittingInfo where no = '"+param.getPartno()+"'";
			ResultSet prs = null;
			String partsName = "";
			try {
				prs = new DataBaseAccess().query(partsSql);
				if(prs.next()) {
					partsName = prs.getString("name");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(partsName!=""&&partsName!=null) {
				for(int i=0; i<nlist.size(); i++) {
					Statistical obj = nlist.get(i);
					if(obj.getPartsName().equals(partsName)) {
						finalList.add(obj);
					}
				}
			}
		} else if(param.getPartname()!=""&&param.getPartname()!=null) {
			for(int i=0; i<list.size(); i++) {
				Statistical obj = list.get(i);
				if(obj.getPartsName().indexOf(param.getPartname())!=-1) {
					finalList.add(obj);
				}
			}
		} else {
			return nlist;
		}
		return finalList;
		
	}
	
	public static void partsAdpt(String[] parts,Statistical obj) {
		if(parts.length>0 && parts.length==2) {
			obj.setPartsName(parts[0]);
			obj.setPartsNumber(parts[1]);
		}
	}
	
	public static void main(String[] args) {
		Param param = new Param();
//		param.setDeviceno("1");
//		param.setDevicename("1");
//		param.setStarttime("");
//		param.setTask("1");
//		param.setImplementationDistinction("1");
//		param.setAcceptancePerson("1");
		param.setFinish("是");
//		param.setPartname("1");
//		param.setPartno("1");
//		param.setPart("1");
//		param.setArea("1");
//		param.setRepairman("1");
		get(param);
	}
	
}
