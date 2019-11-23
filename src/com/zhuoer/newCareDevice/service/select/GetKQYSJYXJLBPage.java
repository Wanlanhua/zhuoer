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

import com.zhuoer.newCareDevice.bean.Kqysjyxjlb;
import com.zhuoer.newCareDevice.dao.SelectDao;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class GetKQYSJYXJLBPage
 */
@WebServlet("/newCareDevice/GetKQYSJYXJLBPage")
public class GetKQYSJYXJLBPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetKQYSJYXJLBPage() {
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
		List<Kqysjyxjlb> list = SelectDao.getKqysjyxjlb(departmentid, shebei, starttime, endtime);
		if(list.size()>0) {
			long subffix = new Date().getTime();
			File xlsFile = new File(request.getServletContext().getRealPath("/")+"file/空气压缩机"+subffix+".xls");
			String[] arrs = {"时间","设备","排气压力","系统压力","冷却水压力","润滑油压力",
					"1#排气温度","2#排气温度","系统温度","环境温度","润滑油温度","冷却水温度",
					"主电机前轴承温度","主电机后轴承温度","运转电压","电流指示","累计运行时间","累计负荷时间","记录人","备注"};
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
					label = new Label(2,j+1,String.valueOf(list.get(j).getPaiqiyali()));
					sheet.addCell(label);
					label = new Label(3,j+1,String.valueOf(list.get(j).getXitongyali()));
					sheet.addCell(label);
					label = new Label(4,j+1,String.valueOf(list.get(j).getLengqueshuiyali()));
					sheet.addCell(label);
					label = new Label(5,j+1,String.valueOf(list.get(j).getRunhuayouyali()));
					sheet.addCell(label);
					label = new Label(6,j+1,String.valueOf(list.get(j).getPaiqiwendu1()));
					sheet.addCell(label);
					label = new Label(7,j+1,String.valueOf(list.get(j).getPaiqiwendu2()));
					sheet.addCell(label);
					label = new Label(8,j+1,String.valueOf(list.get(j).getXitongwendu()));
					sheet.addCell(label);
					label = new Label(9,j+1,String.valueOf(list.get(j).getHuanjingwendu()));
					sheet.addCell(label);
					label = new Label(10,j+1,String.valueOf(list.get(j).getRunhuayouwendu()));
					sheet.addCell(label);
					label = new Label(11,j+1,String.valueOf(list.get(j).getLengqueshuiwendu()));
					sheet.addCell(label);
					label = new Label(12,j+1,String.valueOf(list.get(j).getQzhudianjiwendu()));
					sheet.addCell(label);
					label = new Label(13,j+1,String.valueOf(list.get(j).getHzhudianjiwendu()));
					sheet.addCell(label);
					label = new Label(14,j+1,String.valueOf(list.get(j).getYunzhuandianya()));
					sheet.addCell(label);
					label = new Label(15,j+1,String.valueOf(list.get(j).getDianliuzhishi()));
					sheet.addCell(label);
					label = new Label(16,j+1,String.valueOf(list.get(j).getLeijiyunxingshijian()));
					sheet.addCell(label);
					label = new Label(17,j+1,String.valueOf(list.get(j).getLeijifuheshijian()));
					sheet.addCell(label);
					label = new Label(18,j+1,list.get(j).getJiluren());
					sheet.addCell(label);
					label = new Label(19,j+1,list.get(j).getMark());
					sheet.addCell(label);
					
				}
				workbook.write();
				workbook.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println("file/空气压缩机"+subffix+".xls");
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
