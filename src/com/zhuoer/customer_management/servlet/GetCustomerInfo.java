package com.zhuoer.customer_management.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.customer_management.Bean.CustomerInfo;
import com.zhuoer.customer_management.dao.CustomerDao;

/**
 * Servlet implementation class GetCustomerInfo
 */
@WebServlet("/GetCustomerInfo")
public class GetCustomerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String qname = request.getParameter("qname");
		String page = request.getParameter("page");
		if(page == null) {
			page = request.getSession().getAttribute("thisPageCostomer").toString();
		}
		List<CustomerInfo> list = CustomerDao.getCustomer(qname, page, request.getSession().getAttribute("name").toString());
		int count = CustomerDao.getCustomerCount(qname, request.getSession().getAttribute("name").toString());
		request.getSession().setAttribute("queryCostomer",qname);
		request.getSession().setAttribute("thisPageCostomer", page);
		request.getSession().setAttribute("listCostomer", list);
		request.getSession().setAttribute("pageSizeCostomer", count);
		response.sendRedirect(request.getContextPath()+"/customerinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
