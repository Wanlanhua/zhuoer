package com.zhuoer.employeeAndCustomerInfo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.employeeAndCustomerInfo.dao.EmploeeDao;
import com.zhuoer.employeeAndCustomerInfo.entity.EmployeeInfo;

/**
 * Servlet implementation class GetEmployeeInfo
 */
@WebServlet("/GetEmployeeInfo")
public class GetEmployeeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qname = request.getParameter("qname");
		String page = request.getParameter("page");
		if(page == null) {
			page = request.getSession().getAttribute("thisPageEI").toString();
		}
		if(qname == null) {
			qname = "";
		}
		int count = EmploeeDao.getEmployeeCount(qname, request.getSession().getAttribute("name").toString());
		List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
		list = EmploeeDao.getEmployee(qname, page, request.getSession().getAttribute("name").toString());
		request.getSession().setAttribute("queryEI",qname);
		request.getSession().setAttribute("thisPageEI", page);
		request.getSession().setAttribute("listEI", list);
		request.getSession().setAttribute("pageSizeEI", count);
		response.sendRedirect(request.getContextPath()+"/employeeinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
