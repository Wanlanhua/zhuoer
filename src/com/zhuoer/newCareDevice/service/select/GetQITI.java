package com.zhuoer.newCareDevice.service.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Qiti;
import com.zhuoer.newCareDevice.dao.SelectDao;

/**
 * Servlet implementation class GetQITI
 */
@WebServlet("/newCareDevice/GetQITI")
public class GetQITI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQITI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shebei = "";
		String page = "1";
		if(request.getParameter("page")!=null) {
			page = request.getParameter("page");
		}
		if(request.getParameter("shebei")!=null) {
			shebei = request.getParameter("shebei");
		}
		List<Qiti> list = SelectDao.getQiti(shebei, Integer.valueOf(page));
		
		request.getSession().setAttribute("shebei", shebei);
		request.getSession().setAttribute("qtlist", list);
		request.getSession().setAttribute("thisPage", page);
		response.sendRedirect(request.getContextPath()+"/qiti.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
