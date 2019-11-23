package com.zhuoer.newCareDevice.service.insert;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Kqysjyxjlb;
import com.zhuoer.newCareDevice.dao.InsertDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class InsertKQYSJYXJLB
 */
@WebServlet("/InsertKQYSJYXJLB")
public class InsertKQYSJYXJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertKQYSJYXJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String jiluren = request.getParameter("jiluren");
		String mark = request.getParameter("mark");
		
		Kqysjyxjlb qt = new Kqysjyxjlb();
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
		qt.setJiluren(jiluren);
		qt.setStatus("0");
		qt.setShenheyijian("");
		boolean flag = InsertDao.insertKqysjyxjlb(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>空气压缩机=>添加操作");
		}
		response.getWriter().println(flag);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String id  = "";
		String datetime = js1.getString("datetime");
		String shebei = js1.getString("shebei");
		String paiqiyali = js1.getString("paiqiyali");
		String xitongyali = js1.getString("xitongyali");
		
		String jiluren = js1.getString("jiluren");
		String lengqueshuiyali = js1.getString("lengqueshuiyali");
		String runhuayouyali = js1.getString("runhuayouyali");
		String paiqiwendu1 = js1.getString("paiqiwendu1");
		String paiqiwendu2 = js1.getString("paiqiwendu2");
		String xitongwendu = js1.getString("xitongwendu");
		String huanjingwendu = js1.getString("huanjingwendu");
		String runhuayouwendu = js1.getString("runhuayouwendu");
		String lengqueshuiwendu = js1.getString("lengqueshuiwendu");
		String qzhudianjiwendu = js1.getString("qzhudianjiwendu");
		String hzhudianjiwendu = js1.getString("hzhudianjiwendu");
		String yunzhuandianya = js1.getString("yunzhuandianya");
		String dianliuzhishi = js1.getString("dianliuzhishi");
		String leijiyunxingshijian = js1.getString("leijiyunxingshijian");
		String leijifuheshijian = js1.getString("leijifuheshijian");
		String mark = js1.getString("mark");
		
		Kqysjyxjlb qt = new Kqysjyxjlb();
		qt.setJiluren(jiluren);
		//qt.setId(Integer.parseInt(id));
		qt.setDatetime(datetime);
		qt.setShebei(shebei);
		float f=0;
		try {
			f=Float.valueOf(paiqiyali);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setPaiqiyali(f);
		f=0;
		try {
			f=Float.valueOf(xitongyali);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setXitongyali(f);
		f=0;
		try {
			f=Float.valueOf(lengqueshuiyali);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setLengqueshuiyali(f);
		f=0;
		try {
			f=Float.valueOf(runhuayouyali);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setRunhuayouyali(f);
		f=0;
		try {
			f=Float.valueOf(paiqiwendu1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setPaiqiwendu1(f);
		f=0;
		try {
			f=Float.valueOf(paiqiwendu2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setPaiqiwendu2(f);
		f=0;
		try {
			f=Float.valueOf(xitongwendu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setXitongwendu(f);
		f=0;
		try {
			f=Float.valueOf(huanjingwendu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setHuanjingwendu(f);
		f=0;
		try {
			f=Float.valueOf(runhuayouwendu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setRunhuayouwendu(f);
		f=0;
		try {
			f=Float.valueOf(lengqueshuiwendu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setLengqueshuiwendu(f);
		f=0;
		try {
			f=Float.valueOf(qzhudianjiwendu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setQzhudianjiwendu(f);
		f=0;
		try {
			f=Float.valueOf(hzhudianjiwendu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setHzhudianjiwendu(f);
		f=0;
		try {
			f=Float.valueOf(yunzhuandianya);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setYunzhuandianya(f);
		f=0;
		try {
			f=Float.valueOf(dianliuzhishi);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setDianliuzhishi(f);
		f=0;
		try {
			f=Float.valueOf(leijiyunxingshijian);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setLeijiyunxingshijian(f);
		f=0;
		try {
			f=Float.valueOf(leijifuheshijian);
		} catch (Exception e) {
			// TODO: handle exception
		}
		qt.setLeijifuheshijian(f);
		qt.setMark(mark);
		qt.setStatus("0");
		qt.setShenheyijian("");
		boolean flag = InsertDao.insertKqysjyxjlb(qt);
		String result="0";
		if(flag) {
			result="1";
			OpLogInfoTools.insertOpLogInfo(qt.getJiluren(), "看护管理=>空气压缩机=>添加操作");
		}
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("result", result);
		String string = jsonObject1.toString();
		response.getWriter().write(string);
		response.getWriter().println(flag);
	}

}
