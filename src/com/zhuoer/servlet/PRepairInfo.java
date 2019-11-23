package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zhuoer.dao.PDate;
import com.zhuoer.dao.Tools;
import com.zhuoer.qmaintance.beans.RepairRecordsInfo;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;
import com.zhuoer.qmaintance.utils.RepairRecordsTools;

import net.sf.json.JSONObject;

/**
 * 接受报修信息进行数据库插入
 */
@WebServlet("/PRepairInfo")
public class PRepairInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	java.sql.Date sDate = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PRepairInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		StringBuffer json1 = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			if ((line = reader.readLine()) != null) {
				json1.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 接受数据
		JSONObject js1 = JSONObject.fromObject(json1.toString());
		String Class = js1.getString("className");
		String deviceNo = js1.getString("deviceNo");
		String person = js1.getString("person");
		String date = js1.getString("date");
		
		String content = js1.getString("content");
		String source = js1.getString("source");
		String auditLevel = js1.getString("auditLevel");
		String path1 = js1.getString("path1");
		String path2 = js1.getString("path2");
		String path3 = js1.getString("path3");
		String path4 = js1.getString("path4");
		String path5 = js1.getString("path5");
		String repairDates = js1.getString("repairDate");
		if (repairDates == null || repairDates.equals("") || repairDates.length() <= 0) {
			Date Udate = new Date();
			repairDates = sdf.format(Udate);
		}
		repairDates = PDate.sss(repairDates);
		java.util.Date repairDate = null;
		try {
			repairDate = sdf.parse(repairDates);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sDate = new java.sql.Date(repairDate.getTime());
		String repairDepartment = js1.getString("repairDepartment");
		String repairTime = js1.getString("repairTime");
		String faultPhenomenon = js1.getString("faultPhenomenon");
		String maintenancePerson = js1.getString("persons");
		String state1 = js1.getString("state1");
		String date1 = js1.getString("date1");
		String state2 = js1.getString("state2");
		String person2 = js1.getString("person2");
		String date2 = js1.getString("date2");
		String state = js1.getString("state");
		String auditOpinion1 = js1.getString("auditOpinion1");
		String auditOpinion2 = js1.getString("auditOpinion2");
		String mark = js1.getString("mark");
		String faultCategory = js1.getString("faultCategory");
		String reviewer = js1.getString("reviewer");
		if (date1 == null || date1.equals("") || date1.length() <= 0) {
			Date Udate1 = new Date();
			date1 = sdf2.format(Udate1);
		}
		if (date2 == null || date2.equals("") || date2.length() <= 0) {
			Date Udate2 = new Date();
			date2 = sdf2.format(Udate2);
		}

		js1.clear();

		String sql = "insert into repairinfo(className,deviceNo,person,date,content,source,auditLevel,"
				+ "path1,path2,path3,path4,path5,repairDate,repairDepartment,repairTime,faultPhenomenon,maintenancePerson,state1,date1,"
				+ "state2,person2,date2,state,auditOpinion1,auditOpinion2,mark,faultCategory,reviewer)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,?)";
		int count = Tools.executeUpdate(sql, Class, deviceNo, person, date, content, source, auditLevel, path1, path2,
				path3, path4, path5, sDate, repairDepartment, repairTime, faultPhenomenon, maintenancePerson, state1,
				date1, state2, person2, date2, state, auditOpinion1, auditOpinion2, mark, faultCategory, reviewer);
		if (count > 0) {
//			OpLogInfoTools.insertOpLogInfo(maintenancePerson, 
//					"报修管理=>插入操作");
			String sql1 = "select * from repairinfo where deviceNo=? and date=? and content=? order by id desc";
			int id = (int) ((HashMap<String, Object>) RepairInfoTools.executeQuary(sql1, deviceNo, date,content).get(0)).get("id");
			
			// 添加维修信息
			RepairRecordsInfo rr=new RepairRecordsInfo();
			rr.setNo(String.valueOf(id));
			//转换为sql.date
			java.util.Date d = null; 
			 try { 
			  d = sdf.parse(date); 
			 } catch (Exception e) { 
			  e.printStackTrace(); 
			 } 
			 java.sql.Date sqldate = new java.sql.Date(d.getTime()); 
			
			rr.setRepairDate(sqldate);
			rr.setDeviceNo(deviceNo);
			RepairRecordsTools.addRepairRecords(rr);
			
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put("result", "1");
			jsonObject1.put("msg", "成功");
			String string = jsonObject1.toString();
			response.getWriter().write(string);
		} else {
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("result", "2");
			jsonObject2.put("msg", "失败");
			String string2 = jsonObject2.toString();
			response.getWriter().write(string2);
		}


	}

}
