package com.zhuoer.servlet;

import java.io.BufferedReader;
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
 * 登陆
 */
@WebServlet("/QLoginServlet")
public class QLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		StringBuffer json1 = new StringBuffer();
		String line = null;
		String no ="";
		String password ="";
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json1.append(line);
			}
			System.out.println("登录:"+json1.toString());
			JSONObject jsonObject = JSONObject.fromObject(json1.toString());
			no =jsonObject.getString("no");
			password = jsonObject.getString("password");
			jsonObject.clear();
			System.out.println(no+""+password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sqlNo = "select * from logininfo l,employeeinfo e where l.name=e.no and l.name='" + no + "' and l.password='" + password + "'";
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlNo);
		if (listAll.size()>0) {
			String name = listAll.get(0).get("name").toString();
			String department =listAll.get(0).get("department").toString();
			String post =listAll.get(0).get("post").toString() ;

			JSONObject jsonObject2 = new JSONObject();
			JSONObject jsonObject3 = new JSONObject();
			jsonObject2.put("name", name);
			jsonObject2.put("department", department);
			jsonObject2.put("post", post);
			jsonObject3.put("result", "1");
			jsonObject3.put("msg", "成功");
			jsonObject3.put("content", jsonObject2.toString());
			String string6 = jsonObject3.toString();
			resp.getWriter().write(string6);
		}
		else{
			JSONObject jsonObject4 = new JSONObject();
			jsonObject4.put("result", "0");
			jsonObject4.put("msg", "登陆失败");
			String string2 = jsonObject4.toString();
			resp.getWriter().write(string2);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
