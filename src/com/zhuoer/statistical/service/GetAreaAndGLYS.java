package com.zhuoer.statistical.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.aJsonAPI.Util.ToJson;
import com.zhuoer.statistical.dto.JsonData;

/**
 * Servlet implementation class GetAreaAndGLYS
 */
@WebServlet("/GetAreaAndGLYS")
public class GetAreaAndGLYS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAreaAndGLYS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deviceno = request.getParameter("deviceno");
		String sql = "select b.no,b.name from deviceinfo a, areainfo b where a.area = b.no and a.no = '"+deviceno+"'";
		ResultSet rs = null;
		Map<String,String> map = new HashMap();
		try {
			rs = new DataBaseAccess().query(sql);
			if(rs.next()) {
				map.put(rs.getString("no"), rs.getString("name"));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map> list = new ArrayList<>();
		list.add(map);
		JsonData jd = new JsonData<Map>();
		jd.setStatus(true);
		jd.setMessage("");
		jd.setContent(list);
		response.getWriter().println(ToJson.toJson(list));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
