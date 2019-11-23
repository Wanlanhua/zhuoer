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
import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

/**根据条件返回报修表信息
 * Servlet implementation class QRepairInfo
 */
@WebServlet("/QRepairInfo")
public class QRepairInfo extends HttpServlet {
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
		
		//接收数据
		String startdate=request.getParameter("startdate");
		String enddate=request.getParameter("enddate");
		String state=request.getParameter("state");
		String state1=request.getParameter("state1");
		String state2=request.getParameter("state2");
		String deviceNo=request.getParameter("deviceNo");
		String customerNo=request.getParameter("customerNo");
		String spage = request.getParameter("spage");
		int page = Integer.valueOf(spage);
		
		
		String sql="select repairinfo.*,deviceinfo.name as deviceName from deviceinfo,repairinfo where  deviceinfo.no=repairinfo.deviceNo";
		if((deviceNo!=null && !deviceNo.equals("")))
		{
			sql+=" and repairinfo.deviceNo='"+deviceNo+"'";
		}
		if(customerNo!=null && !customerNo.equals(""))
		{
			sql+=" and deviceinfo.customerNo='"+customerNo+"'";
		}
		if(state!=null && !state.equals(""))
		{
			sql+=" and state = '"+state+"'";
		}
		if(state1!=null && !state1.equals(""))
		{
			sql+=" and state1 = '"+state1+"'";
		}
		if(state2!=null && !state2.equals(""))
		{
			sql+=" and state2='"+state2+"'";
		}
		if(startdate!=null && !startdate.equals(""))
		{
			sql+=" and repairDate>='"+startdate+"'";
		}
		if(enddate!=null && !enddate.equals(""))
		{
			sql+=" and repairDate<='"+enddate+"'";
		}
		sql+=" limit "+15*(page-1)+",15";//分页,每页显示15条数据
		
		//查询保养记录表中的数据
				ArrayList records =(ArrayList) RepairInfoTools.executeQuary(sql);
				//
				//查询分页总页数total
				String sql2=sql.replace("repairinfo.*", "count(*)");
				sql2 = sql2.split("limit")[0];
				int selectCount = MaintenanceRecordsTools.selectCount(sql2)-1;
				int total=((int)selectCount/15)+1;
				//用map存分页数,把map加到list中,把list转为json
				Map map=new HashMap();
				map.put("totalpage", total);
				records.add(map);
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json=gson.toJson(records);
				
				//
				response.getWriter().write(json);
	}

}
