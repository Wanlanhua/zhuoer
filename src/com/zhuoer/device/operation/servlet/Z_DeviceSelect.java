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
import com.zhuoer.device.dao.Z_deviceCrudDao;
import com.zhuoer.device.entity.DeviceInfo;
import com.zhuoer.device.entity.PageBean;

/**
 * Servlet implementation class Z_deciceSelete
 */
@WebServlet("/Z_DeviceSelect")
public class Z_DeviceSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DeviceSelect() {
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
		List<DeviceInfo> deviceName =null;
		String customerNo = request.getParameter("customerNo");
		String area = request.getParameter("area");
		String no = request.getParameter("no");
		int page = Integer.parseInt(request.getParameter("page"));
		List<Object> p = new ArrayList<>();
		List<Object> p2 = new ArrayList<>();
		String sql ="";
		String sql2 = "";
		if (area.equals("全部")) {
			sql = "SELECT" + 
					"	deviceinfo.id," + 
					"	deviceinfo.customerNo," + 
					"	deviceinfo.name," + 
					"	deviceinfo.no," + 
					"	deviceinfo.type," + 
					"	deviceinfo.address," + 
					"	deviceinfo.createDate," + 
					"	deviceinfo.mark," + 
					"	deviceinfo.stamp," + 
					"	areainfo.`name` AS area " + 
					"FROM" + 
					"	deviceinfo," + 
					"	areainfo " + 
					"WHERE" + 
					"	deviceinfo.area = areainfo.no and customerNo = ? "
					+ " and deviceinfo.no like ? limit ?,15";
			sql2="SELECT COUNT(*) FROM DeviceInfo where customerNo = ?  and no like ?";
			p.add(customerNo);

			p.add("%"+no+"%");
			p.add((page-1)*15);
			p2.add(customerNo);
			
			p2.add("%"+no+"%");
		}
		else {
			sql = "SELECT" + 
					"	deviceinfo.id," + 
					"	deviceinfo.customerNo," + 
					"	deviceinfo.name," + 
					"	deviceinfo.no," + 
					"	deviceinfo.type," + 
					"	deviceinfo.address," + 
					"	deviceinfo.createDate," + 
					"	deviceinfo.mark," + 
					"	deviceinfo.stamp," + 
					"	areainfo.`name` AS area " + 
					"FROM" + 
					"	deviceinfo," + 
					"	areainfo " + 
					"WHERE" + 
					"	deviceinfo.area = areainfo.no and customerNo = ? "
					+ "and deviceinfo.area = ? and deviceinfo.no like ? limit ?,15";
			sql2="SELECT COUNT(*) FROM DeviceInfo where customerNo = ? and area = ? and no like ?";
			p.add(customerNo);
			p.add(area);
			p.add("%"+no+"%");
			p.add((page-1)*15);
			p2.add(customerNo);
			p2.add(area);
			p2.add("%"+no+"%");
		}
		
		int a=GetTotalDao.selectTotal(sql2, p2);
		PageBean<DeviceInfo> pbDevice = new PageBean<>(page, a);
		deviceName = Z_deviceCrudDao.selectDevice(sql, p);
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
