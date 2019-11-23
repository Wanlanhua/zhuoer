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
import com.zhuoer.device.dao.Z_devicePartInfoCrudDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class Z_deviceDelete
 */
@WebServlet("/Z_DevicePartInfoDelete")
public class Z_DevicePartInfoDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DevicePartInfoDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 删除接口
	 *  接收：id
	 *  返回:json数据 true或false；
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String id = request.getParameter("id");
		String sql="delete from devicepartInfo where id=?";
		List<Object> p = new ArrayList<>();
		p.add(id);
		boolean flag = Z_devicePartInfoCrudDao.updateDevicePartInfo(sql, p);
		Map<String, Object> map=new HashMap<String, Object>();
		if (flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"设备信息=>部位定义=>删除操作:"+id);
			map.put("message", "删除成功!");
		}else {
			map.put("message", "删除失败!");
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
