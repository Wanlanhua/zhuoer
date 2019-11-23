package com.zhuoer.device.operation.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.zhuoer.device.dao.Z_DeviceFittingInfoCrudDao;
import com.zhuoer.device.entity.DeviceFittingInfo;

/**
 * Servlet implementation class Z_DevicePartInfoSelect
 */
@WebServlet("/Z_DeviceFittingInfoSelectOnly")
public class Z_DeviceFittingInfoSelectOnly extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DeviceFittingInfoSelectOnly() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 查询接口
	 * 接收：id （）
	 * 返回：DevicePartInfo满足条件的所有值
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		List<DeviceFittingInfo> deviceName = null;
		String id=request.getParameter("id");
		List<Object> p = new ArrayList<>();
		String sql="";
		sql = "select * from DeviceFittingInfo where id = ?";
		p.add(id);
		deviceName =Z_DeviceFittingInfoCrudDao.selectDeviceFittingInfo(sql, p);
		String nameString = gson.toJson(deviceName);
		out.println(nameString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
