package com.zhuoer.newCareDevice.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Dbczjlb;
import com.zhuoer.newCareDevice.dao.UpdateDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class UpdateDBCZJLB
 */
@WebServlet("/UpdateDBCZJLB")
public class UpdateDBCZJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDBCZJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String datetime = request.getParameter("datetime");
		String shebei = request.getParameter("shebei");
		String qjine = request.getParameter("qjine");
		String jine = request.getParameter("jine");
		String hjine = request.getParameter("hjine");
		String jiluren = request.getParameter("jiluren");
		String mark = request.getParameter("mark");
		Dbczjlb qt = new Dbczjlb();
		qt.setId(Integer.parseInt(id));
		qt.setDatetime(datetime);
		qt.setShebei(shebei);
		qt.setQjine(Float.valueOf(qjine));
		qt.setHjine(Float.valueOf(hjine));
		qt.setJiluren(jiluren);
		qt.setJine(Float.valueOf(jine));
		qt.setMark(mark);
		boolean flag = UpdateDao.updateDbczjlb(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>电表充值记录表=>更新操作");
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
