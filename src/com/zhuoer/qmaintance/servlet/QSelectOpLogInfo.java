package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

/**查询日志信息表
 * Servlet implementation class QSelectOpLogInfo
 */
@WebServlet("/QSelectOpLogInfo")
public class QSelectOpLogInfo extends HttpServlet {
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
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域
		
		String no=request.getParameter("no");
		String department=request.getParameter("department");
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		//根据查询条件组装sql语句
		String sql="select e.no,e.name,e.department,o.content,o.stamp from OpLogInfo o,EmployeeInfo e where o.no!='admin' and o.no=e.no";
		if((no!=null && !no.equals("")))
		{
			sql+=" and o.no='"+no+"'";
		}
		if(starttime!=null && !starttime.equals(""))
		{
			sql+=" and o.stamp>='"+starttime+"'";
		}
		if(endtime!=null && !endtime.equals(""))
		{
			sql+=" and o.stamp<='"+endtime+"'";
		}
		if(department!=null && !department.equals(""))
		{
			sql+=" and e.department='"+department+"'";
		}
		System.out.println(sql);
		List li=RepairInfoTools.executeQuary(sql);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json=gson.toJson(li);
		response.getWriter().write(json);
	}

}
