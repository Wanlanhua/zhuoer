package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;
import com.zhuoer.qmaintance.utils.RepairRecordsTools;

/**根据id查询保养记录表,报修表,维修记录表
 * Servlet implementation class QSelectById
 */
@WebServlet("/QSelectById")
public class QSelectById extends HttpServlet {
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
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域
		
		String id=request.getParameter("id");
		String operation=request.getParameter("operation");
		switch (operation) {
		case "maintenanceRecords":
			String sql="select * from maintenancerecordsinfo where id=?";
			List li=MaintenanceRecordsTools.executeQuary(sql, id);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();			
			String json=gson.toJson(li);			
			
			response.getWriter().write(json);
			break;
		case "LubricationRecordsInfo":
			sql="select * from LubricationRecordsInfo where id=?";
			li=MaintenanceRecordsTools.executeQuary(sql, id);
			gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();			
			json=gson.toJson(li);			
			
			response.getWriter().write(json);
			break;
		case"repairInfo":
			String sql2="select * from repairinfo where id=?";
			List li2=RepairInfoTools.executeQuary(sql2, id);
			Gson gson2=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();			
			String json2=gson2.toJson(li2);			
			
			response.getWriter().write(json2);
			break;
		case"repairRecords":
			String sql3="select * from repairrecordsinfo where id=?";
			List li3=RepairRecordsTools.executeQuary(sql3, id);
			Gson gson3=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();			
			String json3=gson3.toJson(li3);			
			
			response.getWriter().write(json3);
			break;

		default:
			break;
		}
	}

}
