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
import com.zhuoer.device.dao.GetTotalDao;
import com.zhuoer.device.dao.Z_devicePartInfoCrudDao;
import com.zhuoer.device.entity.DevicePartInfo;
import com.zhuoer.device.entity.PageBean;

/**
 * Servlet implementation class Z_DevicePartInfoSelect
 */
@WebServlet("/Z_DevicePartInfoSelect")
public class Z_DevicePartInfoSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DevicePartInfoSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 查询接口
	 * 接收：id customerNo（id模糊查询）
	 * 返回：DevicePartInfo满足条件的所有值
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		List<DevicePartInfo> deviceName = null;
		List<Object> p = new ArrayList<>();
		List<Object> p2 = new ArrayList<>();
		String sql="";
		String sql2="";
		String customerNo = request.getParameter("customerNo");
		String name=request.getParameter("name");
		int page = Integer.parseInt(request.getParameter("page"));
		
		if(name!=""){
			sql = "select * from DevicePartInfo where customerNo = ? and name like ? limit ?,15";
			sql2="SELECT COUNT(*) FROM DevicePartInfo where customerNo = ? and name like ?";
			p.add(customerNo);
			p.add(name);
			p.add((page-1)*15);
			p2.add(customerNo);
			p2.add(name);
		}else{
			sql = "select * from DevicePartInfo where customerNo = ? limit ?,15";
			sql2="SELECT COUNT(*) FROM DevicePartInfo where customerNo = ?";
			p.add(customerNo);
			p.add((page-1)*15);
			p2.add(customerNo);
		}
		int a=GetTotalDao.selectTotal(sql2, p2);
		PageBean<DevicePartInfo> pbDevice = new PageBean<>(page, a);
		deviceName =Z_devicePartInfoCrudDao.selectDevicePartInfo(sql, p);
		pbDevice.setData(deviceName);
		String nameString = gson.toJson(pbDevice);
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
