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
 * 接受设备编号和state1和state2 进行查询
 */
@WebServlet("/PRepairrecordsInfoQ")
public class PRepairrecordsInfoQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> arrlist = new ArrayList<String>();
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PRepairrecordsInfoQ() {
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
//		接受数据
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		System.out.println(json1.toString());
		String deviceNo = jsonObject.getString("deviceNo");
		String state1 = jsonObject.getString("state1");
		String state2 = jsonObject.getString("state2");
//		String deviceNo = "";
//		String state1 ="1";
//		String state2 = "1";
		String no=""; 
		String Aname="";
		try {
			Aname=jsonObject.getString("Aname");
			no=jsonObject.getString("no");
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (state1.equals("未领取")) {
			state1 = "0";
		} else {
			state1 = "1";
		}

		if (state2.equals("已完成")) {
			state2 = "1";
		} else if (state2.equals("计划")) {
			state2 = "2";
		} else if (state2.equals("未完成")) {
			state2 = "0";
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
		int blob=0;
		//存放当 全部的时候的数据
		List<Map<String, Object>> Quanbu_list=new ArrayList<>();
		String sql = "";
		if(!deviceNo.equals(""))
		{//判断该设备编号是不是在员工所在的区域
			// 第一设备基本信息表和 区域表查出该设备所在的区域
			String Dasql="select a.name from deviceinfo d,areainfo a where d.no='"+deviceNo+"' and d.area=a.no";
			List<Map<String, Object>>  Dalist=RepairInfoTools.executeQuary(Dasql);
			String Area_name="";
			for (int i = 0; i < Dalist.size(); i++) {
				try {
					Area_name=Dalist.get(0).get("name").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			//第二 查出在员工所在的区域
			String Employsql="select area from employeeinfo where no='"+no+"'";
			List<Map<String, Object>>  Employlist=RepairInfoTools.executeQuary(Employsql);
			String Employ_area="";
			for (int i = 0; i < Employlist.size(); i++) {
				try {
					Employ_area=Employlist.get(0).get("area").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if(Employ_area.contains(Area_name))
			{
				if (state2.equals("") || state2 == null) {
					sql = "select a.*,d.name from repairinfo a,deviceinfo d where a.deviceNo='" + deviceNo + "' and a.state1='" + state1 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
				} else {
					sql = "select a.*,d.name from repairinfo a,deviceinfo d where a.deviceNo='" + deviceNo + "' and a.state1='" + state1 + "' and a.state2='"
							+ state2 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
				}
			}else
			{
				//返回json
				JSONObject cunwu=new JSONObject();
				cunwu.put("result", "2");
				cunwu.put("msg", "该设备不在该员工的区域内");
				response.getWriter().write(cunwu.toString());
				return;
			}
			
		}else
		{

			if(Aname.equals("全部"))
			{//设置blob为1
				blob=1;
				//第一 查出在员工所在的区域 
				String Employsql="select area from employeeinfo where no='"+no+"'";
				List<Map<String, Object>>  Employlist=RepairInfoTools.executeQuary(Employsql);
				String Employ_area="";
				for (int i = 0; i < Employlist.size(); i++) {
					try {
						Employ_area=Employlist.get(0).get("area").toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				//第二 进行拆分
				String[] Employ_areaS=Employ_area.split("-");
				//第三进行循环
				String LastAreainfo_sql="";
				for(int i=0;i<Employ_areaS.length;i++)
				{
					if (state2.equals("") || state2 == null) {
						LastAreainfo_sql = "select a.*,d.name from repairinfo a,deviceinfo d where  a.deviceNo in(select no from deviceinfo where area in (select no from areainfo where name='"+Employ_areaS[i]+"')) and a.state1='" + state1 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
					} else {
						LastAreainfo_sql = "select a.*,d.name from repairinfo a,deviceinfo d where a.deviceNo in(select no from deviceinfo where area in (select no from areainfo where name='"+Employ_areaS[i]+"')) and a.state1='" + state1 + "' and a.state2='"
								+ state2 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
					}
					List<Map<String, Object>> LastAreainfo_list=RepairInfoTools.executeQuary(LastAreainfo_sql);
					if(LastAreainfo_list.size()>0)
					{
						for(int j=0;j<LastAreainfo_list.size();j++)
						{
							Map<String, Object> teMap=new HashMap<>();
							//新增设备名称
							String name="";
							try {
								name=LastAreainfo_list.get(j).get("name").toString();
							} catch (Exception e) {
								// TODO: handle exception
							}
							teMap.put("name", name);
							
							
							String id="";
							try {
								id=LastAreainfo_list.get(j).get("id").toString();
							} catch (Exception e) {
								// TODO: handle exception
							}
							teMap.put("id", id);
							String deviceNo1="";
							try {
								deviceNo1=LastAreainfo_list.get(j).get("deviceNo").toString();
							} catch (Exception e) {
								// TODO: handle exception
							}
							teMap.put("deviceNo", deviceNo1);
							String state11="";
							try {
								state11=LastAreainfo_list.get(j).get("state1").toString();
							} catch (Exception e) {
								// TODO: handle exception
							}
							teMap.put("state1", state11);
							String state21="";
							try {
								state21=LastAreainfo_list.get(j).get("state2").toString();
							} catch (Exception e) {
								// TODO: handle exception
							}
							teMap.put("state2", state21);
							
							String content="";
							try {
								content=LastAreainfo_list.get(j).get("faultPhenomenon").toString();
							} catch (Exception e) {
								// TODO: handle exception
							}
							teMap.put("content", content);
							Quanbu_list.add(teMap);
						}
					}
				}
				
			}else
			{
				if (state2.equals("") || state2 == null) {
					sql = "select a.*,d.name from repairinfo a,deviceinfo d where  a.deviceNo in(select no from deviceinfo where area in (select no from areainfo where name='"+Aname+"')) and a.state1='" + state1 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
				} else {
					sql = "select a.*,d.name from repairinfo a,deviceinfo d where a.deviceNo in(select no from deviceinfo where area in (select no from areainfo where name='"+Aname+"')) and a.state1='" + state1 + "' and a.state2='"
							+ state2 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
				}
			}
		}
//		if(!deviceNo.equals(""))
//		{
//			if (state2.equals("") || state2 == null) {
//				sql = "select a.*,d.name from repairinfo a,deviceinfo d where a.deviceNo='" + deviceNo + "' and a.state1='" + state1 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
//			} else {
//				sql = "select a.*,d.name from repairinfo a,deviceinfo d where a.deviceNo='" + deviceNo + "' and a.state1='" + state1 + "' and a.state2='"
//						+ state2 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
//			}
//		}else
//		{
//
//			if(Aname.equals("全部"))
//			{
//		
//				if (state2.equals("") || state2 == null) {
//					sql = "select a.*,d.name from repairinfo a,deviceinfo d where  a.deviceNo in(select no from deviceinfo where area in (select no from areainfo)) and a.state1='" + state1 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
//				} else {
//					sql = "select a.*,d.name from repairinfo a,deviceinfo d where a.deviceNo in(select no from deviceinfo where area in (select no from areainfo)) and a.state1='" + state1 + "' and a.state2='"
//							+ state2 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
//				}
//			}else
//			{
//				if (state2.equals("") || state2 == null) {
//					sql = "select a.*,d.name from repairinfo a,deviceinfo d where  a.deviceNo in(select no from deviceinfo where area in (select no from areainfo where name='"+Aname+"')) and a.state1='" + state1 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
//				} else {
//					sql = "select a.*,d.name from repairinfo a,deviceinfo d where a.deviceNo in(select no from deviceinfo where area in (select no from areainfo where name='"+Aname+"')) and a.state1='" + state1 + "' and a.state2='"
//							+ state2 + "' and a.deviceNo=d.no and d.customerNo='"+department+"'";
//				}
//			}
//		}
		if(blob==1)
		{
			try {
				JSONArray jsonArray = JSONArray.fromObject(Quanbu_list);
				JSONObject zq=new JSONObject();
				zq.put("result", "1");
				zq.put("msg", "成功");
				zq.put("contents", jsonArray.toString());
				response.getWriter().write(zq.toString());
			} catch (Exception e) {
				// TODO: handle exception
				String mString=e.getMessage();
				
			}
		}else
		{
			List<Map<String, Object>> listW=RepairInfoTools.executeQuary(sql);
			List<Map<String, Object>> list=new ArrayList<>();
			for (int i = 0; i < listW.size(); i++) {
				Map<String, Object> teMap=new HashMap<>();
				//新增设备名称
				String name="";
				try {
					name=listW.get(i).get("name").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("name", name);
				
				
				String id="";
				try {
					id=listW.get(i).get("id").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("id", id);
				String deviceNo1="";
				try {
					deviceNo1=listW.get(i).get("deviceNo").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("deviceNo", deviceNo1);
				String state11="";
				try {
					state11=listW.get(i).get("state1").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("state1", state11);
				String state21="";
				try {
					state21=listW.get(i).get("state2").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("state2", state21);
				String content="";
				try {
					content=listW.get(i).get("faultPhenomenon").toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				teMap.put("content", content);
				list.add(teMap);
				
			}
			try {
				JSONArray jsonArray = JSONArray.fromObject(list);
				JSONObject zq=new JSONObject();
				zq.put("result", "1");
				zq.put("msg", "成功");
				zq.put("contents", jsonArray.toString());
				response.getWriter().write(zq.toString());
			} catch (Exception e) {
				// TODO: handle exception
				String mString=e.getMessage();
				
			}
		}
		
		
		
			
//		ResultSet rs = Tools.executeQuary(sql);
//		arrlist.clear();
//		try {
//			while (rs.next()) {
//				JSONObject jo1 = new JSONObject();
//				int i = rs.getInt("id");
//				String idd = Integer.toString(i);
//				jo1.put("id", idd);
//				jo1.put("deviceNo", rs.getString("deviceNo"));
//				jo1.put("state1", rs.getString("state1"));
//				jo1.put("state2", rs.getString("state2"));
//				jo1.put("content", rs.getString("content"));
//				String jo1s = jo1.toString();
//				arrlist.add(jo1s);
//			}
//			JSONArray jsonArray = JSONArray.fromObject(arrlist);
//			response.getWriter().write(jsonArray.toString());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if(Tools.ps!=null)
//				{
//					Tools.ps.close();
//				}
//				if(rs!=null)
//				{
//					rs.close();
//				}
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}

}
