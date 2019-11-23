package com.zhuoer.aJsonAPI;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.aJsonAPI.Util.ToJson;

/**
 * Servlet implementation class DepartmentId
 */
@WebServlet("/GetDepartmentId")
public class GetDepartmentId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetDepartmentId() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getSession().getAttribute("name").toString();
		String sql = "";
		Map<String, String> map = new LinkedHashMap<String, String>();
		if("admin".equals(no)) {
			sql = "select distinct(departmentid),departmentname from customerinfo;";
			try {
				ResultSet rs = new DataBaseAccess().query(sql);
				while(rs.next()) {
					map.put(rs.getString("departmentid"), rs.getString("departmentname"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println(ToJson.toJson(map));
		}
		else {
			PreparedStatement ps = null;
			String csql = "select departmentid from customerinfo where no=?";
			String esql = "select department from employeeinfo where no=?";
			String department = "";
			boolean state = false;
			try {
				ps = new DataBaseAccess().getPreparedStatement(csql);
				ps.setString(1, no);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					department = rs.getString("departmentid");
					state = true;
				}
				rs.close();
				ps.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!state) {
				try {
					ps = new DataBaseAccess().getPreparedStatement(esql);
					ps.setString(1, no);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						department = rs.getString("department");
					}
					rs.close();
					ps.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			sql = "select departmentid,departmentname from customerinfo where departmentid=?";

			try {
				ps = new DataBaseAccess().getPreparedStatement(sql);
				ps.setString(1, department);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String id = rs.getString("departmentid");
					String name = rs.getString("departmentname");
					map.put(id, name);
				}
				rs.close();
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println(ToJson.toJson(map));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
