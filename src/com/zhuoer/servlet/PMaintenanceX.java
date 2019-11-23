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
@WebServlet("/PMaintenanceX")
public class PMaintenanceX extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public PMaintenanceX() {
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
		String contentTpye=jsonObject.getString("contentType");
		jsonObject.clear();
		String sql="";
		if(contentTpye.contains("润滑"))
		{
			 sql = "select * from lubricationrecordsinfo where id='" + idd + "'";
		}else
		{
			 sql = "select * from maintenancerecordsinfo where id='" + idd + "'";
		}
		List<Map<String, Object>> rs = RepairInfoTools.executeQuary(sql);
		String sql2 = "select * from deviceinfo where no='" + deviceNo + "'";
		List<Map<String, Object>> rs2 = RepairInfoTools.executeQuary(sql2);
		List<Map<String, Object>> list = new ArrayList<>();
		//设备保养信息
		for (int i1 = 0; i1 < rs.size(); i1++) {
			Map<String, Object> teMap = new HashMap<>();
			String content = "";
			try {
				content = rs.get(i1).get("content").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("content", content);
			
			String deviceNos = "";
			try {
				deviceNos = rs.get(i1).get("deviceNo").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("deviceNo", deviceNos);
			String auditOpinion4 = "";
			try {
				auditOpinion4 = rs.get(i1).get("auditOpinion4").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("auditOpinion4", auditOpinion4);
			String cycle = "";
			try {
				cycle = rs.get(i1).get("cycle").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("cycle", cycle);
			
			String maintenancePerson = "";
			try {
				maintenancePerson = rs.get(i1).get("maintenancePerson").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("maintenancePerson", maintenancePerson);
			
			String date1 = "";
			try {
				date1 = rs.get(i1).get("date1").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("date1", date1);
			
			
			String maintenanceDate = "";
			try {
				maintenanceDate = rs.get(i1).get("maintenanceDate").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("maintenanceDate", maintenanceDate);
			
             //设备基本信息
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
				System.out.println(jsonArray.toString());
				response.getWriter().write(jsonArray.toString());
			} catch (Exception e) {
				// TODO: handle exception
				String mString = e.getMessage();

			}
		}
	}
}
