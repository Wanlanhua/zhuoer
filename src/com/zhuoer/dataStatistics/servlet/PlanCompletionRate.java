package com.zhuoer.dataStatistics.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PlanCompletionRate {
	public String getPlanCompletionRate(String no,String deviceNo,String sTime,String eTime,String tableNo) {
		String jsonStr="";
		String result="0";
		String msg="没有统计数据！";
		String titleCount="5";
		List<String>titleList=new ArrayList<>();
		titleList.add("部门编号");
		titleList.add("设备名称");
		titleList.add("总计划数");
		titleList.add("完成数量");
		titleList.add("完成率");
		JSONArray jsonArrayTitle=JSONArray.fromObject(titleList);
		List<String>keyList=new ArrayList<>();
		keyList.add("customerNo");
		keyList.add("name");
		keyList.add("allCount");
		keyList.add("wCount");
		keyList.add("rate");
		JSONArray jsonArrayKey=JSONArray.fromObject(keyList);
		String tableName="";
		switch (tableNo) {
		case "0":
			//保养
			tableName="MaintenanceRecordsInfo";
			break;
		case "1":
			//润滑
			tableName="lubricationRecordsInfo";
			break;
		case "2":
			//维修
			tableName="RepairInfo";
			break;

		default:
			break;
		}
		//根据开始时间、结束时间、项目部编号统计所有设备计划数量
		//String sqlAll="select COUNT(id) as allCount from maintenancerecordsinfo where id in (SELECT id  from maintenancerecordsinfo where state2=2 or person2!='') AND DATE(stamp)>=? AND DATE(stamp)<=? and deviceNo in (SELECT no FROM deviceinfo WHERE customerNo=?)";
		String sqlAll="select d.customerNo,d.name,COUNT(m.id) as allCount from "+tableName+" m,deviceinfo d where m.id in (SELECT id  from "+tableName+" where state2=2 or person2!='') AND DATE(m.stamp)>=? AND DATE(m.stamp)<=? and m.deviceNo=d.no";
		if (!no.equals("")) {
			sqlAll=sqlAll+" AND d.customerNo='"+no+"' ";
		}
		if (!deviceNo.equals("")) {
			sqlAll=sqlAll+" AND m.deviceNo='"+deviceNo+"' ";
		}
		sqlAll=sqlAll+" GROUP BY(m.deviceNo)";
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlAll,sTime,eTime);
		//根据开始时间、结束时间、项目部编号统计所有设备计划完成
		//String sqlW="select COUNT(id) as wCount from maintenancerecordsinfo where state2=1 and person2!='' AND DATE(stamp)>=? AND DATE(stamp)<=? and deviceNo in (SELECT no FROM deviceinfo WHERE customerNo=?)";
		String sqlW="select d.customerNo,d.name,COUNT(m.id) as wCount from "+tableName+" m,deviceinfo d where m.state2=1 and m.person2!=''  AND DATE(m.stamp)>=? AND DATE(m.stamp)<=? and m.deviceNo=d.no ";
		if (!no.equals("")) {
			sqlW=sqlW+" AND d.customerNo='"+no+"' ";
		}
		if (!deviceNo.equals("")) {
			sqlW=sqlW+" AND m.deviceNo='"+deviceNo+"' ";
		}
		sqlW=sqlW+" GROUP BY(m.deviceNo)";
		List<Map<String, Object>> listW=RepairInfoTools.executeQuary(sqlW,sTime,eTime);

		List<Map<String, Object>> listJson=new ArrayList<>();
		for (int i = 0; i < listAll.size(); i++) {
			
			Map<String, Object> teMap=new HashMap<>();
			String customerNo=listAll.get(i).get("customerNo").toString();
			teMap.put("customerNo", customerNo);
			String name=listAll.get(i).get("name").toString();
			teMap.put("name",name );
			String allCount=listAll.get(i).get("allCount").toString();
			teMap.put("allCount",allCount );
			String wCount="0";
			for (int j = 0; j < listW.size(); j++) {
				if (name.equals(listW.get(j).get("name"))) {
					wCount=listW.get(j).get("wCount").toString();
				}
			}
			teMap.put("wCount", wCount);
			String rate="0";
			try {
				rate=Double.toString(Double.parseDouble(wCount)/Double.parseDouble(allCount)*100).substring(0, 4)+"%";
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("rate", rate);
			listJson.add(teMap);
			result="1";
			msg="成功";
			
		}
		JSONArray jsonArray=JSONArray.fromObject(listJson);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("msg", msg);
		jsonObject.put("titleCount", titleCount);
		jsonObject.put("titleName", jsonArrayTitle.toString());
		jsonObject.put("key", jsonArrayKey.toString());
		jsonObject.put("content", jsonArray.toString());
		jsonStr=jsonObject.toString();
		return jsonStr;
	}
}
