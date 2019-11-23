package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.qmaintance.utils.RepairInfoTools;

/**
 * Servlet implementation class SelectNameById
 */
@WebServlet("/SelectNameById")
public class SelectNameById extends HttpServlet {
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
		
		
	}
	public String getid(String no)
	{
		String name = "";
		String sql="select name from EmployeeInfo where id=?";
		List<Map<String, String>> li=RepairInfoTools.executeQuary(sql, no);
		if(li.size()>0)
		{
			name = li.get(0).get("name");
		}
		return name;
	}

}
