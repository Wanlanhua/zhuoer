package com.zhuoer.statistical.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.statistical.dao.Dao;
import com.zhuoer.statistical.dto.Statistical;
import com.zhuoer.statistical.param.Param;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class exportExcel
 */
@WebServlet("/exportStatisticalExcel")
public class ExportStatisticalExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportStatisticalExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String deviceno = request.getParameter("deviceno");
		String devicename = request.getParameter("devicename");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String task = request.getParameter("task");
		String implementationDistinction = request.getParameter("implementationDistinction");
		String jihuastarttime = request.getParameter("jihuastarttime");
		String jihuaendtime = request.getParameter("jihuaendtime");
		String acceptancePerson = request.getParameter("acceptancePerson");
		String finish = request.getParameter("finish");
		String partname = request.getParameter("partname");
		String partno = request.getParameter("partno");
		String part = request.getParameter("part");
		String area = request.getParameter("area");
		String repairman = request.getParameter("repairman");
		Param param = new Param();
		param.setDeviceno(deviceno);
		param.setDevicename(devicename);
		param.setStarttime(starttime);
		param.setEndtime(endtime);
		param.setTask(task);
		param.setImplementationDistinction(implementationDistinction);
		param.setJihuastarttime(jihuastarttime);
		param.setJihuaendtime(jihuaendtime);
		param.setAcceptancePerson(acceptancePerson);
		param.setFinish(finish);
		param.setPartname(partname);
		param.setPartno(partno);
		param.setArea(area);
		param.setPart(part);
		param.setRepairman(repairman);
		List<Statistical> list = Dao.get(param);
		if(list.size()>0) {
			long subffix = new Date().getTime();
			File xlsFile = new File(request.getServletContext().getRealPath("/")+"file/维修统计"+subffix+".xls");
			String[] arrs = {"设备号","报修日期","接报时刻","开始时刻","终了时刻","接报人","作业分区","实施分区","完成与否","保全修理时间","设备停机时间","保全人数","部位","故障原因","处置方法","维修人","配件名称","配件数量","备注"};
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
					label = new Label(0,j+1,list.get(j).getDeviceNo());
					sheet.addCell(label);
					label = new Label(1,j+1,list.get(j).getRepairDate());
					sheet.addCell(label);
					label = new Label(2,j+1,list.get(j).getAcceptance());
					sheet.addCell(label);
					label = new Label(3,j+1,list.get(j).getStartTime());
					sheet.addCell(label);
					label = new Label(4,j+1,list.get(j).getEndTime());
					sheet.addCell(label);
					label = new Label(5,j+1,list.get(j).getAcceptancePerson());
					sheet.addCell(label);
					label = new Label(6,j+1,list.get(j).getTask());
					sheet.addCell(label);
					label = new Label(7,j+1,list.get(j).getImplementationDistinction());
					sheet.addCell(label);
					label = new Label(8,j+1,list.get(j).getFinish());
					sheet.addCell(label);
					label = new Label(9,j+1,list.get(j).getMaintenanceTime());
					sheet.addCell(label);
					label = new Label(10,j+1,list.get(j).getDownTime());
					sheet.addCell(label);
					label = new Label(11,j+1,list.get(j).getRepairmanNumber());
					sheet.addCell(label);
					label = new Label(12,j+1,list.get(j).getPart());
					sheet.addCell(label);
					label = new Label(13,j+1,list.get(j).getFailureCause());
					sheet.addCell(label);
					label = new Label(14,j+1,list.get(j).getDisposalMethod());
					sheet.addCell(label);
					label = new Label(15,j+1,list.get(j).getRepairman());
					sheet.addCell(label);
					label = new Label(16,j+1,list.get(j).getPartsName());
					sheet.addCell(label);
					label = new Label(17,j+1,list.get(j).getPartsNumber());
					sheet.addCell(label);
					label = new Label(18,j+1,list.get(j).getMark());
					sheet.addCell(label);
				}
				workbook.write();
				workbook.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println("file/维修统计"+subffix+".xls");
		} else {
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
