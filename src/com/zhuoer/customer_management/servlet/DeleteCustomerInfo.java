package com.zhuoer.customer_management.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.aJsonAPI.Power;
import com.zhuoer.customer_management.dao.CustomerDao;

/**
 * Servlet implementation class DeleteCustomerInfo
 */
@WebServlet("/DeleteCustomerInfo")
public class DeleteCustomerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCustomerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String sql = "select no from customerinfo where id = "+id;
		ResultSet rs;
		String no="";
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				no = rs.getString("no");
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CustomerDao.deleteCustomer(id, request.getSession().getAttribute("name").toString());
		Power.deletePower(no);
		String qname = "";
		if(request.getSession().getAttribute("queryCI")!=null)
			qname = request.getSession().getAttribute("queryCostomer").toString();
		request.getRequestDispatcher("/GetCustomerInfo?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
