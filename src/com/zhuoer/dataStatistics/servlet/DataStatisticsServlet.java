package com.zhuoer.dataStatistics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhuoer.aJsonAPI.Power;
import com.zhuoer.employeeAndCustomerInfo.dao.PowerLogin;
import com.zhuoer.userLogin.UserLoginBean;
import com.zhuoer.userLogin.UserLoginDao;

@WebServlet("/DataStatisticsServlet")
public class DataStatisticsServlet extends HttpServlet{
	public DataStatisticsServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();	
        String s=request.getParameter("s").trim();
		String e=request.getParameter("e").trim();
		String no=request.getParameter("no").trim();
		String deviceNo=request.getParameter("deviceNo").trim();
		String tableNo=request.getParameter("tableNo").trim();
		String content=new PlanCompletionRate().getPlanCompletionRate(no,deviceNo, s, e,tableNo);
        out.write(content);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
			doGet(request, response);

	}
}
