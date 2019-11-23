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
import com.zhuoer.device.dao.Z_MaintenanceInfoCrudDao;
import com.zhuoer.device.entity.MaintenanceInfo;

/**
 * Servlet implementation class Z_deciceSelete
 */
@WebServlet("/Z_MaintenanceInfoSelectOnly")
public class Z_MaintenanceInfoSelectOnly extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_MaintenanceInfoSelectOnly() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 查询接口
	 * 接收：id
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
		String sql="";
		String id = request.getParameter("id");
		sql = "select * from maintenanceinfo where id = ?";
		p.add(id);
		deviceName = Z_MaintenanceInfoCrudDao.selectMaintenanceInfo(sql, p);
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
