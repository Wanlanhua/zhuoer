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
import com.zhuoer.device.dao.Z_DandFInfoCrudDao;
import com.zhuoer.device.entity.DandFInfo;
import com.zhuoer.device.entity.PageBean;

/**
 * Servlet implementation class Z_DandMInfoSelectServlet
 */
@WebServlet("/Z_DandFInfoSelectServlet")
public class Z_DandFInfoSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DandFInfoSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *表DandFInfo的查询
	 *接收  device name  page（页数）
	 *返回： json 封装 DandFInfo对象
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<DandFInfo> deviceName =null;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String deviceNo = request.getParameter("deviceNo");
		String area = request.getParameter("area");
		int page = Integer.parseInt(request.getParameter("page"));
		List<Object> p = new ArrayList<>();
		List<Object> p2 = new ArrayList<>();
		String sql ="";
		String sql2 = "";
		sql = "SELECT   \r\n" + 
				"				dandpinfo.deviceNo,   \r\n" + 
				"				dandfinfo.id,   \r\n" + 
				"				dandfinfo.mark,   \r\n" + 
				"				devicefittinginfo.name as no,   \r\n" + 
				"				dandpinfo.name as partname   \r\n" + 
				"				FROM   \r\n" + 
				"				dandpinfo ,   \r\n" + 
				"				dandfinfo ,   \r\n" + 
				"				devicefittinginfo ,   \r\n" + 
				"				deviceinfo   \r\n" + 
				"				WHERE   \r\n" + 
				"				dandpinfo.name = dandfinfo.deviceNo AND   \r\n" + 
				"				dandfinfo.no = devicefittinginfo.no AND   \r\n" + 
				"				dandpinfo.deviceNo = deviceinfo.no AND   \r\n" + 
				"				deviceinfo.no=dandfinfo.deviceNo1  AND deviceinfo.no=dandfinfo.deviceNo1 and " + 
				"deviceinfo.area=? AND deviceinfo.no like ?"
				+ "limit ?,15";
		sql2="SELECT " + 
				"count(*)" + 
				"FROM " + 
				"dandpinfo , " + 
				"dandfinfo , " + 
				"devicefittinginfo , " + 
				"deviceinfo " + 
				"WHERE " + 
				"dandpinfo.name = dandfinfo.deviceNo AND " + 
				"dandfinfo.no = devicefittinginfo.no AND " + 
				"dandpinfo.deviceNo = deviceinfo.no AND "+ 
				"deviceinfo.no=dandfinfo.deviceNo1  AND deviceinfo.no=dandfinfo.deviceNo1 and " + 
				"deviceinfo.area = ? AND deviceinfo.no like ?";
		p.add(area);
		p.add("%"+deviceNo+"%");
		p.add((page-1)*15);
		p2.add(area);
		p2.add("%"+deviceNo+"%");
		int a=GetTotalDao.selectTotal(sql2, p2);
		PageBean<DandFInfo> pbDevice = new PageBean<>(page, a);
		deviceName =Z_DandFInfoCrudDao.selectDandFInfo(sql, p);
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
