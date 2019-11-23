package com.zhuoer.newCareDevice.service.insert;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Wnsdlq;
import com.zhuoer.newCareDevice.dao.InsertDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class InsertWNSDLQ
 */
@WebServlet("/InsertWNSDLQ")
public class InsertWNSDLQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertWNSDLQ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datetime = request.getParameter("datetime");
		String dianliu = request.getParameter("dianliu");
		String dianliang = request.getParameter("dianliang");
		String shebei = request.getParameter("shebei");
		String mark = request.getParameter("mark");
		String status = "0";
		String shenheyijian = "";
		String jiluren = request.getParameter("jiluren");
		Wnsdlq qt = new Wnsdlq();
		qt.setJiluren(jiluren);
		qt.setDatetime(datetime);
		qt.setDianliu(Float.valueOf(dianliu));
		qt.setDianliang(Float.valueOf(dianliang));
		qt.setShebei(shebei);
		qt.setMark(mark);
		qt.setStatus(status);
		qt.setShenheyijian(shenheyijian);
		boolean flag = InsertDao.insertWnsdlq(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>万能式断路器=>添加操作");
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
		String dianliu = js1.getString("dianliu");
		String dianliang = js1.getString("dianliang");
		String shebei = js1.getString("shebei");
		String mark = js1.getString("mark");
		String status = "0";
		String shenheyijian = "";
		String jiluren = js1.getString("jiluren");
		Wnsdlq qt = new Wnsdlq();
		qt.setJiluren(jiluren);
		qt.setDatetime(datetime);
		qt.setDianliu(Float.valueOf(dianliu));
		qt.setDianliang(Float.valueOf(dianliang));
		qt.setShebei(shebei);
		qt.setMark(mark);
		qt.setStatus(status);
		qt.setShenheyijian(shenheyijian);
		boolean flag = InsertDao.insertWnsdlq(qt);
		String result="0";
		if(flag) {
			result="1";
			OpLogInfoTools.insertOpLogInfo(qt.getJiluren(), "看护管理=>万能式断路器=>添加操作");
		}
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("result", result);
		String string = jsonObject1.toString();
		response.getWriter().write(string);
	}

}
