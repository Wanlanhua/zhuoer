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
import com.zhuoer.device.dao.DevicePartDao;

/**
 * Servlet implementation class DevicePartNameSearchServlet
 */
@WebServlet("/DevicePartNameSearchServlet")
public class DevicePartNameSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DevicePartNameSearchServlet() {
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
		String name = request.getParameter("name");
		if ("admin".equals(no)) {
			String sql = "select name from devicepartinfo where name like ?";
			List<String> list = DevicePartDao.selectNameToList(sql,"%"+name+"%");
			String json = gson.toJson(list);
			out.println(json);
		}else {
			String sql = "select name from devicepartinfo where customerNo in (select department from employeeinfo where no = ?) and name like ?";
			List<String> list = DevicePartDao.selectNameToList(sql,no,"%"+name+"%");
			String json = gson.toJson(list);
			out.println(json);
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
