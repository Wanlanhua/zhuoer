package com.zhuoer.customer_management.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.customer_management.Bean.AreaInfo;
import com.zhuoer.customer_management.dao.AreaDao;

/**
 * Servlet implementation class GetAreaInfo
 */
@WebServlet("/GetAreaInfo")
public class GetAreaInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAreaInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page == null) {
			page = request.getSession().getAttribute("thisPageAI").toString();
		}
		String qname = request.getParameter("qname");
		List<AreaInfo> list = AreaDao.AreaSelect(qname, page, request.getSession().getAttribute("name").toString());
		int count = AreaDao.getAreaCount(qname, request.getSession().getAttribute("name").toString());
		request.getSession().setAttribute("queryAI",qname);
		request.getSession().setAttribute("thisPageAI", page);
		request.getSession().setAttribute("listAI", list);
		request.getSession().setAttribute("pageSizeAI", count);
		response.sendRedirect(request.getContextPath()+"/areainfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
