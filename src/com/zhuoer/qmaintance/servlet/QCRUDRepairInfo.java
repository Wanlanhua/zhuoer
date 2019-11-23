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
import com.zhuoer.qmaintance.beans.RepairInfo;
import com.zhuoer.qmaintance.beans.RepairRecordsInfo;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;
import com.zhuoer.qmaintance.utils.RepairRecordsTools;

/**报修表增删改
 * Servlet implementation class QCRUDRepairInfo
 */
@WebServlet("/QCRUDRepairInfo")
public class QCRUDRepairInfo extends HttpServlet {
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
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域问题
		
		//接收数据封装对象
		RepairInfo ri=new RepairInfo();
		if((request.getParameter("id"))!=null)
		{
			ri.setId(Integer.parseInt(request.getParameter("id")));
		}
		ri.setClassName(request.getParameter("className"));
		ri.setDeviceNo(request.getParameter("deviceNo"));
		ri.setPerson(request.getParameter("person"));
		if(request.getParameter("date")!=null)
			ri.setDate(Date.valueOf(request.getParameter("date")));
		ri.setContent(request.getParameter("content"));
		ri.setSource(request.getParameter("source"));
		ri.setAuditLevel(request.getParameter("auditLevel"));
		ri.setPath1(request.getParameter("path1"));
		ri.setPath2(request.getParameter("path2"));
		ri.setPath3(request.getParameter("path3"));
		ri.setPath4(request.getParameter("path4"));
		ri.setPath5(request.getParameter("path5"));
		
		if(request.getParameter("repairDate") == null || (request.getParameter("repairDate")).equals(""))
		{
			Date d=new Date(System.currentTimeMillis());//sql包取当前日期(年月日)
			ri.setRepairDate(d);
		}
		else
		{
			ri.setRepairDate(Date.valueOf(request.getParameter("repairDate")));
		}	
				
		ri.setRepairDepartment(request.getParameter("repairDepartment"));
		ri.setRepairTime(request.getParameter("repairTime"));
		ri.setTime(request.getParameter("time"));
		ri.setFaultPhenomenon(request.getParameter("faultPhenomenon"));
		ri.setMaintenancePerson(request.getParameter("maintenancePerson"));
		ri.setState1("0");
		ri.setDate1(request.getParameter("date1"));
		ri.setState2("0");
		ri.setPerson2(request.getParameter("person2"));
		ri.setDate2(request.getParameter("date2"));
		ri.setState(request.getParameter("state"));
		ri.setAuditOpinion1(request.getParameter("auditOpinion1"));
		ri.setAuditOpinion2(request.getParameter("auditOpinion2"));
		ri.setStamp(new Date(System.currentTimeMillis()).toString());
		ri.setMark(request.getParameter("mark"));
		ri.setFaultCategory(request.getParameter("faultCategory"));
		ri.setReviewer(request.getParameter("reviewer"));
		ri.setAuditOpinion4(request.getParameter("contentMark"));
		
		String operation=request.getParameter("operation");//����CRUD
		
		switch(operation)
		{
		case"add"://添加
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>报修管理=>增加操作");
			int i=RepairInfoTools.addRepairInfo(ri);
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
			String sql1 = "select * from repairinfo where deviceNo=? and date=? and content=? order by id desc";
			int id = (int) ((HashMap<String, Object>) RepairInfoTools.executeQuary(sql1, ri.getDeviceNo(), ri.getDate(), ri.getContent()).get(0)).get("id");
			// 添加维修信息
			RepairRecordsInfo rr=new RepairRecordsInfo();
			rr.setNo(String.valueOf(id));
			rr.setRepairDate(ri.getDate());
			rr.setDeviceNo(ri.getDeviceNo());
			RepairRecordsTools.addRepairRecords(rr);
			break;
		case "del"://删除
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>报修管理=>删除操作：" + ri.getId());
			String sql="delete from repairinfo where id=?";
			int i2=RepairInfoTools.executeUpdate(sql, ri.getId());
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
			RepairRecordsTools.executeUpdate("delete from repairrecordsinfo where no=?", ri.getId());
			break;
		case"alt"://修改
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>报修管理=>修改操作：" + ri.getId());
			String sql2="update repairinfo set className=?,deviceNo=?,person=?,date=?,content=?,source=?,"
				+ "auditLevel=?,path1=?,path2=?,path3=?,path4=?,path5=?,repairDate=?,repairDepartment=?,"
				+ "repairTime=?,faultPhenomenon=?,maintenancePerson=?,state1=?,date1=?,state2=?,person2=?,"
				+ "date2=?,auditOpinion1=?,auditOpinion2=?,stamp=?,mark=?,time=?,faultCategory=?,reviewer=? where id=?";
			int i3=RepairInfoTools.executeUpdate(sql2,ri.getClassName(),ri.getDeviceNo(),ri.getPerson()
					,ri.getDate(),ri.getContent(),ri.getSource(),ri.getAuditLevel(),ri.getPath1()
					,ri.getPath2(),ri.getPath3(),ri.getPath4(),ri.getPath5(),ri.getRepairDate()
					,ri.getRepairDepartment(),ri.getRepairTime(),ri.getFaultPhenomenon(),ri.getMaintenancePerson()
					,ri.getState1(),ri.getDate1(),ri.getState2(),ri.getPerson2(),ri.getDate2()
					,ri.getAuditOpinion1(),ri.getAuditOpinion2(),ri.getStamp(),ri.getMark(),ri.getTime()
					,ri.getFaultCategory(),ri.getReviewer(), ri.getId());
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
		case"one"://一级审核
			if (ri.getState().equals("1"))
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>报修管理=>一级审核通过：" + ri.getId());
			else
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>报修管理=>一级审核未通过：" + ri.getId());
			String sql3="update repairinfo set state=?,auditOpinion1=? where id=?";
			
			int i4=RepairRecordsTools.executeUpdate(sql3, ri.getState(),ri.getAuditOpinion1(),ri.getId());
			RepairRecordsTools.executeUpdate("update repairrecordsinfo set state=?,auditOpinion1=? where no=?", ri.getState(),ri.getAuditOpinion1(),ri.getId());
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
		case"two"://二级审核
			if (ri.getState().equals("3"))
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>报修管理=>二级审核通过：" + ri.getId());
			else
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>报修管理=>二级审核未通过：" + ri.getId());
			String sql4="update repairinfo set state=?,auditOpinion2=? where id=?";
			
			int i5=RepairRecordsTools.executeUpdate(sql4, ri.getState(),ri.getAuditOpinion2(),ri.getId());
			RepairRecordsTools.executeUpdate("update repairrecordsinfo set state=?,auditOpinion2=? where no=?", ri.getState(),ri.getAuditOpinion2(),ri.getId());
			if(i5>0)
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
