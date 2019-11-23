package com.zhuoer.careDevice.servlet.ajaxServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.careDevice.util.JsonUtil;
import com.zhuoer.customer_management.Bean.CustomerInfo;

/**
 * Servlet implementation class CustomerNo
 */
@WebServlet("/CustomerNo")
public class CustomerNo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerNo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<CustomerInfo> list = null;
		try {
			ResultSet resultSet = new DataBaseAccess().query("select no,name from customerinfo");
			list = new ArrayList<CustomerInfo>();
			if(resultSet.getRow() > 0) {
				while(resultSet.next()) {
					CustomerInfo customer = new CustomerInfo();
					customer.setNo(resultSet.getString("no"));
					customer.setName(resultSet.getString("name"));
					list.add(customer);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().println(JsonUtil.toJson(list));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
