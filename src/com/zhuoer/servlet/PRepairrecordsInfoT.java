package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.dao.Tools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class PRepairrecordsInfoT
 */
@WebServlet("/PRepairrecordsInfoT")
public class PRepairrecordsInfoT extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PRepairrecordsInfoT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		StringBuffer json1 = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			if ((line = reader.readLine()) != null) {
				json1.append(line);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//接受数据
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String di=jsonObject.getString("id");
		//根据id查出来 完成的状态 
		String wcsql="select state2 from   repairinfo where id='"+di+"'";
		String sql="update repairinfo set state1=?,date1=?,maintenancePerson=? where id='"+di+"'";
		List<Map<String, Object>> listem=RepairInfoTools.executeQuary(wcsql);
		String state2="";
		for (int i = 0; i < listem.size(); i++) {
			try {
				state2=listem.get(i).get("state2").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(state2.equals("1"))
		{
			JSONObject js=new JSONObject();
			js.put("result", "2");
			js.put("msg", "失败");
			 response.getWriter().write(js.toString());
		}else
		{
			int i=Tools.executeUpdate(sql,0,"","");
			if(i>0)
			{
				JSONObject js=new JSONObject();
				js.put("result", "1");
				js.put("msg", "成功");
				 response.getWriter().write(js.toString());
			}else
			{
				JSONObject js=new JSONObject();
				js.put("result", "2");
				js.put("msg", "失败");
				 response.getWriter().write(js.toString());
			}
		}
		
			
	

	}

}
