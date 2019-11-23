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
 * 推送
 */
@WebServlet("/QPushAndUserInfo")
public class QPushAndUserInfo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		StringBuffer json1 = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				json1.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String name = jsonObject.getString("name");
		String channelId = jsonObject.getString("channelId");
		jsonObject.clear();
		String sql = "select * from pushanduserinfo where name='" + name + "'";
		List<Map<String, Object>> listW=RepairInfoTools.executeQuary(sql);
		if (listW.size()>0) {
			String sql2 = "update pushanduserinfo set channelId='" + channelId + "' where name='" + name
					+ "'";
			int i2 = Tools.executeUpdate(sql2);
			if (i2 > 0) {
				JSONObject jsonObject3 = new JSONObject();
				jsonObject3.put("result", "1");
				jsonObject3.put("msg", "成功");
				String s3 = jsonObject3.toString();
				resp.getWriter().write(s3);
			} else {
				JSONObject jsonObject4 = new JSONObject();
				jsonObject4.put("result", "0");
				jsonObject4.put("msg", "失败");
				String s4 = jsonObject4.toString();
				resp.getWriter().write(s4);
			}
		} else {
			String sql1 = "insert into pushanduserinfo(name,channelId) values(?,?)";
			int i = Tools.executeUpdate(sql1, name, channelId);
			if (i != 0) {
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("result", "1");
				jsonObject2.put("msg", "成功");
				String s2 = jsonObject2.toString();
				resp.getWriter().write(s2);
			} else {
				JSONObject jsonObject1 = new JSONObject();
				jsonObject1.put("result", "0");
				jsonObject1.put("msg", "失败");
				String s1 = jsonObject1.toString();
				resp.getWriter().write(s1);

			}
		}
		
//		ResultSet rs = Tools.executeQuary(sql);
//		try {
//			if (rs!=null) {
//					if (!(rs.first()))
//					{
//						String sql1 = "insert into pushanduserinfo(name,channelId) values(?,?)";
//						int i = Tools.executeUpdate(sql1, name, channelId);
//						if (i != 0) {
//							JSONObject jsonObject2 = new JSONObject();
//							jsonObject2.put("result", "1");
//							jsonObject2.put("msg", "成功");
//							String s2 = jsonObject2.toString();
//							resp.getWriter().write(s2);
//						} else {
//							JSONObject jsonObject1 = new JSONObject();
//							jsonObject1.put("result", "0");
//							jsonObject1.put("msg", "失败");
//							String s1 = jsonObject1.toString();
//							resp.getWriter().write(s1);
//
//						}
//					} else
//					{
//						rs.previous();
//						while (rs.next()) {
//							String channelId1 = rs.getString("channelId");
//							if (!(channelId.equals(channelId1))) {
//								String sql2 = "update pushanduserinfo set channelId='" + channelId + "' where name='" + name
//										+ "'";
//								int i2 = Tools.executeUpdate(sql2);
//								if (i2 > 0) {
//									JSONObject jsonObject3 = new JSONObject();
//									jsonObject3.put("result", "1");
//									jsonObject3.put("msg", "成功");
//									String s3 = jsonObject3.toString();
//									resp.getWriter().write(s3);
//								} else {
//									JSONObject jsonObject4 = new JSONObject();
//									jsonObject4.put("result", "0");
//									jsonObject4.put("msg", "失败");
//									String s4 = jsonObject4.toString();
//									resp.getWriter().write(s4);
//								}
//							} else {
//								JSONObject jsonObject5 = new JSONObject();
//								jsonObject5.put("result", "1");
//								jsonObject5.put("msg", "成功");
//								String s5 = jsonObject5.toString();
//								resp.getWriter().write(s5);
//							}
//
//						}
//					}
//				
//
//			}else
//			{
//				JSONObject joss=new JSONObject();
//				joss.put("result", "2");
//				joss.put("msg","失败");
//				resp.getWriter().write(joss.toString());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				if(Tools.ps!=null)
//				{
//					Tools.ps.close();
//				}
//				if(rs!=null)
//				{
//					rs.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
