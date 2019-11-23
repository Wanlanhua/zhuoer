package com.zhuoer.dataStatistics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.qmaintance.utils.RepairInfoTools;
@WebServlet("/AddPowerInfoServlet")
public class AddPowerInfoServlet extends HttpServlet{
	public AddPowerInfoServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();	
        String m2no="";
        m2no=request.getParameter("m2no").trim();
        if (m2no.length()<3) {
        	out.write("m2no数据长度不符合规定");        	
		}
        
		String sqlAll="SELECT DISTINCT(no) FROM powerinfo";		
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlAll);
		for (int i = 0; i < listAll.size(); i++) {
			String sql1="SELECT * FROM powerinfo where m2no like '"+m2no.substring(0, 3)+"%' and no='"+listAll.get(i).get("no")+"'";
			List<Map<String, Object>> listM=RepairInfoTools.executeQuary(sql1);
			if (listM.size()>0) {
				String sqlIn="INSERT INTO powerinfo(no,m2no,visible,input,modify,auditing,auditing2,del,find) VALUES(?,?,'"+
			                                        listM.get(0).get("visible")+"','"+
			                                        listM.get(0).get("input")+"','"+
			                                        listM.get(0).get("modify")+"','"+
			                                        listM.get(0).get("auditing")+"','"+
			                                        listM.get(0).get("auditing2")+"','"+
			                                        listM.get(0).get("del")+"','"+
			                                        listM.get(0).get("find")+"')";
				int k=RepairInfoTools.executeUpdate(sqlIn, listAll.get(i).get("no"),m2no);
			} else {
				String sqlIn="INSERT INTO powerinfo(no,m2no,visible,input,modify,auditing,auditing2,del,find) VALUES(?,?,0,0,0,0,0,0,1)";
				int k=RepairInfoTools.executeUpdate(sqlIn, listAll.get(i).get("no"),m2no);
			}
			
		}
		
		
        out.write("添加完毕");
	}

}
