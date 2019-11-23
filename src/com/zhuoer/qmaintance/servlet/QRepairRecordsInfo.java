package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.qmaintance.beans.MaintenanceRecords;
import com.zhuoer.qmaintance.beans.RepairRecordsInfo;
import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;
import com.zhuoer.qmaintance.utils.RepairRecordsTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**根据不同条件条件返回维修记录信息
 * Servlet implementation class QRepairRecordsInfo
 */
@WebServlet("/QRepairRecordsInfo")
public class QRepairRecordsInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域问题
		
		//��������
		String startTime=request.getParameter("startdate");
		String endTime=request.getParameter("enddate");
		String deviceNo=request.getParameter("deviceNo");
		String customerNo=request.getParameter("customerNo");
		String spage = request.getParameter("spage");
		String state=request.getParameter("state");
		String finish=request.getParameter("finish");
//		String startTime="2018-06-12";
//		String endTime="2018-06-20";
//		String deviceNo="11";
//		String customerNo="2";
//		String spage = "1";
		int page = Integer.valueOf(spage);
		
		//多条件查询
		String sql="select repairrecordsinfo.*,deviceinfo.name as deviceName from deviceinfo,repairrecordsinfo where  deviceinfo.no=repairrecordsinfo.deviceNo";
		if(deviceNo!=null&& !deviceNo.equals(""))
		{
			sql+=" and repairrecordsinfo.deviceNo='"+deviceNo+"'";
		}
		if(customerNo!=null&& !customerNo.equals(""))
		{
			sql+=" and deviceinfo.customerNo='"+customerNo+"'";
		}
		
		if(startTime!=null&& !startTime.equals(""))
		{
			sql+=" and repairDate>='"+startTime+"'";
		}
		if(endTime!=null&& !endTime.equals(""))
		{
			sql+=" and repairDate<='"+endTime+"'";
		}
		if(state!=null && !state.equals(""))
		{
			sql+=" and state = '"+state+"'";
		}
		if(finish!=null && !finish.equals(""))
		{
			sql+=" and finish = '"+finish+"'";
		}
		sql+=" limit "+15*(page-1)+",15";
		
		//查询维修记录表中的数据
				ArrayList records = (ArrayList<RepairRecordsInfo>) RepairRecordsTools.executeQuary(sql);
				//查询分页总页数total
				String sql2=sql.replace("repairrecordsinfo.*", "count(*)");
				
				sql2 = sql2.split("limit")[0];
				int selectCount = MaintenanceRecordsTools.selectCount(sql2)-1;
				int total=((int)selectCount/15)+1;
				Map map=new HashMap();
				map.put("totalpage", total);
				records.add(map);
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();			
				String json=gson.toJson(records);				
				
				response.getWriter().write(json);
	}

}
