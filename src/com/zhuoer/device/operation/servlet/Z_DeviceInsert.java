package com.zhuoer.device.operation.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.zhuoer.device.dao.Z_deviceCrudDao;
import com.zhuoer.device.util.BarcodeFactory;
import com.zhuoer.device.util.QRCode;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

import net.sf.json.JSON;

/**
 * Servlet implementation class Z_deviceInsert
 */
@WebServlet("/Z_DeviceInsert")
public class Z_DeviceInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DeviceInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 添加接口
	 * 接收：customerNo，area，no，name，type，address，createDate，mark（其中stamp 这里自动添加当前时间）；
	 * 返回:json数据 true或false；
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String customerNo = request.getParameter("customerNo").trim();
		String area=request.getParameter("area").trim();
		String no = request.getParameter("no").trim();
		String name = request.getParameter("name").trim();
		String address = request.getParameter("address").trim();
		String createDate = request.getParameter("createDate").trim();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stamp = sdf.format(date).trim();
		String mark = request.getParameter("mark").trim();
		Map<String, String> map = new HashMap<>();
		List<Object> p = new ArrayList<>();
		p.add(customerNo);
		p.add(area);
		p.add(no);
		p.add(name);
		p.add(address);
		p.add(createDate);
		p.add(stamp);
		p.add(mark);
		if (customerNo=="") {
			response.sendRedirect("addDevice.jsp");
		}else {
			String s = "select count(*) from deviceInfo where no = ?";
			boolean flag = Z_deviceCrudDao.selcetRepeat(s,no);
			if (flag) {
				String sql = "insert into deviceinfo(customerNo,area,no,name,address,createDate,stamp,mark)values(?,?,?,?,?,?,?,?)";
				boolean f=Z_deviceCrudDao.insertDeviceInfo(sql, p);
				Map<String, Object> qr = new HashMap();
				qr.put("no", no);
				qr.put("name", name);
				String json = gson.toJson(qr);
				String path = request.getSession().getServletContext().getRealPath("/")+"QR/";
				//QRCode.createQR(json, path, no+".png");
				BarcodeFactory
				.encode(json,
						800,800, path+"loginimg.png", path+no+".jpg");	
				if (f) {
					OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
							"设备信息=>基本信息=>插入操作");
					map.put("message", "添加成功!");
				}else {
					map.put("message", "添加失败!");
				}
			}else {
				map.put("message", "设备号已存在!");
			}
		}
		out.println(gson.toJson(map));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
