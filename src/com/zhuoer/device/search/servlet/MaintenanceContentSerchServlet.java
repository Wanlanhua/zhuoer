package com.zhuoer.device.search.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhuoer.device.dao.MaintenanceDao;

/**
 * Servlet implementation class MaintenanceContentSerchServlet
 */
@WebServlet("/MaintenanceContentSerchServlet")
public class MaintenanceContentSerchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaintenanceContentSerchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String no = request.getSession().getAttribute("name").toString();
		String content = request.getParameter("content");
		if ("admin".equals(no)) {
			String s = "select content from MaintenanceInfo where content like ?";
			List<String> mainNameList = MaintenanceDao.selectContentToList(s, "%"+content+"%");
			out.print(gson.toJson(mainNameList));
		}else {
			String s = "select content from MaintenanceInfo where customerNo in (select department from employeeinfo where no = ?) and content like ?";
			List<String> mainNameList = MaintenanceDao.selectContentToList(s, no,"%"+content+"%");
			out.print(gson.toJson(mainNameList));
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
