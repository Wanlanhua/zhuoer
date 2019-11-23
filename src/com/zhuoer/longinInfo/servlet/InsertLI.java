package com.zhuoer.longinInfo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.util.GetPageSize;
import com.zhuoer.longinInfo.dao.LoginInfoInsert;
import com.zhuoer.longinInfo.entity.LoginInfo;

/**
 * Servlet implementation class InsertLI
 */
@WebServlet("/employeeAndCustomerInfo/InsertLI")
public class InsertLI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertLI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setName(name);
		loginInfo.setRole(role);
		loginInfo.setPassword(password);
		new LoginInfoInsert().insert(loginInfo, request.getSession().getAttribute("name").toString());
		int pageSize = GetPageSize.getPageSize("employeeandcustomerinfo");
		request.getSession().setAttribute("liThisPage","1");
		request.getSession().setAttribute("liPage", pageSize);
		request.getRequestDispatcher("/employeeAndCustomerInfo/GetLI?qname=").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
