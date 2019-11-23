package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
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
 * 
 */
@WebServlet("/PRepairrecordsInfoWW")
public class PRepairrecordsInfoWW extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PRepairrecordsInfoWW() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		JSONObject js = JSONObject.fromObject(json1.toString());
		String di = js.getString("id");
		String state2 = js.getString("state2");
		js.clear();	
	
		if(state2.equals("完成"))
		{
			state2="1";
		}else
		{
			state2="2";
		}
		String sql2 = "update repairinfo set state2=?,state1=? where id='" + di + "'";
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
		
		int i = Tools.executeUpdate(sql2, state2,"0");
		if (i > 0) {
			OpLogInfoTools.insertOpLogInfo(employNo, 
					"维修管理=>未完成操作");
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
