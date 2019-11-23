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

/**
 * 
 */
@WebServlet("/PRepairrecordsInfoU")
public class PRepairrecordsInfoU extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> list2 = new ArrayList<String>();
	List<String> list3 = new ArrayList<String>();
	List<String> list6 = new ArrayList<String>();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PRepairrecordsInfoU() {
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
		String date1 = jsonObject.getString("date1");
		String deviceNo = jsonObject.getString("deviceNo");
		String no = jsonObject.getString("no");
		String state2=jsonObject.getString("state2");
		jsonObject.clear();
		//根据员工编号获取姓名
		String namesql="select * from employeeinfo where no='"+no+"'";
		// 获取当前的时间
		Date day = new Date();
		String Rday = df.format(day);

		//新增sql
		 String xsql="select r.deviceNo,r.repairDate,r.no,d.name from repairrecordsinfo  r,deviceinfo  d where r.no='"+di+"' and d.no=r.deviceNo";
		String sql = "select * from repairinfo where id='" + di + "'";
		String sql2 = "SELECT a.maintenancePerson, b.name FROM repairinfo AS a,deviceinfo AS b WHERE a.id='" + di
				+ "' and a.deviceNo=b.no";
		String sql3 = "select * from employeeinfo where department in(select department from employeeinfo where no='"
				+ no + "')";
		String sql6 = "SELECT  a.no,a.deviceNo,b.name FROM dandfinfo AS a,devicefittinginfo AS b WHERE a.no=b.no and a.deviceNo in(select deviceNo from dandfinfo where deviceNo1='"
				+ deviceNo + "')";
		List<Map<String, Object>> Urs = RepairInfoTools.executeQuary(sql);
		List<Map<String, Object>> rs2 = RepairInfoTools.executeQuary(sql2);
		List<Map<String, Object>> rs3 = RepairInfoTools.executeQuary(sql3);
		List<Map<String, Object>> rs6 = RepairInfoTools.executeQuary(sql6);
		List<Map<String, Object>> namers = RepairInfoTools.executeQuary(namesql);
		List<Map<String, Object>> xrs = RepairInfoTools.executeQuary(xsql);
		
			//员工名字
			String noname="";
			for (int i = 0; i < namers.size(); i++) {
				try {
					noname = namers.get(i).get("name").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			String state1 = "";
			//审核状态
			String state = "0";
			String source="";
			int i = 0;
			for (int i1 = 0; i1 < Urs.size(); i1++) {
				try {
					state1 = Urs.get(i1).get("state1").toString();
					state = Urs.get(i1).get("state").toString();
					source= Urs.get(i1).get("source").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			List<Map<String, Object>> list2 = new ArrayList<>();
			List<Map<String, Object>> list3 = new ArrayList<>();
			List<Map<String, Object>> list6 = new ArrayList<>();
			// 返回员工编号和姓名
			for (int i1 = 0; i1 < rs3.size(); i1++) {
				Map<String, Object> teMap2 = new HashMap<>();
				String no1="";
				try {
					no1= rs3.get(i1).get("no").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap2.put("no", no1);
				
				String name="";
				try {
					name= rs3.get(i1).get("name").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap2.put("ename", name);
				list3.add(teMap2);
			}
			JSONArray ja3 = JSONArray.fromObject(list3);
			date1 = date1.substring(date1.lastIndexOf("-") + 3);
			for (int i1 = 0; i1 < rs2.size(); i1++) {
				Map<String, Object> teMap2 = new HashMap<>();
			
				teMap2.put("deviceNo", deviceNo);
				
				String maintenancePerson="";
				try {
					maintenancePerson= rs2.get(i1).get("maintenancePerson").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap2.put("acceptancePerson", maintenancePerson);
				
				
				String name="";
				try {
					name= rs2.get(i1).get("name").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap2.put("jname", name);
				teMap2.put("startTime", date1);
				teMap2.put("result", "1");
				list2.add(teMap2);
			}
			JSONArray ja2 = JSONArray.fromObject(list2);
			// 返回配件编号和设备部位和配件名称
			for (int i1 = 0; i1 < rs6.size(); i1++) {
				Map<String, Object> teMap6 = new HashMap<>();
			
				String deviceNo1="";
				try {
					deviceNo1= rs6.get(i1).get("deviceNo").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap6.put("dname", deviceNo1);
				
				String name="";
				try {
					name= rs6.get(i1).get("name").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap6.put("Dename", name);
				
				String no1="";
				try {
					no1= rs6.get(i1).get("no").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap6.put("dno", no1);
				list6.add(teMap6);
			}
			JSONArray ja6 = JSONArray.fromObject(list6);
			
			
			//返回4个值
			String xdeviceNo="";
			String xrepairDates="";
			String xrepairDate="";
			String xid="";
			String xname="";
			
			for (int i1 = 0; i1 < xrs.size(); i1++) {
			
			try {
				xdeviceNo= xrs.get(i1).get("deviceNo").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				xrepairDates= xrs.get(i1).get("repairDate").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				xid= xrs.get(i1).get("no").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				xname= xrs.get(i1).get("name").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			xrepairDate=xrepairDates.substring(0, xrepairDates.indexOf("."));
			}
			JSONObject zo = new JSONObject();
			if (state1.equals("0")) {
				state1 = "1";
				if(state2.equals("0"))
				{
					String Psql2 = "update repairinfo set state1=?,maintenancePerson=?,date1=? where id='" + di + "'";
					i = Tools.executeUpdate(Psql2, state1, noname, Rday);
					OpLogInfoTools.insertOpLogInfo(no, 
							"维修管理=>认领操作");
				}else
				{
					String Psql2 = "update repairinfo set state1=?,person2=?,date2=? where id='" + di + "'";
					i = Tools.executeUpdate(Psql2,state1, noname, Rday);
					OpLogInfoTools.insertOpLogInfo(no, 
							"维修管理=>认领操作");
				}
				if (i > 0) {
										
					zo.put("source", source);
					zo.put("state1", "0");
					zo.put("state", state);
					zo.put("RepairInfo", ja2.toString());
					zo.put("EmployeeInfo", ja3.toString());
					zo.put("kv", ja6.toString());
					zo.put("deviceNo", xdeviceNo);
					zo.put("repairDate", xrepairDate);
					zo.put("id", xid);
					zo.put("name", xname);
					response.getWriter().write(zo.toString());
					System.out.println(zo.toString());

				} else {
					JSONObject jo2 = new JSONObject();
					jo2.put("result", "2");
					jo2.put("msg", "失败");
					String string3 = jo2.toString();
					response.getWriter().write(string3);
				
				}

			} else {
				zo.put("source", source);
				zo.put("state1", "1");
				zo.put("state", state);
				zo.put("RepairInfo", ja2.toString());
				zo.put("EmployeeInfo", ja3.toString());
				zo.put("kv", ja6.toString());
				zo.put("deviceNo", xdeviceNo);
				zo.put("repairDate", xrepairDate);
				zo.put("id", xid);
				zo.put("name", xname);
				zo.put("msg", "已被领取");
				String s1 = zo.toString();
				response.getWriter().write(s1);
			}
		
		
			
			
		}

	}


