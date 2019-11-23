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
 * Servlet implementation class DevicePartSearchServlet
 */
@WebServlet("/DevicePartSearchServlet")
public class DevicePartSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DevicePartSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String type = "";
		type = request.getParameter("type");
		if ("selectDevicePart".equals(type)) {
			String no = request.getSession().getAttribute("name").toString();
			if ("admin".equals(no)) {
				String deviceNo = request.getParameter("deviceNo");
				String sql = "select name from dandpinfo where deviceNo = ?";
				List<String> selectDeviceName = DevicePartDao.selectNameToList(sql, deviceNo);
				String json = gson.toJson(selectDeviceName);
				out.print(json);
			}else {
				String deviceNo = request.getParameter("deviceNo");
//				String sql = "select name from dandpinfo where deviceNo = ? and customerNo in (select department from employeeinfo where no = ?)";
				String sql = "select name from dandpinfo where deviceNo = ?";
				List<String> selectDeviceName = DevicePartDao.selectNameToList(sql, deviceNo);
				String json = gson.toJson(selectDeviceName);
				out.print(json);
			}
		}else {
			String department = request.getParameter("department");
			String devicePart = request.getParameter("devicePart");
			String sql = "select name from devicepartinfo where customerno=? and name like ? limit 0,15";
			List<String> list = DevicePartDao.selectNameToList(sql,department,"%"+devicePart+"%");
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
