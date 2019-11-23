package com.zhuoer.qmaintance.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.qmaintance.beans.MaintenanceRecords;
import com.zhuoer.qmaintance.beans.RepairRecordsInfo;
import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;
import com.zhuoer.qmaintance.utils.RepairRecordsTools;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**根据不同条件条件返回维修记录信息
 * Servlet implementation class QRepairRecordsInfo
 */
@WebServlet("/QRepairRecordsInfoSendExcel")
public class QRepairRecordsInfoSendExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域问题
		
		//��������
		String startTime=request.getParameter("startdate");
		String endTime=request.getParameter("enddate");
		String deviceNo=request.getParameter("deviceNo");
		String customerNo=request.getParameter("customerNo");
		String spage = request.getParameter("spage");
		String state=request.getParameter("state");
		String finish=request.getParameter("finish");
		
		//多条件查询
		String sql="select repairrecordsinfo.*,deviceinfo.name as deviceinfoname from deviceinfo,repairrecordsinfo where  deviceinfo.no=repairrecordsinfo.deviceNo";
		if(deviceNo!=null&& !deviceNo.equals(""))
		{
			sql+=" and repairrecordsinfo.deviceNo='"+deviceNo+"'";
		}
		if(customerNo!=null&& !customerNo.equals(""))
		{
			sql+=" and deviceinfo.customerNo='"+customerNo+"'";
		}
		
		if(startTime!=null&& !startTime.equals(""))
		{
			sql+=" and repairDate>='"+startTime+"'";
		}
		if(endTime!=null&& !endTime.equals(""))
		{
			sql+=" and repairDate<='"+endTime+"'";
		}
		if(state!=null && !state.equals(""))
		{
			sql+=" and state = '"+state+"'";
		}
		if(finish!=null && !finish.equals(""))
		{
			sql+=" and finish = '"+finish+"'";
		}
		
		//查询维修记录表中的数据
				ArrayList<Map<String,Object>> records = (ArrayList<Map<String,Object>>) RepairRecordsTools.executeQuary(sql);
				
				
				if(records.size()>0) {
					long subffix = new Date().getTime();
					File xlsFile = new File(request.getServletContext().getRealPath("/")+"file/维修记录表"+subffix+".xls");
					String[] arrs = {"审核状态","一级审核意见","二级审核意见","报修日期",
							"接报时刻","开始时刻","终了时刻","接报人","作业区分","实施区分",
							"完成状态","保全修理时间","设备停机时间","保全人数","部位","故障原因",
							"处置方法","维修人","配件和数量","备注","设备编号","设备名称"};
					Label label = null;
					try {
						xlsFile.createNewFile();
						WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
						WritableSheet sheet = workbook.createSheet("sheet1", 0);
						for(int i=0; i<arrs.length; i++) {
							label = new Label(i,0,arrs[i]);
							sheet.addCell(label);
						}
						for(int j=0;j<records.size(); j++) {
							
							
							String endstate="";
		            		if(records.get(j).get("state").equals("0"))
		            		{
		            			endstate="提交";
		            		}
		            		if(records.get(j).get("state").equals("1"))
		            		{
		            			endstate="一级审核通过";
		            		}
		            		if(records.get(j).get("state").equals("2"))
		            		{
		            			endstate="一级审核未通过";
		            		}
		            		if(records.get(j).get("state").equals("3"))
		            		{
		            			endstate="二级审核通过";
		            		}
		            		if(records.get(j).get("state").equals("4"))
		            		{
		            			endstate="二级审核未通过";
		            		}
		            		
							
							
							
							
							label = new Label(0,j+1,endstate);
							sheet.addCell(label);
							label = new Label(1,j+1,(records.get(j).get("auditOpinion1"))==null?"无":(records.get(j).get("auditOpinion1").toString()));
							sheet.addCell(label);
							label = new Label(2,j+1,(records.get(j).get("auditOpinion2"))==null?"无":(records.get(j).get("auditOpinion2").toString()));
							sheet.addCell(label);
							label = new Label(3,j+1, records.get(j).get("repairDate")==null?"无":(records.get(j).get("repairDate").toString()));
							sheet.addCell(label);
							label = new Label(4,j+1,records.get(j).get("acceptance")==null?"无":(records.get(j).get("acceptance").toString()));
							sheet.addCell(label);
							label = new Label(5,j+1, records.get(j).get("startTime")==null?"无":(records.get(j).get("startTime").toString()));
							sheet.addCell(label);
							label = new Label(6,j+1, records.get(j).get("endTime")==null?"无":(records.get(j).get("endTime").toString()));
							sheet.addCell(label);
							label = new Label(7,j+1, records.get(j).get("acceptancePerson")==null?"无":(records.get(j).get("acceptancePerson").toString()));
							sheet.addCell(label);
							label = new Label(8,j+1,records.get(j).get("task")==null?"无":(records.get(j).get("task").toString()));
							sheet.addCell(label);
							label = new Label(9,j+1,records.get(j).get("implementationDistinction")==null?"无":(records.get(j).get("implementationDistinction").toString()));
							sheet.addCell(label);
							label = new Label(10,j+1, records.get(j).get("finish")==null?"无":(records.get(j).get("finish").toString()));
							sheet.addCell(label);
							label = new Label(11,j+1, records.get(j).get("maintenanceTime")==null?"无":(records.get(j).get("maintenanceTime").toString()));
							sheet.addCell(label);
							label = new Label(12,j+1, records.get(j).get("downTime")==null?"无":(records.get(j).get("downTime").toString()));
							sheet.addCell(label);
							label = new Label(13,j+1, records.get(j).get("repairmanNumber")==null?"无":(records.get(j).get("repairmanNumber").toString()));
							sheet.addCell(label);
							label = new Label(14,j+1, records.get(j).get("part")==null?"无":(records.get(j).get("part").toString()));
							sheet.addCell(label);
							label = new Label(15,j+1, records.get(j).get("failureCause")==null?"无":(records.get(j).get("failureCause").toString()));
							sheet.addCell(label);
							label = new Label(16,j+1, records.get(j).get("disposalMethod")==null?"无":(records.get(j).get("disposalMethod").toString()));
							sheet.addCell(label);
							label = new Label(17,j+1, records.get(j).get("repairman")==null?"无":(records.get(j).get("repairman").toString()));
							sheet.addCell(label);
							label = new Label(18,j+1, records.get(j).get("parts")==null?"无":(records.get(j).get("parts").toString()));
							sheet.addCell(label);
							label = new Label(19,j+1, records.get(j).get("mark")==null?"无":(records.get(j).get("mark").toString()));
							sheet.addCell(label);
							label = new Label(20,j+1, records.get(j).get("deviceNo")==null?"无":(records.get(j).get("deviceNo").toString()));
							sheet.addCell(label);
							label = new Label(21,j+1, records.get(j).get("deviceinfoname")==null?"无":(records.get(j).get("deviceinfoname").toString()));
							sheet.addCell(label);
						}
						workbook.write();
						workbook.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.getWriter().println("file/维修记录表"+subffix+".xls");
				}
				else {
					response.getWriter().println("empty");
				}
	}

}
