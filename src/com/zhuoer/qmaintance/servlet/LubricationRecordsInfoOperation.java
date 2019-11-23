package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhuoer.qmaintance.utils.DBTools;

@WebServlet("/LubricationRecordsInfoOperation")
public class LubricationRecordsInfoOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LubricationRecordsInfoOperation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		String deviceNo = request.getParameter("deviceNo");
		String content = request.getParameter("content");
		String no = (String) request.getSession().getAttribute("name");
		switch(operation) {
		case "getPart":response.getWriter().write(getPart(deviceNo));
			break;
		case "getMaintanInfo":response.getWriter().write(getMaintanInfo(deviceNo));
			break;
		case "getLubricationRecordsInfo":response.getWriter().write(getLubricationRecordsInfo(deviceNo));
			break;
		case "getContentMark":response.getWriter().write(getContentMark(no, content));
			break;
		}
	}

	private String getContentMark(String no, String content) {
		List<String> mark = new ArrayList<>();
		String sql = "select mark from LubricationInfo where customerNo='"+no+"' and content='"+content+"'";
		if (no.equals("admin"))
			sql = "select mark from LubricationInfo where content='"+content+"'";
		try {
			Statement stmt = DBTools.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				mark.add(rs.getString("mark"));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(mark);
	}

	// 部位
	private String getPart(String no) {
		List<String> parts = new ArrayList<>();
		String sql = "select name from dandpinfo where deviceNo='"+no+"'";
		try {
			Statement stmt = DBTools.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				parts.add(rs.getString("name"));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(parts);
	}
	private String getMaintanInfo(String deviceNo) {
		List<String> parts = new ArrayList<>();
		String sql = "select name from dandminfo where deviceNo='"+deviceNo+"'";
		try {
			Statement stmt = DBTools.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				parts.add(rs.getString("name"));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(parts);
	}
	private String getLubricationRecordsInfo(String deviceNo) {
		List<String> parts = new ArrayList<>();
		String sql = "select name from iandminfo where deviceNo='"+deviceNo+"'";
		try {
			Statement stmt = DBTools.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				parts.add(rs.getString("name"));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Gson().toJson(parts);
	}
}
