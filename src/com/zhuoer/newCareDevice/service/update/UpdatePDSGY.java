package com.zhuoer.newCareDevice.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Pdsgy;
import com.zhuoer.newCareDevice.dao.UpdateDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class UpdatePDSGY
 */
@WebServlet("/UpdatePDSGY")
public class UpdatePDSGY extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePDSGY() {
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
		String jine = request.getParameter("jine");
		String yougongzong = request.getParameter("yougongzong");
		String yougongxuliang = request.getParameter("yougongxuliang");
		String yougongjianzhi = request.getParameter("yougongjianzhi");
		String yougongfengzhi = request.getParameter("yougongfengzhi");
		String yougongpingzhi = request.getParameter("yougongpingzhi");
		String yougongguzhi = request.getParameter("yougongguzhi");
		String wugong1 = request.getParameter("wugong1");
		String wugong2 = request.getParameter("wugong2");
		String wugong3 = request.getParameter("wugong3");
		String wugong4 = request.getParameter("wugong4");
		String gonglvyinshu = request.getParameter("gonglvyinshu");
		double s = Double.parseDouble(wugong1) + Double.parseDouble(wugong2) + Double.parseDouble(wugong3) + Double.parseDouble(wugong4);
		double q = Double.parseDouble(yougongzong);
		double p = s + q;
		Float shijigonglvyinshu = (float) (p / (Math.sqrt(Math.pow(p, 2) + Math.pow(q, 2))));
		String beilv = request.getParameter("beilv");
		String dianya = request.getParameter("dianya");
		String dianliu = request.getParameter("dianliu");
		String jiluren = request.getParameter("jiluren");
		String wendu = request.getParameter("wendu");
		String shidu = request.getParameter("shidu");
		String mark = request.getParameter("mark");
		Pdsgy qt = new Pdsgy();
		qt.setId(Integer.valueOf(id));
		qt.setDatetime(datetime);
		qt.setShebei(shebei);
		qt.setJine(jine);
		qt.setYougongzong(Float.valueOf(yougongzong));
		qt.setYougongxuliang(Float.valueOf(yougongxuliang));
		qt.setYougongjianzhi(Float.valueOf(yougongjianzhi));
		qt.setYougongfengzhi(Float.valueOf(yougongfengzhi));
		qt.setYougongpingzhi(Float.valueOf(yougongpingzhi));
		qt.setYougongguzhi(Float.valueOf(yougongguzhi));
		qt.setWugong1(Float.valueOf(wugong1));
		qt.setWugong2(Float.valueOf(wugong2));
		qt.setWugong3(Float.valueOf(wugong3));
		qt.setWugong4(Float.valueOf(wugong4));
		qt.setGonglvyinshu(Float.valueOf(gonglvyinshu));
		qt.setShijigonglvyinshu(Float.valueOf(shijigonglvyinshu));
		qt.setBeilv(beilv);
		qt.setDianya(dianya);
		qt.setDianliu(Float.valueOf(dianliu));
		qt.setJiluren(jiluren);
		qt.setWendu(Float.valueOf(wendu));
		qt.setShidu(shidu);
		qt.setMark(mark);
		boolean flag = UpdateDao.updatePdsgy(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>配电室高压=>更新操作");
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
