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
import com.zhuoer.device.dao.Z_DeviceFittingInfoCrudDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class Z_deviceInsert
 */
@WebServlet("/Z_DeviceFittingInfoInsert")
public class Z_DeviceFittingInfoInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DeviceFittingInfoInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 添加接口
	 * 接收：customerNo，no，type1，type2，name，qty,manufactor，mark（其中stamp 这里自动添加当前时间）；
	 * 返回: 跳转页面 + 提示框
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();
		String customerNo = request.getParameter("customerNo").trim();
		String no =request.getParameter("no").toUpperCase().trim();
		String type1=request.getParameter("type1").trim();
		String type2=request.getParameter("type2").trim();
		String type = request.getParameter("type").trim();
		String name = request.getParameter("name").trim();
		String qty=request.getParameter("qty").trim();
		String manufactor=request.getParameter("manufactor").trim();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stamp = sdf.format(date).trim();
		String mark = request.getParameter("mark").trim();

		List<Object> p = new ArrayList<>();
		p.add(customerNo);
		p.add(no);
		p.add(type1);
		p.add(type2);
		p.add(type);
		p.add(name);
		p.add(qty);
		p.add(stamp);
		p.add(manufactor);
		p.add(mark);
		if (customerNo=="") {
			response.sendRedirect("addDeviceFitting.jsp");
		}else {
			String s = "select count(*) from DeviceFittingInfo where no = ?";
			boolean flag = Z_DeviceFittingInfoCrudDao.selcetRepeat(s, no);
			if (flag) {
				String sql = "insert into DeviceFittingInfo(customerNo,no,type1,type2,type,name,"
						+ "qty,stamp,manufactor,mark)values(?,?,?,?,?,?,?,?,?,?)";
				boolean f=Z_DeviceFittingInfoCrudDao.insertDeviceFittingInfo(sql, p);
				if (f) {
					OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
							"设备信息=>配件辅料=>添加操作");
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
