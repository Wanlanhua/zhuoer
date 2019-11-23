package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.qmaintance.utils.RepairRecordsTools;

/**刷新维修记录表
 * Servlet implementation class QRepairRecordsFrush
 */
@WebServlet("/QRepairRecordsFrush")
public class QRepairRecordsFrush extends HttpServlet {
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
		
//		修改完成̬
		String sql="UPDATE repairrecordsinfo SET finish='���' WHERE CURDATE()<repairDate and finish='δ���'";
		int i=RepairRecordsTools.executeUpdate(sql);
		if(i>0)
		{
			String sql2="select * from repairrecordsinfo";
			ArrayList li=(ArrayList) RepairRecordsTools.executeQuary(sql2);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();			
			String json=gson.toJson(li);			
			
			response.getWriter().write(json);
		}
		else
		{
			
		}
	}

}
