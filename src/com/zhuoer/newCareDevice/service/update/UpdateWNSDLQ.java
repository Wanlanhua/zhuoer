package com.zhuoer.newCareDevice.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Wnsdlq;
import com.zhuoer.newCareDevice.dao.UpdateDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class UpdateWNSDLQ
 */
@WebServlet("/UpdateWNSDLQ")
public class UpdateWNSDLQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWNSDLQ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String datetime = request.getParameter("datetime");
		String dianliu = request.getParameter("dianliu");
		String dianliang = request.getParameter("dianliang");
		String shebei = request.getParameter("shebei");
		String mark = request.getParameter("mark");
		String jiluren = request.getParameter("jiluren");
		Wnsdlq qt = new Wnsdlq();
		qt.setJiluren(jiluren);
		qt.setId(Integer.parseInt(id));
		qt.setDatetime(datetime);
		qt.setDianliu(Float.valueOf(dianliu));
		qt.setDianliang(Float.valueOf(dianliang));
		qt.setShebei(shebei);
		qt.setMark(mark);
		boolean flag = UpdateDao.updateWnsdlq(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>万能式断路器=>更新操作");
		}
		response.getWriter().print(flag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
