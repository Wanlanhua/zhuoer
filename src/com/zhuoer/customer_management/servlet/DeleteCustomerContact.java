package com.zhuoer.customer_management.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.customer_management.dao.CustomerContactInfoDao;

/**
 * Servlet implementation class DeleteCustomerContact
 */
@WebServlet("/DeleteCustomerContact")
public class DeleteCustomerContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCustomerContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		CustomerContactInfoDao.deleteCustomerContact(Integer.valueOf(id), request.getSession().getAttribute("name").toString());
		String qname = "";
		if(request.getSession().getAttribute("queryCCI")!=null)
			qname = request.getSession().getAttribute("queryCCI").toString();
		request.getRequestDispatcher("/GetCustomerContact?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
