package com.zhuoer.device.operation.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class Z_deviceUpdate
 */
@WebServlet("/Z_DeviceUpdate")
public class Z_DeviceUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DeviceUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *更新接口
	 *接收：id，name，type，address，createDate，mark（其中id用做条件不更改）
	 * 返回:json数据 true或false；
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		String area = request.getParameter("area").trim();
		String address = request.getParameter("address").trim();
		String createDate = request.getParameter("createDate").trim();
		String mark = request.getParameter("mark").trim();
		
		String sql="update deviceinfo set name=?,area = ?, address=?,createDate=?,mark=? where id=?";
		List<Object> p = new ArrayList<>();
		p.add(name);
		p.add(area);
		p.add(address);
		p.add(createDate);
		p.add(mark);
		p.add(id);
		boolean flag = Z_deviceCrudDao.updateDevice(sql,p);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("message", flag);
		String nameString = gson.toJson(map);
		out.println(nameString);
		OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
				"设备信息=>基本信息=>修改操作:"+id);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
