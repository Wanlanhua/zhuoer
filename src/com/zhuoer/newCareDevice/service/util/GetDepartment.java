package com.zhuoer.newCareDevice.service.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.DataBaseAccess.DataBaseAccess;

/**
 * Servlet implementation class GetDepartmentAndArea
 */
@WebServlet("/GetDepartmentAndArea")
public class GetDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		if("".equals(no)) {
			no = "admin";
		}
		String sql = "";
		DataBaseAccess db = new DataBaseAccess();
		ResultSet rs = null;
		Map<String,String> department = new LinkedHashMap<>();
		if("admin".equals(no)) {
			sql = "select departmentid,departmentname from customerinfo ";
			try {
				rs = db.query(sql);
				while(rs.next()) {
					department.put(rs.getString("departmentid"), rs.getString("departmentname"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			sql = "select e.department as departmentid,c.departmentname as departmentname from employeeinfo e,customerinfo c where e.no='"+no+"' and e.department = c.departmentid";
			try {
				rs = db.query(sql);
				while(rs.next()) {
					department.put(rs.getString("departmentid"), rs.getString("departmentname"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Set<String> ks = department.keySet();
		String id = "";
		if(ks.size()>0) {
			for(String s : ks) {
				id = s;
				break;
			}
		}
		sql = "select no,name from areainfo where departmentid = '"+id+"'";
		Map<String,String> areas = new LinkedHashMap<>();
		try {
			rs = db.query(sql);
			while(rs.next()) {
				areas.put(rs.getString("no"), rs.getString("name"));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = "";
		Set<String> area = areas.keySet();
		if(ks.size()>0) {
			for(String s : area) {
				name = s;
				break;
			}
		}
		sql = "select no,name from deviceinfo where area = '"+name+"'";
		Map<String, String> devices = new LinkedHashMap<>();
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
		List<Map> list = new ArrayList<>();
		list.add(department);
		list.add(areas);
		list.add(devices);
		String json = GS.ToJson(list);
		response.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
