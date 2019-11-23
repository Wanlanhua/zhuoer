package com.zhuoer.newCareDevice.service.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

/**
 * Servlet implementation class GetEmployee
 */
@WebServlet("/GetEmployee")
public class GetEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String area = request.getParameter("area");
		String sql = "select name from areainfo where no='"+area+"'";
		ResultSet rs =  null;
		String areas = "";
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				areas = rs.getString("name");
			}
			rs.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sql = "select no,name from employeeinfo where area like '%"+areas+"%'";
		Map<String,String> map = new LinkedHashMap<>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				map.put(rs.getString("no"), rs.getString("name"));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
