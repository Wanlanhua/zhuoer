package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.qmaintance.utils.RepairRecordsTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 接收员工编号查找对应员工下部门的所有区域 Servlet implementation class QSelectArea
 */
@WebServlet("/QSelectArea")
public class QSelectArea extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 接收员工编号
		StringBuffer json1 = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json1.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String no = jsonObject.getString("no");
//		String no="2016";
		// 联表查询,查询字段取别名
		String sql = "select e.area from AreaInfo a,EmployeeInfo e where e.no='" + no
				+ "' and e.department=a.departmentid";
		List<Map<String, Object>> list2 = new ArrayList<>();
		List<Map<String, Object>> listW = RepairRecordsTools.executeQuary(sql);
		// 添加全部
		Map<String, Object> teMap2 = new HashMap<>();
		teMap2.put("Ano", "");
		teMap2.put("Aname", "全部");
		list2.add(teMap2);
		//第一 查出 员工所在的区域
		String employ_area="";
		for (int j = 0; j < listW.size(); j++) {
			employ_area=listW.get(0).get("area").toString();
		}
		//拆分
		String[] areaarr=employ_area.split("-");
		//对应查出 区域表no
		for(int i=0;i<areaarr.length;i++) {
			String areainfo_sql="select no from areainfo where name='"+areaarr[i]+"'";
			List<Map<String, Object>> areainfo_list = RepairRecordsTools.executeQuary(areainfo_sql);
			for(int j=0;j<areainfo_list.size();j++) {
				Map<String, Object> teMap=new HashMap<>();
				String nos="";
				try {
					nos=areainfo_list.get(j).get("no").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("Ano", nos);
				teMap.put("Aname", areaarr[i]);
				list2.add(teMap);
			}
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(list2);
			System.out.println(jsonArray.toString());
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
			String mString = e.getMessage();

		}
		


	}

}
