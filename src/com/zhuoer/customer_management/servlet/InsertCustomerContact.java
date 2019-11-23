package com.zhuoer.customer_management.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.customer_management.Bean.CustomerContactInfo;
import com.zhuoer.customer_management.dao.CustomerContactInfoDao;

/**
 * Servlet implementation class InsertCustomerContact
 */
@WebServlet("/InsertCustomerContact")
public class InsertCustomerContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCustomerContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String isEnable = request.getParameter("isEnable");
		String primaryContact = request.getParameter("primaryContact");
		String name = request.getParameter("name");
		String appellation = request.getParameter("appellation");
		String post = request.getParameter("post");
		String phone = request.getParameter("phone");
		String telephone = request.getParameter("telephone");
		String fax = request.getParameter("fax");
		String email = request.getParameter("email");
		String qq = request.getParameter("QQ");
		String weChat = request.getParameter("WeChat");
		String msn = request.getParameter("MSN");
		String skype = request.getParameter("skype");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		CustomerContactInfo cci = new CustomerContactInfo();
		cci.setNo(no);
		cci.setIsEnable(isEnable);
		cci.setPrimaryContact(primaryContact);
		cci.setName(name);
		cci.setAppellation(appellation);
		cci.setPost(post);
		cci.setPhone(phone);
		cci.setTelephone(telephone);
		cci.setFax(fax);
		cci.setEmail(email);
		cci.setQQ(qq);
		cci.setWeChat(weChat);
		cci.setMSN(msn);
		cci.setSkype(skype);
		cci.setBirthday(birthday);
		cci.setAddress(address);
		CustomerContactInfoDao.insertCustomerContact(cci, request.getSession().getAttribute("name").toString());
		response.sendRedirect(request.getContextPath()+"/GetCustomerContact?qname=&page=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
