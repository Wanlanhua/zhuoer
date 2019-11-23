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

import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Pdepartname
 */
@WebServlet("/Pdepartname")
public class Pdepartname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pdepartname() {
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
		//接受数据
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String no = "";
		try {
			no=jsonObject.getString("no");
		} catch (Exception e) {
			// TODO: handle exception
		}
		jsonObject.clear();
		String sql="select * from employeeinfo where no='"+no+"'";
		List<Map<String, Object>> listW = RepairInfoTools.executeQuary(sql);
		String department = "";
		for (int i = 0; i < listW.size(); i++) {
			try {
				department = listW.get(i).get("department").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		String departsql="select * from employeeinfo where department='"+department+"'";
		List<Map<String, Object>> listd= RepairInfoTools.executeQuary(departsql);
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < listd.size(); i++) {
			String name="";
			Map<String, Object> teMap = new HashMap<>();
			try {
				name = listd.get(i).get("name").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("persons", name);
			list.add(teMap);
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.out.println(jsonArray.toString());
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
			String mString = e.getMessage();
		}
		
		
	}

}
