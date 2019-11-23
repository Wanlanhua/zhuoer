package com.zhuoer.employeeAndCustomerInfo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.aJsonAPI.Util.ToJson;
import com.zhuoer.employeeAndCustomerInfo.dao.EmploeeDao;
import com.zhuoer.employeeAndCustomerInfo.entity.EmployeeInfo;

/**
 * Servlet implementation class GetCustomerById
 */
@WebServlet("/GetCustomerById")
public class GetCustomerById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		EmployeeInfo ei = EmploeeDao.getEmployeeById(Integer.valueOf(id));
		response.getWriter().println(ToJson.toJson(ei));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
