package com.zhuoer.aJson;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.aJsonAPI.Power;

/**
 * Servlet implementation class UpdateJsonPower
 */
@WebServlet("/UpdateJsonPower")
public class UpdateJsonPower extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateJsonPower() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String m001 = request.getParameter("m001");
		String m002 = request.getParameter("m002");
		String m003 = request.getParameter("m003");
		String m004 = request.getParameter("m004");
		String m005 = request.getParameter("m005");
		String m006 = request.getParameter("m006");
		String m007 = request.getParameter("m007");
		String m008 = request.getParameter("m008");
		String input = request.getParameter("input");
		String modify = request.getParameter("modify");
		String auditing = request.getParameter("auditing");
		String auditing2 = request.getParameter("auditing2");
		String find = request.getParameter("find");
		String del = request.getParameter("del");
		if(Power.updatePower(no, m001, m002, m003, m004, m005, m006, m007, m008, input, modify, auditing, auditing2, find, del, request.getSession().getAttribute("name").toString())) {
			response.getWriter().print("true");
		} else {
			response.getWriter().print("false");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
