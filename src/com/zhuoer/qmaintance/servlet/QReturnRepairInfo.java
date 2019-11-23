package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;

import net.sf.json.JSONArray;

/**返回员工姓名,设备部位,配件,设备号,设备名接口
 * Servlet implementation class QReturnRepairInfo
 */
@WebServlet("/QReturnRepairInfo")
public class QReturnRepairInfo extends HttpServlet {
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
		
		String operation=request.getParameter("operation");
		switch(operation)
		{
		case"selAreaname":
			//获取设备编号
			String deviceNoArea=request.getParameter("deviceNo");

			ArrayList liArea = null;
			String sqlArea="SELECT employeeinfo.NAME as name from employeeinfo, deviceinfo,areainfo WHERE deviceinfo.area=areainfo.no AND INSTR(employeeinfo.area,areainfo.name) AND deviceinfo.no='"+deviceNoArea+"'";			
			liArea=(ArrayList) MaintenanceRecordsTools.executeQuary(sqlArea);
			JSONArray jsonArrayArea=JSONArray.fromObject(liArea);
			String resultArea=jsonArrayArea.toString();
			
			response.getWriter().write(resultArea);
			break;
		case"selname":
			//根据用户名取对应项目部下所有员工姓名
			//从session中拿用户名
			String no=(String) request.getSession().getAttribute("name");
//			String no="1602";

			ArrayList li = null;
			String sql="SELECT name from employeeinfo WHERE department in (SELECT department from employeeinfo where `no`=?)";
			if (no.equals("admin")) {
				sql = "select name from employeeinfo";
				li=(ArrayList) MaintenanceRecordsTools.executeQuary(sql);
			}
			else
				li=(ArrayList) MaintenanceRecordsTools.executeQuary(sql, no);
			JSONArray jsonArray=JSONArray.fromObject(li);
			String result=jsonArray.toString();
			
			response.getWriter().write(result);
			break;
		case"buwei":
			//从设备与部位关联表中拉取部位名称信息
			
			String deviceNo=request.getParameter("deviceNo");
			String sql1="SELECT name from dandpinfo WHERE deviceNo=?";
			ArrayList li1=(ArrayList) MaintenanceRecordsTools.executeQuary(sql1, deviceNo);
			JSONArray jsonArray1=JSONArray.fromObject(li1);
			String result1=jsonArray1.toString();
			
			response.getWriter().write(result1);
			break;
		case"peijian":
			//从设备配件表中拉取配件名称
			deviceNo=request.getParameter("deviceNo");
			String sql2="SELECT name from devicefittinginfo WHERE no in (select no from dandfinfo where deviceNo=?)";
			ArrayList li2=(ArrayList) MaintenanceRecordsTools.executeQuary(sql2, deviceNo);
			JSONArray jsonArray2=JSONArray.fromObject(li2);
			String result2=jsonArray2.toString();
			
			response.getWriter().write(result2);
			break;
		case"shebeihao":
			//模糊查询设备号(设备信息表)
			//String customerNo2=(String) request.getSession().getAttribute("customerNo");
			String customerNo2="2";
			String math="2";
			String sql3="SELECT no from deviceinfo WHERE customerNo=? AND no LIKE ?";
			ArrayList li3=(ArrayList) MaintenanceRecordsTools.executeQuary(sql3,customerNo2,"%"+math+"%");
			JSONArray jsonArray3=JSONArray.fromObject(li3);
			String result3=jsonArray3.toString();
			
			response.getWriter().write(result3);
			break;
		case"shebeiming":
			//根据项目部编号和设备编号查询设备名称(设备信息表)
			String customerNo3="2";
			String no2="234";
			String sql4="SELECT name FROM deviceinfo WHERE customerNo=? AND no=?";
			ArrayList li4=(ArrayList) MaintenanceRecordsTools.executeQuary(sql4, customerNo3,no2);
			JSONArray jsonArray4=JSONArray.fromObject(li4);
			String result4=jsonArray4.toString();
			
			response.getWriter().write(result4);
			break;
		}
		
		
		
	}

}
