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
import com.zhuoer.device.dao.Z_MaintenanceInfoCrudDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

/**
 * Servlet implementation class Z_deviceInsert
 */
@WebServlet("/Z_MaintenanceInfoInsert")
public class Z_MaintenanceInfoInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_MaintenanceInfoInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 添加接口
	 * 接收：customerNo，content，mark（其中stamp 这里自动添加当前时间）；
	 * 返回:json数据 true或false；
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String customerNo = request.getParameter("customerNo").trim();
		String content = request.getParameter("name").trim();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stamp = sdf.format(date).trim();
		String mark = request.getParameter("mark").trim();
		List<Object> p = new ArrayList<>();
		p.add(customerNo);
		p.add(content);
		p.add(stamp);
		p.add(mark);
		Gson gson = new Gson();
		Map<String, Object> map=new HashMap<String, Object>();
		String sqlFind="select * from MaintenanceInfo where content='"+content+"'";
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlFind);
		if (listAll.size()>0) {
			map.put("message", "该保养项名称已存在!");
			String nameString = gson.toJson(map);
			out.println(nameString);
			return;
		}
		String sql = "insert into MaintenanceInfo(customerNo,content,stamp,mark)values(?,?,?,?)";
		boolean flag=Z_MaintenanceInfoCrudDao.insertMaintenanceInfo(sql, p);
		
		if (flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"设备信息=>保养信息=>添加操作");
			map.put("message", "添加成功!");
		}else {
			map.put("message", "添加成功!");
		}
		String nameString = gson.toJson(map);
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
