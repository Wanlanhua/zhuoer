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
import com.zhuoer.device.dao.DeviceDao;

/**
 * Servlet implementation class DeviceSearchSevlet
 */
@WebServlet("/DeviceSearchSevlet")
public class DeviceSearchSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeviceSearchSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * deviceInfo表中查询条件,联动返回数据
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String department = "";
		String area = "";
		String deviceNo = "";
		String sql = "";
		switch (type) {
		case "selectArea":							//查询当前部门下的区域
			department = request.getParameter("department");
			sql = "select no,name from areainfo where departmentid=?";
			List<List<String>> list = DeviceDao.selectAreaList(sql,department);
			String json = gson.toJson(list);
			out.println(json);
			break;
		case "selectDeviceNo":						//查询符合条件的设备号
			department = request.getParameter("department");
			area = request.getParameter("area");
			deviceNo = request.getParameter("deviceNo");
			sql = "select no from deviceinfo where customerno=? and area=? and no like ? limit 0,15";
			List<String> deviceNoList = DeviceDao.selectDeviceNoList(sql,department,area,"%"+deviceNo+"%");
			String deviceNoStr = gson.toJson(deviceNoList);
			out.print(deviceNoStr);
			break;
		case "selectDeviceNoOnly":						//查询符合条件的设备号
			department = request.getParameter("department");
			area = request.getParameter("area");
			deviceNo = request.getParameter("deviceNo");
			String no = request.getSession().getAttribute("name").toString();
			if ("admin".equals(no)) {
				sql = "select no from deviceinfo where no like ? limit 0,15";
				List<String> deviceNoOnlyList = DeviceDao.selectDeviceNoList(sql,"%"+deviceNo+"%");
				String deviceNoOnlyStr = gson.toJson(deviceNoOnlyList);
				out.print(deviceNoOnlyStr);
			}else {
				sql = "select no from deviceinfo where no like ? and  customerNo in (select department from employeeinfo where no = ?) limit 0,15";
				List<String> deviceNoOnlyList = DeviceDao.selectDeviceNoList(sql,"%"+deviceNo+"%",no);
				String deviceNoOnlyStr = gson.toJson(deviceNoOnlyList);
				out.print(deviceNoOnlyStr);
			}
			break;
		case "selectDeviceName":						//查询符合条件的设备号
			deviceNo = request.getParameter("deviceNo");
			sql = "select name from deviceinfo where no = ? limit 0,15";
			List<String> deviceNameList = DeviceDao.selectDeviceNameList(sql,deviceNo);
			String deviceNameStr = gson.toJson(deviceNameList);
			out.print(deviceNameStr);
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
