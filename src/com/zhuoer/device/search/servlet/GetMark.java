package com.zhuoer.device.search.servlet;
/*
 * ��ȡ����������Ŀ�ı�׼�빤ʱ
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhuoer.device.dao.MaintenanceDao;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

@WebServlet("/GetMark")
public class GetMark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMark() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String tableName = request.getParameter("tableName");
		String content = request.getParameter("content");
		String sqlAll="select mark from "+tableName+" where content='"+content+"'";
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlAll);
		List<String> markList = new ArrayList<>();
		if (listAll.size()>0) {
			markList.add(listAll.get(0).get("mark").toString());
		} else {
			markList.add("");
		}
		out.print(gson.toJson(markList));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
