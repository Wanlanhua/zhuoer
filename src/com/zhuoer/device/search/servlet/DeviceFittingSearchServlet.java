package com.zhuoer.device.search.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.zhuoer.device.dao.DeviceFittingDao;

/**
 * Servlet implementation class DeviceFittingSearchServlet
 */
@WebServlet("/DeviceFittingSearchServlet")
public class DeviceFittingSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeviceFittingSearchServlet() {
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
		String type = request.getParameter("type");
		String type1 = "";
		String type2 = "";
		String department = "";
		String name = "";
		switch (type) {
		case "selectType1":
			String sql = "select DISTINCT type1 from DeviceFittingInfo where customerno = ?";
			department = request.getParameter("department");
			List<String> list = DeviceFittingDao.selectType1(sql,department);
			String json = gson.toJson(list);
			out.println(json);	
			break;
		case "selectType2":
			String sql1 = "select DISTINCT type2 from DeviceFittingInfo where customerno = ? and type1=?";
			department = request.getParameter("department");
			type1 = request.getParameter("type1");
			List<String> type2List = DeviceFittingDao.selectType2(sql1,department,type1);
			String jn = gson.toJson(type2List);
			out.println(jn);	
			break;
		case "selcetDeviceFitting":
			String sql2 = "select name from DeviceFittingInfo where customerno=? and type1=? and type2=? and name like ? limit 0,15";
			department = request.getParameter("department");
			type1 = request.getParameter("type1");
			type2 = request.getParameter("type2");
			name = request.getParameter("DeviceFittingName");
			List<String> deviceFittingList = DeviceFittingDao.selectDeviceFittingName(sql2,department,type1,type2,"%"+name+"%");
			String j = gson.toJson(deviceFittingList);
			out.println(j);	
			break;
		case "selectDeviceFittingName":
			String no = request.getSession().getAttribute("name").toString();
			String deviceFitName = request.getParameter("deviceFitName");
			if ("admin".equals(no)) {
				String sql3 = "select no,name from DeviceFittingInfo where name like ?";
				Map<String,String> map = DeviceFittingDao.selectDeviceFittingNameToMap(sql3,"%"+deviceFitName+"%");
				String js = gson.toJson(map);
				out.println(js);	
			}else {
				String sql3 = "select no,name from DeviceFittingInfo where name like ? and customerNo in (select department from employeeinfo where no = ?)";
				Map<String,String> map = DeviceFittingDao.selectDeviceFittingNameToMap(sql3,"%"+deviceFitName+"%",no);
				String js = gson.toJson(map);
				out.println(js);	
			}
			break;
		default:
			break;
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
