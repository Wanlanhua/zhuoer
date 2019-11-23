package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 改变认领状态
 */
@WebServlet("/PMaintenanceU")
public class PMaintenanceU extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PMaintenanceU() {
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
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String di = jsonObject.getString("id");
		String No = jsonObject.getString("no");
		
		String state2 = jsonObject.getString("state2");
		String contentTpye = jsonObject.getString("contentType");
		jsonObject.clear();
		
		//根据no查处人民
		String namesql="select * from employeeinfo where no='"+No+"'";
		// 获取当前的时间
		Date day = new Date();
		String Rday = df.format(day);
		// 根据id进行判断是否认领
		String Rsql = "";
		if (contentTpye.contains("润滑")) {
			Rsql = "select * from lubricationrecordsinfo where id='" + di + "'";
		} else {
			Rsql = "select * from maintenancerecordsinfo where id='" + di + "'";
		}

		List<Map<String, Object>> Rrs = RepairInfoTools.executeQuary(Rsql);
		List<Map<String, Object>> namers = RepairInfoTools.executeQuary(namesql);
		
		
		String Zsql="";
		if(contentTpye.contains("润滑"))
		{
			Zsql="select * from lubricationrecordsinfo where id='"+di+"'";
		}else
		{
			Zsql="select * from maintenancerecordsinfo where id='"+di+"'";
		}
		List<Map<String, Object>> listW = RepairInfoTools.executeQuary(Zsql);
		JSONObject teMap = new JSONObject();
		if(listW.size()>0)
		{
			String id = "";
			try {
				id = listW.get(0).get("id").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("id", id);
			String deviceNo1 = "";
			try {
				deviceNo1 = listW.get(0).get("deviceNo").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("deviceNo", deviceNo1);
			String content = "";
			try {
				content = listW.get(0).get("content").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("content", content);


			String reason = "";
			try {
				reason = listW.get(0).get("reason").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("reason", reason);

			String standard = "";
			try {
				standard = listW.get(0).get("standard").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("standard", standard);

			String security = "";
			try {
				security = listW.get(0).get("security").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("security", security);

			String tool = "";
			try {
				tool = listW.get(0).get("tool").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("tool", tool);

			String pnumber = "";
			try {
				pnumber = listW.get(0).get("pnumber").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("pnumber", pnumber);
			String mnumber = "";
			try {
				mnumber = listW.get(0).get("mnumber").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("mnumber", mnumber);

			String cycle = "";
			try {
				cycle = listW.get(0).get("cycle").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("cycle", cycle);

			String maintenanceDate = "";
			try {
				maintenanceDate = listW.get(0).get("maintenanceDate").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("maintenanceDate", maintenanceDate);

			String maintenancePerson = "";
			try {
				maintenancePerson = listW.get(0).get("maintenancePerson").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("maintenancePerson", maintenancePerson);

			String state11 = "";
			try {
				state11 = listW.get(0).get("state1").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("state1", state11);

			String date1 = "";
			try {
				date1 = listW.get(0).get("date1").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("date1", date1);

			String state21 = "";
			try {
				state21 = listW.get(0).get("state2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("state2", state21);

			String person2 = "";
			try {
				person2 = listW.get(0).get("person2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("person2", person2);

			String date2 = "";
			try {
				date2 = listW.get(0).get("date2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("date2", date2);

			String state = "";
			try {
				state = listW.get(0).get("state").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("state", state);

			String auditOpinion1 = "";
			try {
				auditOpinion1 = listW.get(0).get("auditOpinion1").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("auditOpinion1", auditOpinion1);

			String auditOpinion2 = "";
			try {
				auditOpinion2 = listW.get(0).get("auditOpinion2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("auditOpinion2", auditOpinion2);

			String auditOpinion4 = "";
			try {
				auditOpinion4 = listW.get(0).get("auditOpinion4").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("auditOpinion4", auditOpinion4);

			String mark = "";
			try {
				mark = listW.get(0).get("mark").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("mark", mark);
		}

			String noname="";

			for(int i=0;i<namers.size();i++)
			{
				
				try {
					noname=namers.get(i).get("name").toString();
				} catch (Exception e) {
				}
				
			}
			String state1 = "0";
			for(int i=0;i<Rrs.size();i++)
			{
				
				try {
					state1=Rrs.get(i).get("state1").toString();
				} catch (Exception e) {
				}
				
			}
//			while (rs1.next()) {
//				state1 = rs1.getString("state1");
//			}
			if (state1.equals("0")) {
				int i=0;
				if(state2.equals("0"))
				{
					String sql="";
					if(contentTpye.contains("润滑"))
					{
						sql="update lubricationrecordsinfo set state1=?,maintenancePerson=?,date1=? where id='"+di+"'";
						OpLogInfoTools.insertOpLogInfo(No, 
								"保养润滑=>润滑管理=>认领操作");
					}else
					{
						sql="update maintenancerecordsinfo set state1=?,maintenancePerson=?,date1=? where id='"+di+"'";
						OpLogInfoTools.insertOpLogInfo(No, 
								"保养润滑=>保养管理=>认领操作");
					}
					 i=Tools.executeUpdate(sql, "1",noname,Rday);
				}else
				{
					String sql2="";
					if(contentTpye.contains("润滑"))
					{
						sql2="update lubricationrecordsinfo set state1=?,person2=?,date2=? where id='"+di+"'";
						OpLogInfoTools.insertOpLogInfo(No, 
								"保养润滑=>润滑管理=>认领操作");
					}else
					{
						sql2="update maintenancerecordsinfo set state1=?,person2=?,date2=? where id='"+di+"'";
						OpLogInfoTools.insertOpLogInfo(No, 
								"保养润滑=>保养管理=>认领操作");
					}
					 i=Tools.executeUpdate(sql2,"1",noname,Rday);
				}
				if(i>0)
				{
				
						
						teMap.put("state1", "0");
						teMap.put("msg", "成功");
					
		
					try {
						//JSONArray jsonArray = JSONArray.fromObject(list);
						response.getWriter().write(teMap.toString());
					} catch (Exception e) {
						// TODO: handle exception
						String mString = e.getMessage();

					}
				
				}
				else
				{
					
					teMap.put("result", "1");
					teMap.put("msg", "失败");
					 String string = teMap.toString();
					 response.getWriter().write(string);
				}
			} else {
				
				teMap.put("msg", "已被领取");
				teMap.put("state1", "1");
				String s1 = teMap.toString();

				response.getWriter().write(s1);
			}
		
	}

}
