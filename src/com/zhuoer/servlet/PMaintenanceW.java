				package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
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
 * 改变完成状态
 */
@WebServlet("/PMaintenanceW")
public class PMaintenanceW extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PMaintenanceW() {
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
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String mark = jsonObject.getString("mark");
		String state2 = jsonObject.getString("state2");
		String di = jsonObject.getString("id");
		String contentTpye = jsonObject.getString("contentType");
		//报修人
		String pnumber=jsonObject.getString("persons");
		jsonObject.clear();
		//完成时间
		Date day = new Date();
		String time = df.format(day);
		//领取人
		String sqlss="";
		if (contentTpye.contains("润滑")) 
		{
			sqlss = "select maintenancePerson from lubricationrecordsinfo  where id='" + di + "'";
		}else
		{
			sqlss = "select maintenancePerson from maintenancerecordsinfo  where id='" + di + "'";
		}
	
		List<Map<String, Object>> listW = RepairInfoTools.executeQuary(sqlss);
		String maintenancePerson = "";
		for (int i = 0; i < listW.size(); i++) {
			try {
				maintenancePerson = listW.get(i).get("maintenancePerson").toString();
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
		if (state2.equals("1")) {
					String sql2 = "";
					String sqlupdate="";
					 String numbersql="";
			if (contentTpye.contains("润滑")) {
				sql2 = "update lubricationrecordsinfo set mark=?,state2=?,time=? where id='" + di + "'";
				numbersql="update lubricationrecordsinfo set pnumber='"+pnumber+"' where id='"+di+"'";
				OpLogInfoTools.insertOpLogInfo(employNo, "保养润滑=>润滑管理=>完成操作");
			} else {
				sql2 = "update maintenancerecordsinfo set mark=?,state2=?,time=? where id='" + di + "'";
				numbersql="update maintenancerecordsinfo set pnumber='"+pnumber+"' where id='"+di+"'";
				OpLogInfoTools.insertOpLogInfo(employNo, "保养润滑=>保养管理=>完成操作");
			}
			//插入报修人
		
			    int l=Tools.executeUpdate(numbersql);
    
			int i = Tools.executeUpdate(sql2, mark, state2, time);
			//更新操作
			if (i > 0  && l>0) {
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
		} else {
			String sql3 = "";
			String usql="";
			 String numbersql="";
			if (contentTpye.contains("润滑")) {
				sql3 = "update lubricationrecordsinfo set state2=?,state1=? where id='" + di + "'";
				numbersql="update lubricationrecordsinfo set pnumber='"+pnumber+"' where id='"+di+"'";
				OpLogInfoTools.insertOpLogInfo(employNo, "保养润滑=>润滑管理=>未完成操作");
			} else {
				sql3 = "update maintenancerecordsinfo set state2=?,state1=? where id='" + di + "'";
				numbersql="update maintenancerecordsinfo set pnumber='"+pnumber+"' where id='"+di+"'";
				OpLogInfoTools.insertOpLogInfo(employNo, "保养润滑=>保养管理=>未完成操作");
			}
			   int l=Tools.executeUpdate(numbersql);
			int i = Tools.executeUpdate(sql3, state2, "0");
			if (i > 0  && l>0) {
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
		}

	}

}
