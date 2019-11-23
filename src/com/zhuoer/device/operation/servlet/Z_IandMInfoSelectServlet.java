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
import com.zhuoer.device.dao.Z_DandMInfoCrudDao;
import com.zhuoer.device.entity.DandMInfo;
import com.zhuoer.device.entity.PageBean;

/**
 * Servlet implementation class Z_DandMInfoSelectServlet
 */
@WebServlet("/Z_IandMInfoSelectServlet")
public class Z_IandMInfoSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_IandMInfoSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *表DandMInfo的查询
	 *接收  deviceNo DeviceInfo.area  page（页数）【】
	 *返回： json 封装 DandMInfo对象
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<DandMInfo> deviceName =null;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String deviceNo = request.getParameter("deviceNo");
		String area = request.getParameter("area");
		int page = Integer.parseInt(request.getParameter("page"));
		List<Object> p = new ArrayList<>();
		List<Object> p2 = new ArrayList<>();
		String sql ="";
		String sql2 = "";
		sql = "SELECT IandMInfo.* "
				+ "FROM IandMInfo,deviceinfo "
				+ "where deviceinfo.area = ? and deviceinfo.no = IandMInfo.deviceNo and "
				+ "IandMInfo.deviceNo like ?"
				+ "limit ?,15";
		sql2="SELECT count(*) "
				+ "FROM IandMInfo,deviceinfo "
				+ "where deviceinfo.area = ? and deviceinfo.no = IandMInfo.deviceNo and "
				+ "IandMInfo.deviceNo like ?";
		p.add(area);
		p.add("%"+deviceNo+"%");
		p.add((page-1)*15);
		p2.add(area);
		p2.add("%"+deviceNo+"%");
		int a=GetTotalDao.selectTotal(sql2, p2);
		PageBean<DandMInfo> pbDevice = new PageBean<>(page, a);
		deviceName =Z_DandMInfoCrudDao.selectDandMInfo(sql, p);
		pbDevice.setData(deviceName);
		String nameString = gson.toJson(pbDevice);
		out.println(nameString);
	}

	/**z
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
