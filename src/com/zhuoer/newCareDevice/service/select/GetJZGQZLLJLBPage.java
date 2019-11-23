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

import com.zhuoer.newCareDevice.bean.Jzgqzlljlb;
import com.zhuoer.newCareDevice.dao.SelectDao;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class GetJZGQZLLJLBPage
 */
@WebServlet("/newCareDevice/GetJZGQZLLJLBPage")
public class GetJZGQZLLJLBPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetJZGQZLLJLBPage() {
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
		List<Jzgqzlljlb> list = SelectDao.getJzgqzlljlb(departmentid, shebei, starttime, endtime);
		if(list.size()>0) {
			long subffix = new Date().getTime();
			File xlsFile = new File(request.getServletContext().getRealPath("/")+"file/集中供气站流量记录表"+subffix+".xls");
			String[] arrs = {"时间","设备","气体","流量数值","压力值","记录人","备注"};
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
					label = new Label(3,j+1,list.get(j).getLiuliangshuzhi());
					sheet.addCell(label);
					label = new Label(4,j+1,list.get(j).getYalizhi());
					sheet.addCell(label);
					label = new Label(5,j+1,list.get(j).getJiluren());
					sheet.addCell(label);
					label = new Label(6,j+1,list.get(j).getMark());
					sheet.addCell(label);
				}
				workbook.write();
				workbook.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println("file/集中供气站流量记录表"+subffix+".xls");
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
