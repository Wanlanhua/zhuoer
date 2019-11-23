package com.zhuoer.newCareDevice.service.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Dbczjlb;
import com.zhuoer.newCareDevice.dao.SelectDao;
import com.zhuoer.newCareDevice.util.GetPageSize;

/**
 * Servlet implementation class GetDBCZJLB
 */
@WebServlet("/newCareDevice/GetDBCZJLB")
public class GetDBCZJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDBCZJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shebei = "";
		String page = "1";
		String departmentid = "";
		String starttime =  "";
		String endtime = "";
		
		if(request.getSession().getAttribute("thisdbczjlbPage")!=null) {
			page = request.getSession().getAttribute("thisdbczjlbPage").toString();
		}
		if(request.getParameter("page")!=null) {
			page = request.getParameter("page");
		} 
		if(request.getParameter("shebei")!=null) {
			shebei = request.getParameter("shebei");
		} else if(request.getSession().getAttribute("shebei")!=null) {
			shebei = request.getSession().getAttribute("shebei").toString();
		}
		if(request.getParameter("departmentid")!=null) {
			departmentid = request.getParameter("departmentid");
		} else if(request.getSession().getAttribute("departmentid")!=null) {
			departmentid = request.getSession().getAttribute("departmentid").toString();
		}
		if(request.getParameter("starttime")!=null) {
			starttime = request.getParameter("starttime");
		} else if(request.getSession().getAttribute("starttime")!=null){
			starttime = request.getSession().getAttribute("starttime").toString();
		}
		if(request.getParameter("endtime")!=null) {
			endtime = request.getParameter("endtime");
		} else if(request.getSession().getAttribute("endtime")!=null){
			endtime = request.getSession().getAttribute("endtime").toString();
		}
		List<Dbczjlb> list = SelectDao.getDbczjlb(departmentid, shebei, starttime, endtime, Integer.valueOf(page));
		int count = GetPageSize.getPageSize(shebei, "dbczjlb", departmentid, starttime, endtime);
		request.getSession().setAttribute("dbczjlbpage", page);
		request.getSession().setAttribute("starttime", starttime);
		request.getSession().setAttribute("endtime", endtime);
		request.getSession().setAttribute("shebei", shebei);
		request.getSession().setAttribute("pageSize", count);
		request.getSession().setAttribute("departmentid", departmentid);
		request.getSession().setAttribute("dbczjlblist", list);
		request.getSession().setAttribute("thisdbczjlbPage", page);
		response.sendRedirect(request.getContextPath()+"/dbczjlb.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
