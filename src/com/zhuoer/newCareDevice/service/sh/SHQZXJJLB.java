package com.zhuoer.newCareDevice.service.sh;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.dao.ShDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class SHDBCZJLB
 */
@WebServlet("/SHQZXJJLB")
public class SHQZXJJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SHQZXJJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shenheyijian = request.getParameter("shenheyijian");
		String status = request.getParameter("status");
		String id = request.getParameter("id");
		boolean flag = ShDao.shQzxjjlb(status, "", id);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>气站巡检记录表=>审核操作");
		}
		response.sendRedirect(request.getContextPath()+"/newCareDevice/GetQZXJJLB");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
