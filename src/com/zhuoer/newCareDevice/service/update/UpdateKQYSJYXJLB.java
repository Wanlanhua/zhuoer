package com.zhuoer.newCareDevice.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Kqysjyxjlb;
import com.zhuoer.newCareDevice.dao.UpdateDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class UpdateKQYSJYXJLB
 */
@WebServlet("/UpdateKQYSJYXJLB")
public class UpdateKQYSJYXJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateKQYSJYXJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id  = request.getParameter("id");
		String datetime = request.getParameter("datetime");
		String shebei = request.getParameter("shebei");
		String paiqiyali = request.getParameter("paiqiyali");
		String xitongyali = request.getParameter("xitongyali");
		

		String lengqueshuiyali = request.getParameter("lengqueshuiyali");
		String runhuayouyali = request.getParameter("runhuayouyali");
		String paiqiwendu1 = request.getParameter("paiqiwendu1");
		String paiqiwendu2 = request.getParameter("paiqiwendu2");
		String xitongwendu = request.getParameter("xitongwendu");
		String huanjingwendu = request.getParameter("huanjingwendu");
		String runhuayouwendu = request.getParameter("runhuayouwendu");
		String lengqueshuiwendu = request.getParameter("lengqueshuiwendu");
		String qzhudianjiwendu = request.getParameter("qzhudianjiwendu");
		String hzhudianjiwendu = request.getParameter("hzhudianjiwendu");
		String yunzhuandianya = request.getParameter("yunzhuandianya");
		String dianliuzhishi = request.getParameter("dianliuzhishi");
		String leijiyunxingshijian = request.getParameter("leijiyunxingshijian");
		String leijifuheshijian = request.getParameter("leijifuheshijian");
		String mark = request.getParameter("mark");
		String jiluren = request.getParameter("jiluren");
		
		Kqysjyxjlb qt = new Kqysjyxjlb();
		qt.setId(Integer.parseInt(id));
		qt.setDatetime(datetime);
		qt.setShebei(shebei);
		qt.setPaiqiyali(Float.valueOf(paiqiyali));
		qt.setXitongyali(Float.valueOf(xitongyali));
		qt.setLengqueshuiyali(Float.valueOf(lengqueshuiyali));
		qt.setRunhuayouyali(Float.valueOf(runhuayouyali));
		qt.setPaiqiwendu1(Float.valueOf(paiqiwendu1));
		qt.setPaiqiwendu2(Float.valueOf(paiqiwendu2));
		qt.setXitongwendu(Float.valueOf(xitongwendu));
		qt.setHuanjingwendu(Float.valueOf(huanjingwendu));
		qt.setRunhuayouwendu(Float.valueOf(runhuayouwendu));
		qt.setLengqueshuiwendu(Float.valueOf(lengqueshuiwendu));
		qt.setQzhudianjiwendu(Float.valueOf(qzhudianjiwendu));
		qt.setHzhudianjiwendu(Float.valueOf(hzhudianjiwendu));
		qt.setYunzhuandianya(Float.valueOf(yunzhuandianya));
		qt.setDianliuzhishi(Float.valueOf(dianliuzhishi));
		qt.setLeijiyunxingshijian(Float.valueOf(leijiyunxingshijian));
		qt.setLeijifuheshijian(Float.valueOf(leijifuheshijian));
		qt.setMark(mark);
		qt.setStatus("0");
		qt.setShenheyijian("");
		qt.setJiluren(jiluren);
		boolean flag = UpdateDao.updateKqysjyxjlb(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>空气压缩机=>更新操作");
		}
		response.getWriter().println(flag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
