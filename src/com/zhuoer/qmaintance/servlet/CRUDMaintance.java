package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhuoer.qmaintance.beans.MaintenanceRecords;
import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**增删改审核(保养记录表)
 * Servlet implementation class CRUDMaintance
 */
@WebServlet("/CRUDMaintance")
public class CRUDMaintance extends HttpServlet {
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
		
		//接收数据,封装为对象
		MaintenanceRecords mr=new MaintenanceRecords();
		if((request.getParameter("id"))!=null)
		{
			mr.setId(Integer.parseInt(request.getParameter("id")));
		}		
		mr.setDeviceNo(request.getParameter("deviceNo"));
		mr.setContent(request.getParameter("content"));
		mr.setPart(request.getParameter("part"));
		mr.setOil(request.getParameter("oil"));
		mr.setType(request.getParameter("type"));
		mr.setFill(request.getParameter("fill"));
		mr.setMethod(request.getParameter("method"));
		mr.setTpye(request.getParameter("tpye"));
		mr.setReason(request.getParameter("reason"));
		mr.setStandard(request.getParameter("standard"));
		mr.setPnumber(request.getParameter("pnumber"));
		mr.setMnumber(request.getParameter("mnumber"));
		mr.setSecurity(request.getParameter("security"));
		mr.setTool(request.getParameter("tool"));		
		mr.setCycle(request.getParameter("cycle"));
		if(request.getParameter("maintanceDate") == null || (request.getParameter("maintanceDate")).equals(""))
		{
			Date d=new Date(System.currentTimeMillis());//sql包取当前日期(年月日)
			mr.setMaintenanceDate(d);
		}
		else
		{
			mr.setMaintenanceDate(Date.valueOf(request.getParameter("maintanceDate")));
		}		
		mr.setMaintenancePerson(request.getParameter("maintenancePerson"));
		mr.setState1("0");
		mr.setDate1(request.getParameter("date1"));
		mr.setState2("0");
		mr.setPerson2(request.getParameter("person2"));
		mr.setDate2(request.getParameter("date2"));
		mr.setState(request.getParameter("state"));
		mr.setAuditOpinion1(request.getParameter("auditOpinion1"));
		mr.setAuditOpinion2(request.getParameter("auditOpinion2"));
		mr.setStamp(new Date(System.currentTimeMillis()).toString());
		mr.setMark(request.getParameter("mark"));
		mr.setTime(request.getParameter("time"));
		mr.setAuditOpinion4(request.getParameter("contentMark"));
		String operation=request.getParameter("operation");//操作CRUD
		
		switch(operation){
		case "add" ://添加数据
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>定保管理=>增加操作");
			int i=MaintenanceRecordsTools.addMaintance(mr);
			if(i>0)
			{
				Map m=new HashMap();
				m.put("message", "ok");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			else
			{
				Map m=new HashMap();
				m.put("message", "error");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			break;
		case "del"://删除数据
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>定保管理=>删除操作：" + mr.getId());
			String sql="delete from maintenancerecordsinfo where id=?";
			int i1=MaintenanceRecordsTools.executeUpdate(sql, mr.getId());
			if(i1>0)
			{
				Map m=new HashMap();
				m.put("message", "ok");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			else
			{
				Map m=new HashMap();
				m.put("message", "error");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			break;
		case "alt"://修改数据
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>定保管理=>修改操作：" + mr.getId());
			String state1=request.getParameter("state1");
			String state2=request.getParameter("state2");
			String sql2="update maintenancerecordsinfo set deviceNo=?,content=?,part=?,"
					+ "oil=?,type=?,fill=?,method=?,tpye=?,reason=?,standard=?,"
					+ "security=?,tool=?,pnumber=?,mnumber=?,cycle=?,maintenanceDate=?,maintenancePerson=?,"
					+ "state1=?,date1=?,state2=?,person2=?,date2=?,auditOpinion1=?,"
					+ "auditOpinion2=?,stamp=?,mark=?,time=?,auditOpinion4=? where id=?";
			int i2=MaintenanceRecordsTools.executeUpdate(sql2, mr.getDeviceNo(),mr.getContent(),
					mr.getPart(),mr.getOil(),mr.getType(),mr.getFill(),mr.getMethod(),mr.getTpye(),
					mr.getReason(),mr.getStandard(),mr.getSecurity(),mr.getTool(),mr.getPnumber(),
					mr.getMnumber(),mr.getCycle(),mr.getMaintenanceDate(),mr.getMaintenancePerson(),
					state1,mr.getDate1(),state2,mr.getPerson2(),mr.getDate2(),
					mr.getAuditOpinion1(),mr.getAuditOpinion2(),mr.getStamp(),
					mr.getMark(),mr.getTime(),mr.getAuditOpinion4().toString(), mr.getId());
			if(i2>0)
			{
				Map m=new HashMap();
				m.put("message", "ok");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			else
			{
				Map m=new HashMap();
				m.put("message", "error");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			break;
		case "one"://一级审核
			if (mr.getState().equals("1"))
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>定保管理=>一级审核通过：" + mr.getId());
			else
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>定保管理=>一级审核未通过：" + mr.getId());
			String sql3="update maintenancerecordsinfo set state=?,auditOpinion1=? where id=?";
			int i3=MaintenanceRecordsTools.executeUpdate(sql3, mr.getState(),mr.getAuditOpinion1(),mr.getId());
			if(i3>0)
			{
				Map m=new HashMap();
				m.put("message", "ok");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			else
			{
				Map m=new HashMap();
				m.put("message", "error");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			break;
		case "two"://二级审核
			if (mr.getState().equals("3"))
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>定保管理=>二级审核通过：" + mr.getId());
			else
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>定保管理=>二级审核未通过：" + mr.getId());
			String sql4="update maintenancerecordsinfo set state=?,auditOpinion2=? where id=?";
			int i4=MaintenanceRecordsTools.executeUpdate(sql4, mr.getState(),mr.getAuditOpinion2(),mr.getId());
			if(i4>0)
			{
				Map m=new HashMap();
				m.put("message", "ok");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			else
			{
				Map m=new HashMap();
				m.put("message", "error");
				Gson gson=new Gson();
				String s=gson.toJson(m);
				response.getWriter().write(s);
			}
			break;
		}

	}

}
