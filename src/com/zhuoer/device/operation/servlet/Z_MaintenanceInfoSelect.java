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
import com.google.gson.GsonBuilder;
import com.zhuoer.device.dao.GetTotalDao;
import com.zhuoer.device.dao.Z_MaintenanceInfoCrudDao;
import com.zhuoer.device.entity.MaintenanceInfo;
import com.zhuoer.device.entity.PageBean;

/**
 * Servlet implementation class Z_deciceSelete
 */
@WebServlet("/Z_MaintenanceInfoSelect")
public class Z_MaintenanceInfoSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_MaintenanceInfoSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 查询接口
	 * 接收：customerNo，no
	 * 返回：json数据返回所有查询到的信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<MaintenanceInfo> deviceName =null;
		List<Object> p = new ArrayList<>();
		List<Object> p2 = new ArrayList<>();
		String sql="";
		String sql2=""; 
		String customerNo = request.getParameter("customerNo");
		String content = request.getParameter("content");
		int page = Integer.parseInt(request.getParameter("page"));
		sql = "select * from maintenanceinfo where customerNo = ? and content like ? limit ?,15 ";
		sql2="SELECT COUNT(*) FROM maintenanceinfo where customerNo = ? and content like ?";
		p.add(customerNo);
		p.add("%"+content+"%");
		p.add((page-1)*15);
		p2.add(customerNo);
		p2.add("%"+content+"%");
		deviceName = Z_MaintenanceInfoCrudDao.selectMaintenanceInfo(sql, p);
		int a=GetTotalDao.selectTotal(sql2, p2);
		PageBean<MaintenanceInfo> pbDevice = new PageBean<>(page, a);
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
