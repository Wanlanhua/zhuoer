package com.zhuoer.statistical.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.aJsonAPI.Util.ToJson;
import com.zhuoer.statistical.dao.Dao;
import com.zhuoer.statistical.dto.JsonData;
import com.zhuoer.statistical.dto.Statistical;
import com.zhuoer.statistical.param.Param;

/**
 * Servlet implementation class GetStatistical
 */
@WebServlet("/GetStatistical")
public class GetStatistical extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStatistical() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deviceno = request.getParameter("deviceno");
		String devicename = request.getParameter("devicename");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String task = request.getParameter("task");
		String implementationDistinction = request.getParameter("implementationDistinction");
		String jihuastarttime = request.getParameter("jihuastarttime");
		String jihuaendtime = request.getParameter("jihuaendtime");
		String acceptancePerson = request.getParameter("acceptancePerson");
		String finish = request.getParameter("finish");
		String partname = request.getParameter("partname");
		String partno = request.getParameter("partno");
		String part = request.getParameter("part");
		String area = request.getParameter("area");
		String repairman = request.getParameter("repairman");
		Param param = new Param();
		param.setDeviceno(deviceno);
		param.setDevicename(devicename);
		param.setStarttime(starttime);
		param.setEndtime(endtime);
		param.setTask(task);
		param.setImplementationDistinction(implementationDistinction);
		param.setJihuastarttime(jihuastarttime);
		param.setJihuaendtime(jihuaendtime);
		param.setAcceptancePerson(acceptancePerson);
		param.setFinish(finish);
		param.setPartname(partname);
		param.setPartno(partno);
		param.setArea(area);
		param.setPart(part);
		param.setRepairman(repairman);
		JsonData<Statistical> jd = new JsonData<Statistical>();
		List<Statistical> list = Dao.get(param);
		jd.setStatus(true);
		jd.setMessage("");
		jd.setContent(list);
		String json = ToJson.toJson(jd);
		response.getWriter().println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
