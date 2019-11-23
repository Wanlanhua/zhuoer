package com.zhuoer.newCareDevice.service.update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Jzgqzlljlb;
import com.zhuoer.newCareDevice.dao.UpdateDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class UpdateJZGQZLLJLB
 */
@WebServlet("/UpdateJZGQZLLJLB")
public class UpdateJZGQZLLJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateJZGQZLLJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String datetime = request.getParameter("datetime");
		String qiti = request.getParameter("qiti");
		String shebei = request.getParameter("shebei");
		String liuliangshuzhi = request.getParameter("liuliangshuzhi");
		String yalizhi = request.getParameter("yalizhi");
		String jiluren = request.getParameter("jiluren");
		String mark = request.getParameter("mark");
		Jzgqzlljlb qt = new Jzgqzlljlb();
		qt.setId(Integer.valueOf(id));
		qt.setDatetime(datetime);
		qt.setQiti(qiti);
		qt.setShebei(shebei);
		qt.setLiuliangshuzhi(liuliangshuzhi);
		qt.setYalizhi(yalizhi);
		qt.setJiluren(jiluren);
		qt.setMark(mark);
		boolean flag = UpdateDao.updateJzgqzlljlb(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>集中供气站流量记录表=>更新操作");
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
