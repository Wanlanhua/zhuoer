package com.zhuoer.aJson;

import java.io.IOException;
import java.sql.PreparedStatement;
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

/**
 * Servlet implementation class GetTj
 */
@WebServlet("/GetTj")
public class GetTj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTj() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sql = "select distinct implementationDistinction from RepairRecordsInfo where implementationDistinction is not null and implementationDistinction !=\"\"";
		String tasksql = "select distinct task from repairRecordsInfo where task is not null and task !=\"\"";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String,List> map = new HashMap<String, List>();
		List<String> task = new ArrayList<>();
		List<String> implementationDistinction = new ArrayList<>();
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				implementationDistinction.add(rs.getString("implementationDistinction"));
			}
			map.put("implementationDistinction", implementationDistinction);
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = new DataBaseAccess().getPreparedStatement(tasksql);
			rs = ps.executeQuery();
			while(rs.next()) {
				task.add(rs.getString("task"));
			}
			map.put("task", task);
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().println(ToJson.toJson(map));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
