package com.zhuoer.newCareDevice.service.getDetail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Kqysjyxjlb;
import com.zhuoer.newCareDevice.dao.SelectDao;
import com.zhuoer.newCareDevice.service.util.GS;

/**
 * Servlet implementation class GetSaveKQYSJYXJLB
 */
@WebServlet("/GetSaveKQYSJYXJLB")
public class GetSaveKQYSJYXJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSaveKQYSJYXJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		List<Kqysjyxjlb> list = SelectDao.getKqysjyxjlb(Integer.parseInt(id));
		response.getWriter().println(GS.ToJson(list));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
