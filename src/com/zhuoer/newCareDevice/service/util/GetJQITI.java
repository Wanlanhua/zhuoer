package com.zhuoer.newCareDevice.service.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

/**
 * Servlet implementation class GetJQITI
 */
@WebServlet("/GetJQITI")
public class GetJQITI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetJQITI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "select name from qiti";
		List<Map> l = new ArrayList<>();
		try {
			ResultSet rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				Map<String,String> list = new LinkedHashMap<String, String>();
				list.put("name",rs.getString("name"));
				l.add(list);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("result", "1");
		map.put("msg", "");
		map.put("content", l);
		response.getWriter().println(GS.ToJson(map));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
