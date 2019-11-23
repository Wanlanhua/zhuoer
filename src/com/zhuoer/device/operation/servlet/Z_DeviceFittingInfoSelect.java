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
import com.zhuoer.device.dao.Z_DeviceFittingInfoCrudDao;
import com.zhuoer.device.entity.DeviceFittingInfo;
import com.zhuoer.device.entity.PageBean;

/**
 * Servlet implementation class Z_DevicePartInfoSelect
 */
@WebServlet("/Z_DeviceFittingInfoSelect")
public class Z_DeviceFittingInfoSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DeviceFittingInfoSelect() {
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
		List<DeviceFittingInfo> deviceName = null;
		String customerNo = request.getParameter("customerNo");
		String type1=request.getParameter("type1");
		String type2=request.getParameter("type2");
		String name=request.getParameter("name");
		int page = Integer.parseInt(request.getParameter("page"));
		List<Object> p = new ArrayList<>();
		List<Object> p2 = new ArrayList<>();
		String sql="";
		String sql2 = "";
		if(name==""){
			sql = "select * from DeviceFittingInfo where customerNo = ? and type1= ? and type2= ? limit ?,15 ";
			sql2="SELECT COUNT(*) FROM DeviceFittingInfo  where customerNo = ? and type1= ? and type2= ? ";
			p.add(customerNo);
			p.add(type1);
			p.add(type2);
			p.add((page-1)*15);
			p2.add(customerNo);
			p2.add(type1);
			p2.add(type2);
		}
		else{
			sql = "select * from DeviceFittingInfo where customerNo = ? and type1= ? and type2= ? and name like ? limit ?,15";
			sql2="SELECT COUNT(*) FROM DeviceFittingInfo  where customerNo = ? and type1= ? and type2= ? and name like ? ";
			p.add(customerNo);
			p.add(type1);
			p.add(type2);
			p.add("%"+name+"%");
			p.add((page-1)*15);
			p2.add(customerNo);
			p2.add(type1);
			p2.add(type2);
			p2.add("%"+name+"%");
		}
		int a=GetTotalDao.selectTotal(sql2, p2);
		PageBean<DeviceFittingInfo> pbDevice = new PageBean<>(page, a);
		deviceName =Z_DeviceFittingInfoCrudDao.selectDeviceFittingInfo(sql, p);
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
