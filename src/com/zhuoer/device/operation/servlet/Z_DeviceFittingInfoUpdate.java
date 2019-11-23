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
 * Servlet implementation class Z_deviceUpdate
 */
@WebServlet("/Z_DeviceFittingInfoUpdate")
public class Z_DeviceFittingInfoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DeviceFittingInfoUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *更新接口
	 *接收：id，name，mark（其中id用做条件不更改）
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
		String no =request.getParameter("no").trim();
		String type1=request.getParameter("type1").trim();
		String type2=request.getParameter("type2").trim();
		String type=request.getParameter("type").trim();
		String name = request.getParameter("name").trim();
		String qty=request.getParameter("qty").trim();
		String manufactor=request.getParameter("manufactor").trim();      
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stamp = sdf.format(date).trim();
		String mark = request.getParameter("mark").trim();
		String sql="update DeviceFittingInfo set no=?, type1=?,type2=?,type=?,"
				+ "name=?,qty=?,manufactor=?,stamp=? ,mark=? where id=?";
		List<Object> p = new ArrayList<>();
		p.add(no);
		p.add(type1);
		p.add(type2);
		p.add(type);
		p.add(name);
		p.add(qty);
		p.add(manufactor);
		p.add(stamp);
		p.add(mark);
		p.add(id);
		boolean flag = Z_DeviceFittingInfoCrudDao.updateDeviceFittingInfo(sql, p);
		Map<String, Object> map=new HashMap<String, Object>();
		if (flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"设备信息=>配件辅料=>修改操作:"+id);
			map.put("message", "修改成功!");
		}else {
			map.put("message", "修改失败!");
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
