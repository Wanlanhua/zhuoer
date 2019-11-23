
package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zhuoer.dao.Tools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 获取员工编号，返给安卓设备名称和设备编号
 */
@WebServlet("/PMaintenance")
public class PMaintenance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> arrlist;
	
	JSONArray jsonArray = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PMaintenance() {
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
			while ((line = reader.readLine()) != null) {
				json1.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 System.out.println("dasdsa");
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String Yno = jsonObject.getString("no");
		jsonObject.clear();
		String sql = "";
		String sql2 = "select * from employeeinfo where no='" + Yno + "'";
		List<Map<String, Object>> listW2=RepairInfoTools.executeQuary(sql2);
	
			String area="";
			String department="";
			for(int i=0;i<listW2.size();i++)
			{
				
				try {
					department=listW2.get(i).get("department").toString();
					area=listW2.get(i).get("area").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
//			while(rs2.next())
//			{
//				 department=rs2.getString("department");
//				area=rs2.getString("area");
//			}
			if (area.equals("") || area == null) {
				String sqlss = "SELECT a.no,a.name FROM deviceinfo As a,employeeinfo AS b,areainfo AS c WHERE b.no='" + Yno
					+ "' and b.department=c.departmentid and c.no=a.area";
				List<Map<String, Object>> listW=RepairInfoTools.executeQuary(sqlss);
				List<Map<String, Object>> list=new ArrayList<>();
				for(int i=0;i<listW.size();i++)
				{
					Map<String, Object> teMap=new HashMap<>();
					String no="";
					try {
						no=listW.get(i).get("no").toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
					teMap.put("no", no);
					String name="";
					try {
						name=listW.get(i).get("name").toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
					teMap.put("name", name);
					list.add(teMap);
				}
				try {
					JSONArray jsonArray = JSONArray.fromObject(list);
					response.getWriter().write(jsonArray.toString());
				} catch (Exception e) {
					// TODO: handle exception
					String mString=e.getMessage();
					
				}
			}else
			{
				String[] areaarr=area.split("-");
				
				
				List<Map<String, Object>> list2=new ArrayList<>();
				
			for(int i=0;i<areaarr.length;i++)
			{
				String sqls = "SELECT a.no,a.name FROM deviceinfo As a,employeeinfo AS b,areainfo AS c WHERE b.no='"+Yno+"' and b.department=c.departmentid and '"+areaarr[i]+"'=c.name and c.no=a.area";
				List<Map<String, Object>> listW=RepairInfoTools.executeQuary(sqls);
				for(int j=0;j<listW.size();j++)
				{
					Map<String, Object> teMap=new HashMap<>();
					String no="";
					try {
						no=listW.get(j).get("no").toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
					teMap.put("no", no);
					String name="";
					try {
						name=listW.get(j).get("name").toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
					teMap.put("name", name);
					list2.add(teMap);
				}
			}
			try {
				JSONArray jsonArray = JSONArray.fromObject(list2);
				response.getWriter().write(jsonArray.toString());
			} catch (Exception e) {
				// TODO: handle exception
				String mString=e.getMessage();
				
			}
			}
			
		

	}

}
