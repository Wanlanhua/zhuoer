package com.zhuoer.newCareDevice.service.insert;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Dbczjlb;
import com.zhuoer.newCareDevice.dao.InsertDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class InsertDBCZJLB
 */
@WebServlet("/InsertDBCZJLB")
public class InsertDBCZJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDBCZJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datetime = request.getParameter("datetime");
		String shebei = request.getParameter("shebei");
		String qjine = request.getParameter("qjine");
		String jine = request.getParameter("jine");
		String hjine = request.getParameter("hjine");
		String jiluren = request.getParameter("jiluren");
		String mark = request.getParameter("mark");
		String status = "0";
		Dbczjlb qt = new Dbczjlb();
		qt.setDatetime(datetime);
		qt.setShebei(shebei);
		qt.setQjine(Float.valueOf(qjine));
		qt.setHjine(Float.valueOf(hjine));
		qt.setJiluren(jiluren);
		qt.setJine(Float.valueOf(jine));
		qt.setMark(mark);
		qt.setStatus(status);
		qt.setShenheyijian("");
		boolean flag = InsertDao.insertDbczjlb(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>电表充值记录表=>添加操作");
		}
		response.getWriter().print(flag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		StringBuffer json1 = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			if ((line = reader.readLine()) != null) {
				json1.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 接受数据
		JSONObject js1 = JSONObject.fromObject(json1.toString());
		
		String datetime = js1.getString("datetime");
		String shebei = js1.getString("shebei");
		String qjine = js1.getString("qjine");
		String jine = js1.getString("jine");
		String hjine = js1.getString("hjine");
		String jiluren = js1.getString("jiluren");
		String mark = js1.getString("mark");
		String status = "0";
		Dbczjlb qt = new Dbczjlb();
		qt.setDatetime(datetime);
		qt.setShebei(shebei);
		qt.setQjine(Float.valueOf(qjine));
		qt.setHjine(Float.valueOf(hjine));
		qt.setJiluren(jiluren);
		qt.setJine(Float.valueOf(jine));
		qt.setMark(mark);
		qt.setStatus(status);
		qt.setShenheyijian("");
		boolean flag = InsertDao.insertDbczjlb(qt);
		String result="0";
		if(flag) {
			result="1";
			OpLogInfoTools.insertOpLogInfo(qt.getJiluren(), "看护管理=>电表充值记录表=>添加操作");
		}
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("result", result);
		String string = jsonObject1.toString();
		response.getWriter().write(string);
	}

}
