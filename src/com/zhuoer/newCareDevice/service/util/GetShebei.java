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
 * Servlet implementation class GetShebei
 */
@WebServlet("/GetShebei")
public class GetShebei extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetShebei() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String area = request.getParameter("name");
		String sql = "select no,name from deviceinfo where area = '"+area+"'";
		Map<String, String> devices = new LinkedHashMap<>();
		ResultSet rs = null;
		DataBaseAccess db = new DataBaseAccess();
		try {
			rs = db.query(sql);
			while(rs.next()) {
				devices.put(rs.getString("no"), rs.getString("name"));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().println(GS.ToJson(devices));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
