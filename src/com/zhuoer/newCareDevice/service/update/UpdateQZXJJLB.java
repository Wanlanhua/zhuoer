package com.zhuoer.newCareDevice.service.update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Qzxjjlb;
import com.zhuoer.newCareDevice.dao.UpdateDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class UpdateZXJJLB
 */
@WebServlet("/UpdateQZXJJLB")
public class UpdateQZXJJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQZXJJLB() {
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
		String mark = request.getParameter("mark");
		String jiluren = request.getParameter("jiluren");
		Qzxjjlb qt = new Qzxjjlb();
		qt.setJiluren(jiluren);
		qt.setId(Integer.valueOf(id));
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
		boolean flag = UpdateDao.updateQzxjjlb(qt);
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>气站巡检记录表=>更新操作");
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
