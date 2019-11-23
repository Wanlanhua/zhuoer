package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.device.entity.PageBean;
import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;

/**
 * Servlet implementation class W_Information
 */
@WebServlet("/W_thingList_m")
public class W_thingList_m extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域
		
		String no=(String) request.getSession().getAttribute("name");
		String role=(String) request.getSession().getAttribute("role");//角色
		int page=Integer.parseInt(request.getParameter("page"));
		String type=request.getParameter("type");
		if(no.equals("admin"))
		{
			String sql="SELECT maintenancerecordsinfo.*,deviceinfo.name from MaintenanceRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=maintenancerecordsinfo.deviceNo limit "+(page-1)*5+",5";
			List executeQuary = MaintenanceRecordsTools.executeQuary(sql);
			String sqlCount="SELECT count(*) from MaintenanceRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=maintenancerecordsinfo.deviceNo";
			int selectCount = MaintenanceRecordsTools.selectCount(sqlCount);
			PageBean<List> pageBean=new PageBean<List>(page, selectCount);
			pageBean.setPageSize(5);
			pageBean.setData(executeQuary);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String json = gson.toJson(pageBean);
			response.getWriter().write(json);
		}
		else
		{
			switch(role)
			{
			case "1":
				String sqlAll="SELECT maintenancerecordsinfo.*,deviceinfo.name from MaintenanceRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=maintenancerecordsinfo.deviceNo and (maintenancePerson  in (SELECT name FROM employeeinfo WHERE no ='"+no+"') or person2 in (SELECT name FROM employeeinfo WHERE no ='"+no+"')) limit "+(page-1)*5+",5";
				List executeQuary = MaintenanceRecordsTools.executeQuary(sqlAll);
				String sqlCount="SELECT count(*) from MaintenanceRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=maintenancerecordsinfo.deviceNo and (maintenancePerson  in (SELECT name FROM employeeinfo WHERE no ='"+no+"') or person2 in (SELECT name FROM employeeinfo WHERE no ='"+no+"'))";
				int selectCount = MaintenanceRecordsTools.selectCount(sqlCount);
				PageBean<List> pageBean=new PageBean<List>(page, selectCount);
				pageBean.setPageSize(5);
				pageBean.setData(executeQuary);
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gson.toJson(pageBean);
				response.getWriter().write(json);
				break;
			case "2":
			case "3":
				String sql2="SELECT maintenancerecordsinfo.*,deviceinfo.name from MaintenanceRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=maintenancerecordsinfo.deviceNo and deviceNo in("
						+"SELECT no from deviceinfo WHERE CustomerNo in ("
						+"SELECT department FROM employeeinfo " 
						+"WHERE no = "+no+" )) limit "+(page-1)*5+",5";
				List executeQuary2 = MaintenanceRecordsTools.executeQuary(sql2);
				String sqlCount2="SELECT count(*) from MaintenanceRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=maintenancerecordsinfo.deviceNo and deviceNo in("
						+"SELECT no from deviceinfo WHERE CustomerNo in ("
						+"SELECT department FROM employeeinfo " 
						+"WHERE no = "+no+" ))";
				int selectCount2 = MaintenanceRecordsTools.selectCount(sqlCount2);
				PageBean<List> pageBean2=new PageBean<List>(page, selectCount2);
				pageBean2.setPageSize(5);
				pageBean2.setData(executeQuary2);
				Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json2 = gson2.toJson(pageBean2);
				response.getWriter().write(json2);
				break;
				
			}
		}
		
	}

}
