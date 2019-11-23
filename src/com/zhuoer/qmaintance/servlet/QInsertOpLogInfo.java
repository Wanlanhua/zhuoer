package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.qmaintance.beans.OpLogInfo;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

/**添加日志信息
 * Servlet implementation class QInsertOpLogInfo
 */
@WebServlet("/QInsertOpLogInfo")
public class QInsertOpLogInfo extends HttpServlet {
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
		
		//接收数据封装对象
		OpLogInfo ol=new OpLogInfo();
		ol.setNo(request.getParameter("no"));
		ol.setContent(request.getParameter("content"));
		//转换日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date stamp = null;
		try {
			stamp = sdf.parse(request.getParameter("stamp"));
			ol.setStamp(stamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ol.setMark(request.getParameter("mark"));
		
		//开始执行数据库操作
		String sql="insert into OpLogInfo(no,content,stamp,mark) values(?,?,?,?)";
		int i=RepairInfoTools.executeUpdate(sql, ol.getNo(),ol.getContent(),ol.getStamp(),ol.getMark());
		if(i>0)
		{
			System.out.println("插入成功");
		}
		else
		{
			System.out.println("插入失败");
		}
		
		
		
	}

}
