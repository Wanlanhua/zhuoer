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

/**
 * 扫一扫 接受设备编号，state1和contenttype进行查询
 */
@WebServlet("/PMaintenanceS")
public class PMaintenanceS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> arrlist = new ArrayList<String>();
	JSONArray jsonArray;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PMaintenanceS() {
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
		String deviceNo = jsonObject.getString("deviceNo");
		String state1 = jsonObject.getString("state1");
		String contentTpye = jsonObject.getString("contentType");
		String no = jsonObject.getString("no");
		jsonObject.clear();

		if (state1.equals("未被领取")) {
			state1 = "0";
		} else {
			state1 = "1";
		}

		//新增：：根据编号查到所属部门
				String emsql="select department from employeeinfo where no='"+no+"'";
				List<Map<String, Object>> listem=RepairInfoTools.executeQuary(emsql);
				String department="";
				for (int i = 0; i < listem.size(); i++) {
					try {
						department=listem.get(i).get("department").toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
		String sql = "";
		if (contentTpye.contains("润滑")) {
			sql = "select l.* from lubricationrecordsinfo l,deviceinfo d where l.deviceNo='" + deviceNo + "' and l.state1='" + state1 + "'  and d.customerNo='"+department+"'";
		} else {
			sql = "select m.* from maintenancerecordsinfo m,deviceinfo d  where m.deviceNo='" + deviceNo + "' and m.state1='" + state1 + "'  and d.customerNo='"+department+"'";
		}
		List<Map<String, Object>> listW = RepairInfoTools.executeQuary(sql);
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < listW.size(); i++) {

			Map<String, Object> teMap = new HashMap<>();
			String id = "";
			try {
				id = listW.get(i).get("id").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("id", id);
			String deviceNo1 = "";
			try {
				deviceNo1 = listW.get(i).get("deviceNo").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("deviceNo", deviceNo1);
			String content = "";
			try {
				content = listW.get(i).get("content").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("content", content);

		

			String reason = "";
			try {
				reason = listW.get(i).get("reason").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("reason", reason);

			String standard = "";
			try {
				standard = listW.get(i).get("standard").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("standard", standard);

			String security = "";
			try {
				security = listW.get(i).get("security").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("security", security);

			String tool = "";
			try {
				tool = listW.get(i).get("tool").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("tool", tool);

			String pnumber = "";
			try {
				pnumber = listW.get(i).get("pnumber").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("pnumber", pnumber);
			String mnumber = "";
			try {
				mnumber = listW.get(i).get("mnumber").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("mnumber", mnumber);

			String cycle = "";
			try {
				cycle = listW.get(i).get("cycle").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("cycle", cycle);

			String maintenanceDate = "";
			try {
				maintenanceDate = listW.get(i).get("maintenanceDate").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("maintenanceDate", maintenanceDate);

			String maintenancePerson = "";
			try {
				maintenancePerson = listW.get(i).get("maintenancePerson").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("maintenancePerson", maintenancePerson);

			String state11 = "";
			try {
				state11 = listW.get(i).get("state1").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("state1", state11);

			String date1 = "";
			try {
				date1 = listW.get(i).get("date1").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("date1", date1);

			String state2 = "";
			try {
				state2 = listW.get(i).get("state2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("state2", state2);

			String person2 = "";
			try {
				person2 = listW.get(i).get("person2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("person2", person2);

			String date2 = "";
			try {
				date2 = listW.get(i).get("date2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("date2", date2);

			String state = "";
			try {
				state = listW.get(i).get("state").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("state", state);

			String auditOpinion1 = "";
			try {
				auditOpinion1 = listW.get(i).get("auditOpinion1").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("auditOpinion1", auditOpinion1);

			String auditOpinion2 = "";
			try {
				auditOpinion2 = listW.get(i).get("auditOpinion2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("auditOpinion2", auditOpinion2);

			String auditOpinion4 = "";
			try {
				auditOpinion4 = listW.get(i).get("auditOpinion4").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("auditOpinion4", auditOpinion4);

			String mark = "";
			try {
				mark = listW.get(i).get("mark").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			teMap.put("mark", mark);
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

}
