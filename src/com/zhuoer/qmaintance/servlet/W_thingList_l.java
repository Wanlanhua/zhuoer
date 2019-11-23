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
import com.zhuoer.qmaintance.utils.RepairInfoTools;

/**
 * Servlet implementation class W_Information
 */
@WebServlet("/W_thingList_l")
public class W_thingList_l extends HttpServlet {
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
			String sql="SELECT lubricationRecordsInfo.*,deviceinfo.name from lubricationRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=lubricationRecordsInfo.deviceNo limit "+(page-1)*5+",5";
			List executeQuary = RepairInfoTools.executeQuary(sql);
			String sqlCount="SELECT count(*) from lubricationRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=lubricationRecordsInfo.deviceNo";
			int selectCount = RepairInfoTools.selectCount(sqlCount);
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
				String sqlAll="SELECT lubricationRecordsInfo.*,deviceinfo.name from lubricationRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=lubricationRecordsInfo.deviceNo and (maintenancePerson  in (SELECT name FROM employeeinfo WHERE no ='"+no+"') or person2 in (SELECT name FROM employeeinfo WHERE no ='"+no+"')) limit "+(page-1)*5+",5";
				List executeQuary = RepairInfoTools.executeQuary(sqlAll);
				String sqlCount="SELECT count(*) lubricationRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=lubricationRecordsInfo.deviceNo and (maintenancePerson  in (SELECT name FROM employeeinfo WHERE no ='"+no+"') or person2 in (SELECT name FROM employeeinfo WHERE no ='"+no+"'))";
				int selectCount = RepairInfoTools.selectCount(sqlCount);
				PageBean<List> pageBean=new PageBean<List>(page, selectCount);
				pageBean.setPageSize(5);
				pageBean.setData(executeQuary);
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json = gson.toJson(pageBean);
				response.getWriter().write(json);
				break;
			case "2":
			case "3":
				String sql2="SELECT lubricationRecordsInfo.*,deviceinfo.name from lubricationRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=lubricationRecordsInfo.deviceNo and deviceNo in("
						+"SELECT no from deviceinfo WHERE CustomerNo in ("
						+"SELECT department FROM employeeinfo " 
						+"WHERE no = "+no+" )) limit "+(page-1)*5+",5";
				List executeQuary2 = RepairInfoTools.executeQuary(sql2);
				String sqlCount2="SELECT count(*) from lubricationRecordsInfo,deviceinfo WHERE state2 !='1' and deviceinfo.no=lubricationRecordsInfo.deviceNo and deviceNo in("
						+"SELECT no from deviceinfo WHERE CustomerNo in ("
						+"SELECT department FROM employeeinfo " 
						+"WHERE no = "+no+" ))";
				int selectCount2 = RepairInfoTools.selectCount(sqlCount2);
				PageBean<List> pageBean2=new PageBean<List>(page, selectCount2);
				pageBean2.setData(executeQuary2);
				pageBean2.setPageSize(5);
				Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String json2 = gson2.toJson(pageBean2);
				response.getWriter().write(json2);
				break;
				
			}
		}
		
	}

}
