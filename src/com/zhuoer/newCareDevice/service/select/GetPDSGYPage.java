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

import com.zhuoer.newCareDevice.bean.Pdsgy;
import com.zhuoer.newCareDevice.dao.SelectDao;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class GetPDSGYPage
 */
@WebServlet("/newCareDevice/GetPDSGYPage")
public class GetPDSGYPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPDSGYPage() {
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
		List<Pdsgy> list = SelectDao.getPdsgy(departmentid, shebei, starttime, endtime);
		if(list.size()>0) {
			long subffix = new Date().getTime();
			File xlsFile = new File(request.getServletContext().getRealPath("/")+"file/配电室高压"+subffix+".xls");
			String[] arrs = {"时间","设备","金额","有功总","有功需量","有功尖值",
					"有功峰值","有功平值","有功谷值","无功1","无功2","无功3",
					"无功4","功率因数","实际功率因数","温度","湿度","倍率","电压","电流","记录人","备注"};
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
					label = new Label(2,j+1,list.get(j).getJine());
					sheet.addCell(label);
					label = new Label(3,j+1,String.valueOf(list.get(j).getYougongzong()));
					sheet.addCell(label);
					label = new Label(4,j+1,String.valueOf(list.get(j).getYougongxuliang()));
					sheet.addCell(label);
					label = new Label(5,j+1,String.valueOf(list.get(j).getYougongjianzhi()));
					sheet.addCell(label);
					label = new Label(6,j+1,String.valueOf(list.get(j).getYougongfengzhi()));
					sheet.addCell(label);
					label = new Label(7,j+1,String.valueOf(list.get(j).getYougongpingzhi()));
					sheet.addCell(label);
					label = new Label(8,j+1,String.valueOf(list.get(j).getYougongguzhi()));
					sheet.addCell(label);
					label = new Label(9,j+1,String.valueOf(list.get(j).getWugong1()));
					sheet.addCell(label);
					label = new Label(10,j+1,String.valueOf(list.get(j).getWugong2()));
					sheet.addCell(label);
					label = new Label(11,j+1,String.valueOf(list.get(j).getWugong3()));
					sheet.addCell(label);
					label = new Label(12,j+1,String.valueOf(list.get(j).getWugong4()));
					sheet.addCell(label);
					label = new Label(13,j+1,String.valueOf(list.get(j).getGonglvyinshu()));
					sheet.addCell(label);
					label = new Label(14,j+1,String.valueOf(list.get(j).getShijigonglvyinshu()));
					sheet.addCell(label);
					label = new Label(15,j+1,String.valueOf(list.get(j).getWendu()));
					sheet.addCell(label);
					label = new Label(16,j+1,String.valueOf(list.get(j).getShidu()));
					sheet.addCell(label);
					label = new Label(17,j+1,list.get(j).getBeilv());
					sheet.addCell(label);
					label = new Label(18,j+1,list.get(j).getDianya());
					sheet.addCell(label);
					label = new Label(19,j+1,String.valueOf(list.get(j).getDianliu()));
					sheet.addCell(label);
					label = new Label(20,j+1,String.valueOf(list.get(j).getJiluren()));
					sheet.addCell(label);
					label = new Label(21,j+1,String.valueOf(list.get(j).getMark()));
					sheet.addCell(label);
					
				}
				workbook.write();
				workbook.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println("file/配电室高压"+subffix+".xls");
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
