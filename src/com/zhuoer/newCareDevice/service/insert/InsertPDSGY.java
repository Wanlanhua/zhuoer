package com.zhuoer.newCareDevice.service.insert;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Pdsgy;
import com.zhuoer.newCareDevice.dao.InsertDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class InsertPDSGY
 */
@WebServlet("/InsertPDSGY")
public class InsertPDSGY extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPDSGY() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datetime = request.getParameter("datetime");
		String shebei = request.getParameter("shebei");
		String jine = request.getParameter("jine");
		String yougongzong ="0";
		yougongzong =request.getParameter("yougongzong");
		String yougongxuliang = request.getParameter("yougongxuliang");
		String yougongjianzhi = request.getParameter("yougongjianzhi");
		String yougongfengzhi = request.getParameter("yougongfengzhi");
		String yougongpingzhi = request.getParameter("yougongpingzhi");
		String yougongguzhi = request.getParameter("yougongguzhi");
		String wugong1 ="0";
		wugong1 =request.getParameter("wugong1");
		String wugong2 ="0";
		wugong2 =request.getParameter("wugong2");
		String wugong3 ="0";
		wugong3 =request.getParameter("wugong3");
		String wugong4 ="0";
		wugong4 =request.getParameter("wugong4");
		String gonglvyinshu = request.getParameter("gonglvyinshu");
		double s = Double.parseDouble(wugong1) + Double.parseDouble(wugong2) + Double.parseDouble(wugong3) + Double.parseDouble(wugong4);
		double q = Double.parseDouble(yougongzong);
		double p = s + q;
		Float shijigonglvyinshu = (float) (p / (Math.sqrt(Math.pow(p, 2) + Math.pow(q, 2))));
		String beilv = request.getParameter("beilv");
		String dianya = request.getParameter("dianya");
		String dianliu = request.getParameter("dianliu");
		String jiluren = request.getParameter("jiluren");
		String mark = request.getParameter("mark");
		String wendu = request.getParameter("wendu");
		String shidu = request.getParameter("shidu");
		String status = "0";
		String shenheyijian = "";
		Pdsgy qt = new Pdsgy();
		qt.setDatetime(datetime);
		qt.setShebei(shebei);
		qt.setJine(jine);
		qt.setYougongzong(Float.valueOf(yougongzong));
		qt.setYougongxuliang(Float.valueOf(yougongxuliang));
		qt.setYougongjianzhi(Float.valueOf(yougongjianzhi));
		qt.setYougongfengzhi(Float.valueOf(yougongfengzhi));
		qt.setYougongpingzhi(Float.valueOf(yougongpingzhi));
		qt.setYougongguzhi(Float.valueOf(yougongguzhi));
		qt.setWendu(Float.valueOf(wendu));
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
		qt.setMark(mark);
		qt.setShidu(shidu);
		qt.setStatus(status);
		qt.setShenheyijian(shenheyijian);
		boolean flag = InsertDao.insertPdsgy(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>配电室高压=>添加操作");
		}
		response.getWriter().print(flag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String jine = js1.getString("jine");
		
		if (jine.equals("")) {
			jine ="0";
		}
		String yougongzong ="0";
		yougongzong =js1.getString("yougongzong");
		if (yougongzong.equals("")) {
			yougongzong ="0";
		}
		String yougongxuliang = js1.getString("yougongxuliang");
		if (yougongxuliang.equals("")) {
			yougongxuliang ="0";
		}
		String yougongjianzhi = js1.getString("yougongjianzhi");
		if (yougongjianzhi.equals("")) {
			yougongjianzhi ="0";
		}
		String yougongfengzhi = js1.getString("yougongfengzhi");
		if (yougongfengzhi.equals("")) {
			yougongfengzhi ="0";
		}
		String yougongpingzhi = js1.getString("yougongpingzhi");
		if (yougongpingzhi.equals("")) {
			yougongpingzhi ="0";
		}
		String yougongguzhi = js1.getString("yougongguzhi");
		if (yougongguzhi.equals("")) {
			yougongguzhi ="0";
		}
		String wugong1 ="0";
		wugong1 =js1.getString("wugong1");
		if (wugong1.equals("")) {
			wugong1 ="0";
		}
		String wugong2 ="0";
		wugong2 =js1.getString("wugong2");
		if (wugong2.equals("")) {
			wugong2 ="0";
		}
		String wugong3 ="0";
		wugong3 =js1.getString("wugong3");
		if (wugong3.equals("")) {
			wugong3 ="0";
		}
		String wugong4 ="0";
		wugong4 =js1.getString("wugong4");
		if (wugong4.equals("")) {
			wugong4 ="0";
		}
		String gonglvyinshu = js1.getString("gonglvyinshu");
		if (gonglvyinshu.equals("")) {
			gonglvyinshu ="0";
		}
		double s =0;
		try {
			s=Double.parseDouble(wugong1) + Double.parseDouble(wugong2) + Double.parseDouble(wugong3) + Double.parseDouble(wugong4);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		double q =0;
		try {
			q=Double.parseDouble(yougongzong);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		double p = s + q;
		Float shijigonglvyinshu =(float)0;
		try {
			shijigonglvyinshu=(float) (p / (Math.sqrt(Math.pow(p, 2) + Math.pow(q, 2))));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String beilv = js1.getString("beilv");
		if (beilv.equals("")) {
			beilv ="0";
		}
		String dianya = js1.getString("dianya");
		if (dianya.equals("")) {
			dianya ="0";
		}
		String dianliu = js1.getString("dianliu");
		if (dianliu.equals("")) {
			dianliu ="0";
		}
		String jiluren = js1.getString("jiluren");
		String mark = js1.getString("mark");
		String wendu ="0";
		try {
			wendu =js1.getString("wendu");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String shidu = js1.getString("shidu");
		String status = "0";
		String shenheyijian = "";
		Pdsgy qt = new Pdsgy();
		qt.setDatetime(datetime);
		qt.setShebei(shebei);
		qt.setJine(jine);
		qt.setYougongzong(Float.valueOf(yougongzong));
		qt.setYougongxuliang(Float.valueOf(yougongxuliang));
		qt.setYougongjianzhi(Float.valueOf(yougongjianzhi));
		qt.setYougongfengzhi(Float.valueOf(yougongfengzhi));
		qt.setYougongpingzhi(Float.valueOf(yougongpingzhi));
		qt.setYougongguzhi(Float.valueOf(yougongguzhi));
		qt.setWendu(Float.valueOf(wendu));
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
		qt.setMark(mark);
		qt.setShidu(shidu);
		qt.setStatus(status);
		qt.setShenheyijian(shenheyijian);
		boolean flag = InsertDao.insertPdsgy(qt);
		String result="0";
		if(flag) {
			result="1";
			OpLogInfoTools.insertOpLogInfo(qt.getJiluren(), "看护管理=>配电室高压=>添加操作");
		}
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("result", result);
		String string = jsonObject1.toString();
		response.getWriter().write(string);
		
	}

}
