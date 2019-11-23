package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zhuoer.dao.Tools;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONObject;

/**
 * 接受完成时的数据 进行插入
 */
@WebServlet("/PRepairrecordsInfoW")
public class PRepairrecordsInfoW extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Timestamp dateSQL;
	java.util.Date dates = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PRepairrecordsInfoW() {
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
		JSONObject js = JSONObject.fromObject(json1.toString());
		String deviceNo = js.getString("deviceNo");
		String repairDate = js.getString("repairDate");
		if (repairDate.length() <= 0) {
			Date now = new Date();
			String nows = sdf3.format(now);
			dateSQL = Timestamp.valueOf(nows);
		} else

		{
			try {
				dates = sdf3.parse(repairDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dateSQL = new java.sql.Timestamp(dates.getTime());
		}
		String acceptance = js.getString("acceptance");
		String startTime = js.getString("startTime");
		String endTime = js.getString("endTime");
		String acceptancePerson = js.getString("acceptancePerson");
		String task = js.getString("task");
		String implementationDistinction = js.getString("implementationDistinction");
		String finish = js.getString("finish");
		String finishTime = js.getString("finishTime");
		String maintenanceTime = js.getString("maintenanceTime");
		String downTime = js.getString("downTime");
		String repairmanNumber = js.getString("repairmanNumber");
		String part = js.getString("part");
		String contentOfInsurance = js.getString("contentOfInsurance");
		String riskSourceControl = js.getString("riskSourceControl");
		String faultConditions = js.getString("faultConditions");
		String failureCause = js.getString("failureCause");
		String disposalMethod = js.getString("disposalMethod");
		String conclusion = js.getString("conclusion");
		String repairman = js.getString("repairman");
		String responsiblePerson = js.getString("responsiblePerson");
		String surveyor = js.getString("surveyor");
		String oils = js.getString("oils");
		String oilsType = js.getString("oilsType");
		String oilsPrice = js.getString("oilsPrice");
		String oilsNumber = js.getString("oilsNumber");
		String parts = js.getString("parts");
		String partsType = js.getString("partsType");
		String partsNumber = js.getString("partsNumber");
		String partsPrice = js.getString("partsPrice");
		String wait = js.getString("wait");
		String overtime = js.getString("overtime");
		String overtimePerson = js.getString("overtimePerson");
		String state = js.getString("state");
		String auditOpinion1 = js.getString("auditOpinion1");
		String auditOpinion2 = js.getString("auditOpinion2");
		String auditOpinion3 = js.getString("auditOpinion3");
		String auditOpinion4 = js.getString("auditOpinion4");
		String auditLevel = js.getString("auditLevel");
		String mark = js.getString("mark");
		String path1 = js.getString("path1");
		String path2 = js.getString("path2");
		String path3 = js.getString("path3");
		String path4 = js.getString("path4");
		String path5 = js.getString("path5");
		String no = js.getString("no");

		String di = js.getString("id");
		String time = js.getString("time");
		String state2 = js.getString("state2");
		js.clear();
		if (state2.equals("完成")) {
			state2 = "1";
		} else {
			state2 = "2";
		}
        String noidsql="select id 	from repairrecordsinfo  where no='"+di+"'";
//		ResultSet noidrss=Tools.executeQuary(noidsql);
        List<Map<String, Object>> noidrss = RepairInfoTools.executeQuary(noidsql);

			String rdi="";

			for(int i=0;i<noidrss.size();i++)
			{
				
				try {
					rdi=noidrss.get(i).get("id").toString();
				} catch (Exception e) {
				}
				
			}
//			while(noidrss.next())
//			{
//				rdi=noidrss.getString("id");
//			}
			
			String sql = "update repairrecordsinfo set deviceNo=?,repairDate=?,acceptance=?, startTime=?, endTime=?, acceptancePerson=?, task=?,implementationDistinction=?,"
					+ " finish=?, finishTime=?, maintenanceTime=?, downTime=?, repairmanNumber=?, part=?,contentOfInsurance=?,"
					+ " riskSourceControl=?, faultConditions=?, failureCause=?, disposalMethod=?, conclusion=?,repairman=?, "
					+ "responsiblePerson=?, surveyor=?, oils=?, oilsType=?, oilsPrice=?, oilsNumber=?, parts=?, partsType=?,"
					+ "partsNumber=?, partsPrice=?, wait=?, overtime=?, overtimePerson=?, state=?, auditOpinion1=?, auditOpinion2=?,"
					+ "auditOpinion3=?, auditOpinion4=?, auditLevel=?, mark=?, path1=?, path2=?, path3=?, path4=?, path5=?, no=? where id='"+rdi+"'";

			String sql2 = "update repairinfo set state2=?,time=? where id='" + di + "'";
			//根据id查领取人
			String sql2s="select maintenancePerson from repairinfo where id='"+di+"'";
			List<Map<String, Object>> listW=RepairInfoTools.executeQuary(sql2s);
			String maintenancePerson="";
			for (int i = 0; i < listW.size(); i++) {
				try {
					maintenancePerson=listW.get(i).get("maintenancePerson").toString();
				} catch (Exception e) {
				}
			}
			//根据领取人找到编号no
			String sqlno="select no from employeeinfo where name='"+maintenancePerson+"'";
			List<Map<String, Object>> listno = RepairInfoTools.executeQuary(sqlno);
			String employNo = "";
			for (int i = 0; i < listno.size(); i++) {
				try {
					employNo = listno.get(i).get("no").toString();
				} catch (Exception e) {
				}
			}
			
			int i = Tools.executeUpdate(sql2, state2, time);
			int count = Tools.executeUpdate(sql, deviceNo, dateSQL, acceptance, startTime, endTime, acceptancePerson, task,
					implementationDistinction, finish, finishTime, maintenanceTime, downTime, repairmanNumber, part,
					contentOfInsurance, riskSourceControl, faultConditions, failureCause, disposalMethod, conclusion,
					repairman, responsiblePerson, surveyor, oils, oilsType, oilsPrice, oilsNumber, parts, partsType,
					partsNumber, partsPrice, wait, overtime, overtimePerson, state, auditOpinion1, auditOpinion2,
					auditOpinion3, auditOpinion4, auditLevel, mark, path1, path2, path3, path4, path5, no);
			if (i > 0 && count > 0) {
				OpLogInfoTools.insertOpLogInfo(employNo, 
						"维修管理=>完成操作");
				JSONObject jsonObject1 = new JSONObject();
				jsonObject1.put("result", "1");
				jsonObject1.put("msg", "成功");
				String string = jsonObject1.toString();
				response.getWriter().write(string);
			} else {
				JSONObject jsonObject3 = new JSONObject();
				jsonObject3.put("result", "2");
				jsonObject3.put("msg", "失败");
				String string3 = jsonObject3.toString();
				response.getWriter().write(string3);
			}
	
//			try {
//				if(Tools.ps!=null)
//				{
//					Tools.ps.close();
//				}
//		
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	



	}

}
