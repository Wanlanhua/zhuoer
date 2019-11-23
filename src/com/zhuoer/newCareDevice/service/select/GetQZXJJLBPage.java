package com.zhuoer.newCareDevice.service.select;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.newCareDevice.bean.Qzxjjlb;
import com.zhuoer.newCareDevice.dao.SelectDao;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class GetQZXJJLBPage
 */
@WebServlet("/newCareDevice/GetQZXJJLBPage")
public class GetQZXJJLBPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQZXJJLBPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shebei = "";
		String departmentid = "";
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		if(request.getParameter("shebei")!=null) {
			shebei = request.getParameter("shebei");
		}
		if(request.getParameter("departmentid")!=null) {
			departmentid = request.getParameter("departmentid");
		}
		starttime = request.getSession().getAttribute("starttime").toString();
		endtime = request.getSession().getAttribute("endtime").toString();
		List<Qzxjjlb> list = SelectDao.getQzxjjlb(departmentid, shebei, starttime, endtime);
		if(list.size()>0) {
			long subffix = new Date().getTime();
			File xlsFile = new File(request.getServletContext().getRealPath("/")+"file/气站巡检记录表"+subffix+".xls");
			String[] arrs = {"时间","设备","气体","贮罐压力","液位读数","重量","总流量记表码","低压压力","总流量计标况"
					,"总流量计温度","调压箱温度","调压箱水位	","调压箱压力","复热器水位","增效箱压力",
					"复热器温度","空压式气化器运行状态","气象阀门","液相阀门","出口阀门","阀门连接处漏点","大罐使用情况","记录人","备注"};
			Label label = null;
			try {
				xlsFile.createNewFile();
				WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
				WritableSheet sheet = workbook.createSheet("sheet1", 0);
				for(int i=0; i<arrs.length; i++) {
					label = new Label(i,0,arrs[i]);
					sheet.addCell(label);
				}
				for(int j=0;j<list.size(); j++) {
					label = new Label(0,j+1,list.get(j).getDatetime());
					sheet.addCell(label);
					label = new Label(1,j+1,list.get(j).getShebei());
					sheet.addCell(label);
					label = new Label(2,j+1,list.get(j).getQiti());
					sheet.addCell(label);
					label = new Label(3,j+1,String.valueOf(list.get(j).getCunguanyali()));
					sheet.addCell(label);
					label = new Label(4,j+1,String.valueOf(list.get(j).getYeweidushu()));
					sheet.addCell(label);
					label = new Label(5,j+1,String.valueOf(list.get(j).getZhongliang()));
					sheet.addCell(label);
					label = new Label(6,j+1,String.valueOf(list.get(j).getZongliuliangbiaoma()));
					sheet.addCell(label);
					label = new Label(7,j+1,String.valueOf(list.get(j).getDiyayali()));
					sheet.addCell(label);
					label = new Label(8,j+1,String.valueOf(list.get(j).getZongliuliangbiaokuang()));
					sheet.addCell(label);
					label = new Label(9,j+1,String.valueOf(list.get(j).getZongliuliangwendu()));
					sheet.addCell(label);
					label = new Label(10,j+1,String.valueOf(list.get(j).getTiaoyaxiangwendu()));
					sheet.addCell(label);
					label = new Label(11,j+1,String.valueOf(list.get(j).getTiaoyaxiangshuiwei()));
					sheet.addCell(label);
					label = new Label(12,j+1,String.valueOf(list.get(j).getTiaoyaxiangyali()));
					sheet.addCell(label);
					label = new Label(13,j+1,String.valueOf(list.get(j).getFureqishuiwei()));
					sheet.addCell(label);
					label = new Label(14,j+1,String.valueOf(list.get(j).getZengxiaoxiangyali()));
					sheet.addCell(label);
					label = new Label(15,j+1,String.valueOf(list.get(j).getFureqiwendu()));
					sheet.addCell(label);
					label = new Label(16,j+1,String.valueOf(list.get(j).getKongyashiqihuaqiyunxingzhuangtai()));
					sheet.addCell(label);
					label = new Label(17,j+1,String.valueOf(list.get(j).getQixiangfamen()));
					sheet.addCell(label);
					label = new Label(18,j+1,String.valueOf(list.get(j).getYexiangfamen()));
					sheet.addCell(label);
					label = new Label(19,j+1,String.valueOf(list.get(j).getChukoufamen()));
					sheet.addCell(label);
					label = new Label(20,j+1,String.valueOf(list.get(j).getFamenlianjiechu()));
					sheet.addCell(label);
					label = new Label(21,j+1,String.valueOf(list.get(j).getDaguanshiyongqingkuang()));
					sheet.addCell(label);
					label = new Label(22,j+1,String.valueOf(list.get(j).getJiluren()));
					sheet.addCell(label);
					label = new Label(23,j+1,String.valueOf(list.get(j).getMark()));
					sheet.addCell(label);
				}
				workbook.write();
				workbook.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println("file/气站巡检记录表"+subffix+".xls");
		}
		else {
			response.getWriter().println("empty");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
