package com.zhuoer.customer_management.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.customer_management.Bean.CustomerInfo;
import com.zhuoer.customer_management.dao.CustomerDao;

/**
 * Servlet implementation class INserCustomerInfo
 */
@WebServlet("/InserCustomerInfo")
public class InsertCustomerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCustomerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String managementAddress = request.getParameter("managementAddress");
		String taxNumber = request.getParameter("taxNumber");
		String bank = request.getParameter("bank");
		String account = request.getParameter("account");
		String phone = request.getParameter("phone");
		String departmentid = request.getParameter("departmentid");
		String departmentname = request.getParameter("departmentname");
		CustomerInfo customer = new CustomerInfo();
		customer.setName(name);
		customer.setNo(no);
		customer.setManagementAddress(managementAddress);
		customer.setTaxNumber(taxNumber);
		customer.setBank(bank);
		customer.setBank(bank);
		customer.setAccount(account);
		customer.setPhone(phone);
		customer.setDepartmentid(departmentid);
		customer.setDepartmentname(departmentname);
		CustomerDao.insertCustomer(customer, request.getSession().getAttribute("name").toString());
		response.sendRedirect(request.getContextPath()+"/GetCustomerInfo?qname=&page=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
