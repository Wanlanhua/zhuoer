package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.qmaintance.beans.MaintenanceRecords;
import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;

import net.sf.json.JSONArray;

/**保养刷新
 * Servlet implementation class MaintanceFrush
 */
@WebServlet("/MaintanceFrush")
public class MaintanceFrush extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
			
		//修改完成状态״̬
			String sql="UPDATE maintenancerecordsinfo SET state2 = '2' WHERE CURDATE()<maintenanceDate and state2='0'";
			int i=MaintenanceRecordsTools.executeUpdate(sql);
			if(i>0)
			{
				//修改完成,返回数据
				String sql2="select * from maintenancerecordsinfo";
				ArrayList<MaintenanceRecords> records = MaintenanceRecordsTools.listSQL(sql2);
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();			
				String json=gson.toJson(records);			
				response.getWriter().write(json);
			}
		
	}

}
