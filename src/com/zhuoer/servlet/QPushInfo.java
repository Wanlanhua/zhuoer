package com.zhuoer.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.dao.Tools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONObject;

/**
 * 推送
 */
@WebServlet("/QPushInfo")
public class QPushInfo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String sql="select apiKey from pushinfo";
		List<Map<String, Object>> listW=RepairInfoTools.executeQuary(sql);
		if (listW.size()>0) {
			String apiKey=listW.get(0).get("apiKey").toString();
			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("result", "1");
			jsonObject1.put("msg", "成功");
			jsonObject1.put("apiKey", apiKey);
			String string=jsonObject1.toString();
			resp.getWriter().write(string);
		} else {
			JSONObject jsonObject4=new JSONObject();
			 jsonObject4.put("result", "0");
			 jsonObject4.put("msg", "失败");
			 String string2=jsonObject4.toString();
			 resp.getWriter().write(string2);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
