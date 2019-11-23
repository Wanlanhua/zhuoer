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
import com.zhuoer.device.dao.Z_DandFInfoCrudDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class Z_DandMInfoSelectServlet
 */
@WebServlet("/Z_DandFInfoDeleteServlet")
public class Z_DandFInfoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DandFInfoDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *表DandFInfo的删除
	 *接收  id
	 *返回： 删除成功or删除失败
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String id = request.getParameter("id");
		List<Object> p = new ArrayList<>();
		p.add(id);
		String sql = "delete from DandFInfo where id=?";
		boolean flag = Z_DandFInfoCrudDao.deleteDandFInfo(sql,p);
		Map<String, Object> map=new HashMap<String, Object>();
		if(flag){
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"设备信息=>配件关联=>删除操作:"+id);
			map.put("message", "删除成功");
			String message = gson.toJson(map);
			out.println(message);
		}else{
			map.put("message", "删除失败");
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
