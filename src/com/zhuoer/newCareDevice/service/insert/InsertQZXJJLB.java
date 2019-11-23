package com.zhuoer.newCareDevice.service.insert;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Qzxjjlb;
import com.zhuoer.newCareDevice.dao.InsertDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class InsertQZXJJLB
 */
@WebServlet("/InsertQZXJJLB")
public class InsertQZXJJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertQZXJJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datetime = request.getParameter("datetime");
		String qiti = request.getParameter("qiti");
		String shebei = request.getParameter("shebei");
		String cunguanyali = request.getParameter("cunguanyali");
		String yeweidushu = request.getParameter("yeweidushu");
		String zhongliang = request.getParameter("zhongliang");
		String zongliuliangbiaoma = request.getParameter("zongliuliangbiaoma");
		String diyayali = request.getParameter("diyayali");
		String zongliuliangbiaokuang = request.getParameter("zongliuliangbiaokuang");
		String zongliuliangwendu = request.getParameter("zongliuliangwendu");
		String tiaoyaxiangshuiwei = request.getParameter("tiaoyaxiangshuiwei");
		String tiaoyaxiangwendu = request.getParameter("tiaoyaxiangwendu");
		String tiaoyaxiangyali = request.getParameter("tiaoyaxiangyali");
		String fureqishuiwei = request.getParameter("fureqishuiwei");
		String zengxiaoxiangyali = request.getParameter("zengxiaoxiangyali");
		String fureqiwendu = request.getParameter("fureqiwendu");
		String kongyashiqihuaqiyunxingzhuangtai = request.getParameter("kongyashiqihuaqiyunxingzhuangtai");
		String qixiangfamen = request.getParameter("qixiangfamen");
		String yexiangfamen = request.getParameter("yexiangfamen");
		String chukoufamen = request.getParameter("chukoufamen");
		String famenlianjiechu = request.getParameter("famenlianjiechu");
		String daguanshiyongqingkuang = request.getParameter("daguanshiyongqingkuang");
		String jiluren = request.getParameter("jiluren");
		String mark = request.getParameter("mark");
		String status = "0";
		String shenheyijian = "";
		Qzxjjlb qt = new Qzxjjlb();
		qt.setJiluren(jiluren);
		qt.setDatetime(datetime);
		qt.setQiti(qiti);
		qt.setShebei(shebei);
		qt.setCunguanyali(cunguanyali);
		qt.setYeweidushu(yeweidushu);
		qt.setZhongliang(zhongliang);
		qt.setZongliuliangbiaoma(zongliuliangbiaoma);
		qt.setDiyayali(diyayali);
		qt.setZongliuliangbiaokuang(zongliuliangbiaokuang);
		qt.setZongliuliangwendu(zongliuliangwendu);
		qt.setTiaoyaxiangshuiwei(tiaoyaxiangshuiwei);
		qt.setTiaoyaxiangwendu(tiaoyaxiangwendu);
		qt.setTiaoyaxiangyali(tiaoyaxiangyali);
		qt.setFureqishuiwei(fureqishuiwei);
		qt.setZengxiaoxiangyali(zengxiaoxiangyali);
		qt.setFureqiwendu(fureqiwendu);
		qt.setKongyashiqihuaqiyunxingzhuangtai(kongyashiqihuaqiyunxingzhuangtai);
		qt.setQixiangfamen(qixiangfamen);
		qt.setQixiangfamen(qixiangfamen);
		qt.setYexiangfamen(yexiangfamen);
		qt.setChukoufamen(chukoufamen);
		qt.setFamenlianjiechu(famenlianjiechu);
		qt.setDaguanshiyongqingkuang(daguanshiyongqingkuang);
		qt.setMark(mark);
		qt.setStatus(status);
		qt.setShenheyijian(shenheyijian);
		boolean flag = InsertDao.insertQzxjjlb(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>气站巡检记录表=>添加操作");
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
		String qiti = js1.getString("qiti");
		String shebei = js1.getString("shebei");
		String cunguanyali = js1.getString("cunguanyali");
		String yeweidushu = js1.getString("yeweidushu");
		String zhongliang = js1.getString("zhongliang");
		String zongliuliangbiaoma = js1.getString("zongliuliangbiaoma");
		String diyayali = js1.getString("diyayali");
		String zongliuliangbiaokuang = js1.getString("zongliuliangbiaokuang");
		String zongliuliangwendu = js1.getString("zongliuliangwendu");
		String tiaoyaxiangshuiwei = js1.getString("tiaoyaxiangshuiwei");
		String tiaoyaxiangwendu = js1.getString("tiaoyaxiangwendu");
		String tiaoyaxiangyali = js1.getString("tiaoyaxiangyali");
		String fureqishuiwei = js1.getString("fureqishuiwei");
		String zengxiaoxiangyali = js1.getString("zengxiaoxiangyali");
		String fureqiwendu = js1.getString("fureqiwendu");
		String kongyashiqihuaqiyunxingzhuangtai = js1.getString("kongyashiqihuaqiyunxingzhuangtai");
		String qixiangfamen = js1.getString("qixiangfamen");
		String yexiangfamen = js1.getString("yexiangfamen");
		String chukoufamen = js1.getString("chukoufamen");
		String famenlianjiechu = js1.getString("famenlianjiechu");
		String daguanshiyongqingkuang = js1.getString("daguanshiyongqingkuang");
		String jiluren = js1.getString("jiluren");
		String mark = js1.getString("mark");
		String status = "0";
		String shenheyijian = "";
		Qzxjjlb qt = new Qzxjjlb();
		qt.setJiluren(jiluren);
		qt.setDatetime(datetime);
		qt.setQiti(qiti);
		qt.setShebei(shebei);
		qt.setCunguanyali(cunguanyali);
		qt.setYeweidushu(yeweidushu);
		qt.setZhongliang(zhongliang);
		qt.setZongliuliangbiaoma(zongliuliangbiaoma);
		qt.setDiyayali(diyayali);
		qt.setZongliuliangbiaokuang(zongliuliangbiaokuang);
		qt.setZongliuliangwendu(zongliuliangwendu);
		qt.setTiaoyaxiangshuiwei(tiaoyaxiangshuiwei);
		qt.setTiaoyaxiangwendu(tiaoyaxiangwendu);
		qt.setTiaoyaxiangyali(tiaoyaxiangyali);
		qt.setFureqishuiwei(fureqishuiwei);
		qt.setZengxiaoxiangyali(zengxiaoxiangyali);
		qt.setFureqiwendu(fureqiwendu);
		qt.setKongyashiqihuaqiyunxingzhuangtai(kongyashiqihuaqiyunxingzhuangtai);
		qt.setQixiangfamen(qixiangfamen);
		qt.setQixiangfamen(qixiangfamen);
		qt.setYexiangfamen(yexiangfamen);
		qt.setChukoufamen(chukoufamen);
		qt.setFamenlianjiechu(famenlianjiechu);
		qt.setDaguanshiyongqingkuang(daguanshiyongqingkuang);
		qt.setMark(mark);
		qt.setStatus(status);
		qt.setShenheyijian(shenheyijian);
		boolean flag = InsertDao.insertQzxjjlb(qt);
		String result="0";
		if(flag) {
			result="1";
			OpLogInfoTools.insertOpLogInfo(qt.getJiluren(), "看护管理=>气站巡检记录表=>添加操作");
		}
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("result", result);
		String string = jsonObject1.toString();
		response.getWriter().write(string);
	}

}
