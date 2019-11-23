package com.zhuoer.customer_management.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.customer_management.Bean.AreaInfo;
import com.zhuoer.customer_management.dao.AreaDao;

/**
 * Servlet implementation class InsertAearInfo
 */
@WebServlet("/InsertAearInfo")
public class InsertAearInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAearInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String departmentid = request.getParameter("departmentid");
		AreaInfo ai = new AreaInfo();
		ai.setNo(no);
		ai.setDepartmentid(departmentid);
		ai.setName(name);
		AreaDao.AreaInsert(ai, request.getSession().getAttribute("name").toString());
		String qname = "";
		request.getRequestDispatcher("/GetAreaInfo?qname="+qname+"&page=1").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
