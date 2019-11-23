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
import com.zhuoer.device.dao.Z_DandPInfoCrudDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

/**
 * Servlet implementation class Z_DandMInfoSelectServlet
 */
@WebServlet("/Z_DandPInfoInsertServlet")
public class Z_DandPInfoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DandPInfoInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *表DandPInfo的插入添加
	 *接收  deviceNo name  mark
	 *返回： 添加成功or添加失败
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String deviceNo = request.getParameter("deviceNo").trim();
		String name = request.getParameter("name").trim();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stamp = sdf.format(date).trim();
		String mark = request.getParameter("mark").trim();
		List<Object> p = new ArrayList<>();
		p.add(deviceNo);
		p.add(name);
		p.add(stamp);
		p.add(mark);
		String sqlFind="select * from DandPInfo where deviceNo='"+deviceNo+"' and name='"+name+"'";
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlFind);
		if (listAll.size()>0) {
			Map<String, String> map = new HashMap();
			map.put("message", "该部位已经关联!");
			String nameString = gson.toJson(map);
			out.println(nameString);
			return;
		}
		String sql = "insert into DandPInfo(deviceNo,name,stamp,mark)values(?,?,?,?)";
		boolean flag = Z_DandPInfoCrudDao.insertDandPInfo(sql,p);
		Map<String, Object> map=new HashMap<String, Object>();
		if(flag){
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"设备信息=>部位关联=>添加操作");
			map.put("message", "添加成功!");
			String message = gson.toJson(map);
			out.println(message);
		}else{
			map.put("message", "添加失败!");
			String message2 = gson.toJson(map);
			out.println(message2);
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
