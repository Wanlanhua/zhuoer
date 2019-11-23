package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 详情页面
 */
@WebServlet("/PRepairrecordsInfoX")
public class PRepairrecordsInfoX extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> arrlist = new ArrayList<String>();
	JSONArray jsonArray;
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public PRepairrecordsInfoX() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

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

		String idd = jsonObject.getString("id");
		String deviceNo = jsonObject.getString("deviceNo");
		jsonObject.clear();
		String sql = "select * from repairinfo where id='" + idd + "'";
		List<Map<String, Object>> rs = RepairInfoTools.executeQuary(sql);
//		ResultSet rs = Tools.executeQuary(sql);
		String sql2 = "select * from deviceinfo where no='" + deviceNo + "'";
		List<Map<String, Object>> rs2 = RepairInfoTools.executeQuary(sql2);
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i1 = 0; i1 < rs.size(); i1++) {
			Map<String, Object> teMap = new HashMap<>();
			String className = "";
			try {
				className = rs.get(i1).get("className").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("className", className);
			
			String person = "";
			try {
				person = rs.get(i1).get("person").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("person", person);
			
			String date = "";
			try {
				date = rs.get(i1).get("date").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("date", date);
			
			String content = "";
			try {
				content = rs.get(i1).get("content").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("content", content);
			
			String source = "";
			try {
				source = rs.get(i1).get("source").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("source", source);
			
			String auditLevel = "";
			try {
				auditLevel = rs.get(i1).get("auditLevel").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("auditLevel", auditLevel);
			
			String path1 = "";
			try {
				path1 = rs.get(i1).get("path1").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("path1", path1);
			
			String path2 = "";
			try {
				path2 = rs.get(i1).get("path2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("path2", path2);
			
			String path3 = "";
			try {
				path3 = rs.get(i1).get("path3").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("path3", path3);
			
			String path4 = "";
			try {
				path4 = rs.get(i1).get("path4").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("path4", path4);
			
			String path5 = "";
			try {
				path5 = rs.get(i1).get("path5").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("path5", path5);
			
			String person2 = "";
			try {
				person2 = rs.get(i1).get("person2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("person2", person2);
			
			String repairDepartment = "";
			try {
				repairDepartment = rs.get(i1).get("repairDepartment").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("repairDepartment", repairDepartment);
			
			String repairTime = "";
			try {
				repairTime = rs.get(i1).get("repairTime").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("repairTime", repairTime);
			
			String faultPhenomenon = "";
			try {
				faultPhenomenon = rs.get(i1).get("faultPhenomenon").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("faultPhenomenon", faultPhenomenon);
			String maintenancePerson = "";
			try {
				maintenancePerson = rs.get(i1).get("maintenancePerson").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("maintenancePerson", maintenancePerson);
			
			java.sql.Date s =  (java.sql.Date) rs.get(i1).get("repairDate");
			String repairDate = "";
			try {
				 repairDate = sdf2.format(s);
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("repairDate", repairDate);
			for(int j=0;j<rs2.size();j++)
			{
				String customerNo = "";
				try {
					customerNo = rs2.get(j).get("customerNo").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("customerNo", customerNo);
				String no = "";
				try {
					no = rs2.get(j).get("no").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("no", no);
				
				String name = "";
				try {
					name = rs2.get(j).get("name").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("name", name);
				
				String type = "";
				try {
					type = rs2.get(j).get("type").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("type", type);
				
				String address = "";
				try {
					address = rs2.get(j).get("address").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("address", address);
				
				String mark = "";
				try {
					mark = rs2.get(j).get("mark").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("mark", mark);
				
				Timestamp sss = (Timestamp) rs2.get(j).get("createDate");
				String createDate = "";
				try {
					createDate =sdf2.format(sss);
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("createDate", createDate);
				list.add(teMap);
			}
			try {
				JSONArray jsonArray = JSONArray.fromObject(list);
				response.getWriter().write(jsonArray.toString());
			} catch (Exception e) {
				// TODO: handle exception
				String mString = e.getMessage();

			}
		}
//		ResultSet rs2 = Tools.executeQuary(sql2);
//		arrlist.clear();
//			while (rs.next()) {
//				JSONObject jo1 = new JSONObject();
//				jo1.put("className", rs.getString("className"));
//				jo1.put("deviceNo", rs.getString("deviceNo"));
//				jo1.put("person", rs.getString("person"));
//				jo1.put("date", rs.getString("date"));
//				jo1.put("content", rs.getString("content"));
//				jo1.put("source", rs.getString("source"));
//				jo1.put("auditLevel", rs.getString("auditLevel"));
//				jo1.put("path1", rs.getString("path1"));
//				jo1.put("path2", rs.getString("path2"));
//				jo1.put("path3", rs.getString("path3"));
//				jo1.put("path4", rs.getString("path4"));
//				jo1.put("path5", rs.getString("path5"));
//				jo1.put("person2", rs.getString("person2"));
//				jo1.put("repairDepartment", rs.getString("repairDepartment"));
//				jo1.put("repairTime", rs.getString("repairTime"));
//				jo1.put("faultPhenomenon", rs.getString("faultPhenomenon"));
//				java.sql.Date s = rs.getDate("repairDate");
//				String repairDate = sdf2.format(s);
//				jo1.put("repairDate", repairDate);
//				while (rs2.next()) {
//					jo1.put("customerNo", rs2.getString("customerNo"));
//					jo1.put("no", rs2.getString("no"));
//					jo1.put("name", rs2.getString("name"));
//					jo1.put("type", rs2.getString("type"));
//					jo1.put("address", rs2.getString("address"));
//					jo1.put("mark", rs2.getString("mark"));
//					Timestamp sss = rs2.getTimestamp("createDate");
//					String createDate = sdf2.format(sss);
//					jo1.put("createDate", createDate);
//				}
//				String jo1s = jo1.toString();
//				arrlist.add(jo1s);
//			}
//			jsonArray = JSONArray.fromObject(arrlist);
//			response.getWriter().write(jsonArray.toString());

//			try {
//				if(Tools.ps!=null)
//				{
//					Tools.ps.close();
//				}
//				if(Tools.ps!=null)
//				{
//					Tools.ps.close();
//				}
			
//	}catch (Exception e) {
//		// TODO: handle exception
//	}

	}
}
