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
import com.zhuoer.qmaintance.beans.RepairRecordsInfo;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;
import com.zhuoer.qmaintance.utils.RepairRecordsTools;

/**增删改审核(维修记录表)
 * Servlet implementation class QCRUDRepairRecords
 */
@WebServlet("/QCRUDRepairRecords")
public class QCRUDRepairRecords extends HttpServlet {
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
		RepairRecordsInfo rr=new RepairRecordsInfo();
		if(request.getParameter("id")!=null)
		{
			rr.setId(Integer.parseInt(request.getParameter("id")));
		}
		rr.setDeviceNo(request.getParameter("deviceNo"));
		if(request.getParameter("repairDate") == null || request.getParameter("repairDate").equals(" "))
		{
			Date d=new Date(System.currentTimeMillis());//sql包取当前日期(年月日)
			rr.setRepairDate(d);
		}
		else
		{
			rr.setRepairDate(Date.valueOf(request.getParameter("repairDate")));
		}		
		rr.setAcceptance(request.getParameter("acceptance"));
		rr.setStartTime(request.getParameter("startTime"));
		rr.setEndTime(request.getParameter("endTime"));
		rr.setAcceptancePerson(request.getParameter("acceptancePerson"));
		rr.setTask(request.getParameter("task"));
		rr.setImplementationDistinction(request.getParameter("implementationDistinction"));
		rr.setFinish(request.getParameter("finish"));
		rr.setFinishTime(request.getParameter("finishTime"));
		rr.setMaintenanceTime(request.getParameter("maintenanceTime"));
		rr.setDownTime(request.getParameter("downTime"));
		rr.setRepairmanNumber(request.getParameter("repairmanNumber"));
		rr.setPart(request.getParameter("part"));
		rr.setContentOfInsurance(request.getParameter("contentOfInsurance"));
		rr.setRiskSourceControl(request.getParameter("riskSourceControl"));
		rr.setFaultConditions(request.getParameter("faultConditions"));
		rr.setFailureCause(request.getParameter("failureCause"));
		rr.setDisposalMethod(request.getParameter("disposalMethod"));
		rr.setConclusion(request.getParameter("conclusion"));
		rr.setRepairman(request.getParameter("repairman"));
		rr.setResponsiblePerson(request.getParameter("responsiblePerson"));
		rr.setSurveyor(request.getParameter("surveyor"));
		rr.setOils(request.getParameter("oils"));
		rr.setOilsType(request.getParameter("oilsType"));
		rr.setOilsPrice(request.getParameter("oilsPrice"));
		rr.setOilsNumber(request.getParameter("oilsNumber"));
		rr.setParts(request.getParameter("parts"));
		rr.setPartsType(request.getParameter("partsType"));
		rr.setPartsNumber(request.getParameter("partsNumber"));
		rr.setPartsPrice(request.getParameter("partsPrice"));
		rr.setWait(request.getParameter("wait"));
		rr.setOvertime(request.getParameter("overtime"));
		rr.setOvertimePerson(request.getParameter("overtimePerson"));
		rr.setState(request.getParameter("state"));
		rr.setAuditOpinion1(request.getParameter("auditOpinion1"));
		rr.setAuditOpinion2(request.getParameter("auditOpinion2"));
		rr.setAuditOpinion4(request.getParameter("auditOpinion4"));
		rr.setAuditLevel(request.getParameter("auditLevel"));
		rr.setStamp(request.getParameter("stamp"));
		rr.setMark(request.getParameter("mark"));
		rr.setPath1(request.getParameter("path1"));
		rr.setPath2(request.getParameter("path2"));
		rr.setPath3(request.getParameter("path3"));
		rr.setPath4(request.getParameter("path4"));
		rr.setPath5(request.getParameter("path5"));
		String no="";
		try {
			no=request.getParameter("no");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		rr.setNo(no);
		String operation=request.getParameter("operation");//操作类型
		
		
		switch(operation)
		{
		case"add"://添加
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>维修管理=>添加操作");
			int i=RepairRecordsTools.addRepairRecords(rr);
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
		case "del"://删除
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>维修管理=>删除操作：" + rr.getId());
			String sql="delete from repairrecordsinfo where id=?";
			int i2=RepairRecordsTools.executeUpdate(sql, rr.getId());
			String sqlD="delete from repairinfo where id=?";
			int i2D=RepairInfoTools.executeUpdate(sqlD, no);
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
		case"alt"://修改
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"保养维修=>维修管理=>修改操作：" + rr.getId());
			String sql2="update repairrecordsinfo set deviceNo=?,repairDate=?,acceptance=?,"
					+ "startTime=?,endTime=?,acceptancePerson=?,task=?,implementationDistinction=?,"
					+ "finish=?,finishTime=?,maintenanceTime=?,downTime=?,repairmanNumber=?,part=?,"
					+ "contentOfInsurance=?,riskSourceControl=?,faultConditions=?,failureCause=?,"
					+ "disposalMethod=?,conclusion=?,repairman=?,responsiblePerson=?,surveyor=?,"
					+ "oils=?,oilsType=?,oilsPrice=?,oilsNumber=?,parts=?,partsType=?,partsNumber=?,"
					+ "partsPrice=?,wait=?,overtime=?,overtimePerson=?,auditOpinion1=?,"
					+ "auditOpinion2=?,auditLevel=?,path1=?,path2=?,path3=?,path4=?,path5=?,no=?,"
					+ "stamp=?,mark=? where id=?";
			int i3=RepairRecordsTools.executeUpdate(sql2,rr.getDeviceNo(),rr.getRepairDate(),
					rr.getAcceptance(),rr.getStartTime(),rr.getEndTime(),rr.getAcceptancePerson(),
					rr.getTask(),rr.getImplementationDistinction(),rr.getFinish(),rr.getFinishTime(),
					rr.getMaintenanceTime(),rr.getDownTime(),rr.getRepairmanNumber(),rr.getPart(),
					rr.getContentOfInsurance(),rr.getRiskSourceControl(),rr.getFaultConditions(),
					rr.getFailureCause(),rr.getDisposalMethod(),rr.getConclusion(),rr.getRepairman(),
					rr.getResponsiblePerson(),rr.getSurveyor(),rr.getOils(),rr.getOilsType(),
					rr.getOilsPrice(),rr.getOilsNumber(),rr.getParts(),rr.getPartsType(),
					rr.getPartsNumber(),rr.getPartsPrice(),rr.getWait(),rr.getOvertime(),
					rr.getOvertimePerson(),rr.getAuditOpinion1(),rr.getAuditOpinion2(),
					rr.getAuditLevel(),rr.getPath1(),rr.getPath2(),rr.getPath3(),rr.getPath4(),
					rr.getPath5(),rr.getNo(),rr.getStamp(),rr.getMark(),rr.getId());
			//修改报修表完成情况
			String fString="";
			if (rr.getFinish().equals("完成")) {
				fString="1";
			}
			if (rr.getFinish().equals("未完成")) {
				fString="2";
			}
			if (!fString.equals("")) {
				String sqlU="update repairinfo set state2=? where id=?";
				int i2U=RepairInfoTools.executeUpdate(sqlU, fString,no);
			}
			
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
			if (rr.getState().equals("1"))
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>维修管理=>一级审核通过：" + rr.getId());
			else
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>维修管理=>一级审核未通过：" + rr.getId());
			String sql3="update repairrecordsinfo set state=?,auditOpinion1=? where id=?";
			
			int i4=RepairRecordsTools.executeUpdate(sql3, rr.getState(),rr.getAuditOpinion1(),rr.getId());
			
			
			String sqlU1="update repairinfo set state=?,auditOpinion1=? where id=?";
			int i2U1=RepairInfoTools.executeUpdate(sqlU1,rr.getState(),rr.getAuditOpinion1(),no);
			
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
			if (rr.getState().equals("3"))
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>维修管理=>二级审核通过：" + rr.getId());
			else
				OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
						"保养维修=>维修管理=>二级审核未通过：" + rr.getId());
			String sql4="update repairrecordsinfo set state=? ,auditOpinion2=? where id=?";
			
			int i5=RepairRecordsTools.executeUpdate(sql4, rr.getState(),rr.getAuditOpinion2(),rr.getId());
			String sqlU2="update repairinfo set state=?,auditOpinion2=? where id=?";
			int i2U2=RepairInfoTools.executeUpdate(sqlU2,rr.getState(),rr.getAuditOpinion2(),no);
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
