package com.zhuoer.customer_management.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.customer_management.Bean.CustomerContactInfo;
import com.zhuoer.customer_management.dao.AreaDao;
import com.zhuoer.customer_management.dao.CustomerContactInfoDao;

/**
 * Servlet implementation class GetCustomerContact
 */
@WebServlet("/GetCustomerContact")
public class GetCustomerContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page == null) {
			page = request.getSession().getAttribute("thisPageCCI").toString();
		}
		String qname = request.getParameter("qname");
		List<CustomerContactInfo> list = CustomerContactInfoDao.getCustomerContact(qname,page, request.getSession().getAttribute("name").toString());
		int count = CustomerContactInfoDao.getCustomerContactCount(qname, request.getSession().getAttribute("name").toString());
		request.getSession().setAttribute("queryCCI",qname);
		request.getSession().setAttribute("thisPageCCI", page);
		request.getSession().setAttribute("listCCI", list);
		request.getSession().setAttribute("pageSizeCCI", count);
		response.sendRedirect(request.getContextPath()+"/customercontactinfo.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
