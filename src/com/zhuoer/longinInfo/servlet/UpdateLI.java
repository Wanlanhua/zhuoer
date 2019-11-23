package com.zhuoer.longinInfo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.util.GetPageSize;
import com.zhuoer.longinInfo.dao.LoginInfoUpdate;

/**
 * Servlet implementation class UpdateLI
 */
@WebServlet("/employeeAndCustomerInfo/UpdateLI")
public class UpdateLI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		int id = Integer.parseInt(request.getParameter("id"));
		new LoginInfoUpdate().update(id, password, request.getSession().getAttribute("name").toString());
		int pageSize = GetPageSize.getPageSize("employeeandcustomerinfo");
		int page = 1;
		if(request.getParameter("page") == null || request.getParameter("page").equals(""))
			page = 1;
		else
			page = Integer.parseInt(request.getParameter("page"));
		request.getSession().setAttribute("eaciThisPage", page);
		request.getSession().setAttribute("eaciPage", pageSize);
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
