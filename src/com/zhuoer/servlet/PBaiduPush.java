package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet("/PBaiduPush")
public class PBaiduPush extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PBaiduPush() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
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
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String di=jsonObject.getString("id");
		String deviceNo=jsonObject.getString("deviceNo");
		String contentTpye=jsonObject.getString("contentType");
		jsonObject.clear();
		String sql="";
    
		if(contentTpye.contains("润滑"))
		{
			sql ="select * from lubricationrecordsinfo where id='"+di+"' ";
		}else
		{
			sql ="select * from maintenancerecordsinfo where id='"+di+"' ";
		}
//		ResultSet rs= Tools.executeQuary(sql);
		String sqls="select * from deviceinfo where no='"+deviceNo+"'";
		List<Map<String, Object>> listW=RepairInfoTools.executeQuary(sql);
		List<Map<String, Object>> listW2=RepairInfoTools.executeQuary(sqls);
		JSONObject jo1=new JSONObject();
		for(int i=0;i<listW.size();i++)
		{
			String content="";
			try {
				content=listW.get(i).get("content").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		for(int j=0;j<listW2.size();j++)
		{
			String name="";
			try {
				name=listW2.get(j).get("name").toString();
			} catch (Exception e) {
				// TODO: handle exception
			}	
			jo1.put("name", name);
		}
		 
			jo1.put("content", content);
		}
		response.getWriter().write(jo1.toString());
		//		ResultSet rs2= Tools.executeQuary(sqls);
//		try {
//			JSONObject jo1=new JSONObject();
//			while(rs.next())
//			{
//				String content =rs.getString("content");
//				
//				String name="";
//				 while(rs2.next())
//				 {
//					 name=rs2.getString("name");
//				 }
//				 jo1.put("name", name);
//				jo1.put("content", content);
//				
//			}
//			response.getWriter().write(jo1.toString());
//		} 
	}

}
