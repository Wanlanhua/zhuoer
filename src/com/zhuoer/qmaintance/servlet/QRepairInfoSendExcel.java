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
import com.zhuoer.qmaintance.utils.MaintenanceRecordsTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class QRepairInfo
 */
@WebServlet("/QRepairInfoSendExcel")
public class QRepairInfoSendExcel extends HttpServlet {
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
		
		//接收数据
		String startdate=request.getParameter("startdate");
		String enddate=request.getParameter("enddate");
		String state=request.getParameter("state");
		String state1=request.getParameter("state1");
		String state2=request.getParameter("state2");
		String deviceNo=request.getParameter("deviceNo");
		String customerNo=request.getParameter("customerNo");
		
		
		String sql="select repairinfo.*,deviceinfo.name as deviceinfoname from deviceinfo,repairinfo where  deviceinfo.no=repairinfo.deviceNo";
		if((deviceNo!=null && !deviceNo.equals("")))
		{
			sql+=" and repairinfo.deviceNo='"+deviceNo+"'";
		}
		if(customerNo!=null && !customerNo.equals(""))
		{
			sql+=" and deviceinfo.customerNo='"+customerNo+"'";
		}
		if(state!=null && !state.equals(""))
		{
			sql+=" and state = '"+state+"'";
		}
		if(state1!=null && !state1.equals(""))
		{
			sql+=" and state1 = '"+state1+"'";
		}
		if(state2!=null && !state2.equals(""))
		{
			sql+=" and state2='"+state2+"'";
		}
		if(startdate!=null && !startdate.equals(""))
		{
			sql+=" and repairDate>='"+startdate+"'";
		}
		if(enddate!=null && !enddate.equals(""))
		{
			sql+=" and repairDate<='"+enddate+"'";
		}
		
		//查询保养记录表中的数据
				ArrayList<Map<String,Object>> records =(ArrayList<Map<String,Object>>) RepairInfoTools.executeQuary(sql);
				
				
				if(records.size()>0) {
					long subffix = new Date().getTime();
					File xlsFile = new File(request.getServletContext().getRealPath("/")+"file/报修记录表"+subffix+".xls");
					String[] arrs = {"审核状态","一级审核意见","二级审核意见","提报来源",
							"报修日期","报修部门","报修时间","故障现象描述","领取状态","完成状态",
							"领取人","领取时间年月日","领取时间时分秒","计划领取人","计划领取时间","班组","挂牌人",
							"挂牌日期","故障类别","审核人","备注","设备编号","设备名称"};
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
		            		String stateone="";
		            		if(records.get(j).get("state1").equals("0"))
		            		{
		            			stateone="未领取";
		            		}
		            		if(records.get(j).get("state1").equals("1"))
		            		{
		            			stateone="领取";
		            		}
		            		String statetwo="";
		            		if(records.get(j).get("state2").equals("0"))
		            		{
		            			statetwo="未完成";
		            		}
		            		if(records.get(j).get("state2").equals("1"))
		            		{
		            			statetwo="完成";
		            		}
		            		if(records.get(j).get("state2").equals("2"))
		            		{
		            			statetwo="未完成转计划";
		            		}
							
							
							
							
							label = new Label(0,j+1,endstate);
							sheet.addCell(label);
							label = new Label(1,j+1, (records.get(j).get("auditOpinion1"))==null?"无":(records.get(j).get("auditOpinion1").toString()));
							sheet.addCell(label);
							label = new Label(2,j+1,(records.get(j).get("auditOpinion2"))==null?"无":(records.get(j).get("auditOpinion2").toString()));
							sheet.addCell(label);
							label = new Label(3,j+1, records.get(j).get("source")==null?"无":(records.get(j).get("source").toString()));
							sheet.addCell(label);
							label = new Label(4,j+1,records.get(j).get("repairDate")==null?"无":(records.get(j).get("repairDate").toString()));
							sheet.addCell(label);
							label = new Label(5,j+1, records.get(j).get("repairDepartment")==null?"无":(records.get(j).get("repairDepartment").toString()));
							sheet.addCell(label);
							label = new Label(6,j+1, records.get(j).get("repairTime")==null?"无":(records.get(j).get("repairTime").toString()));
							sheet.addCell(label);
							label = new Label(7,j+1, records.get(j).get("faultPhenomenon")==null?"无":(records.get(j).get("faultPhenomenon").toString()));
							sheet.addCell(label);
							label = new Label(8,j+1,stateone);
							sheet.addCell(label);
							label = new Label(9,j+1,statetwo);
							sheet.addCell(label);
							label = new Label(10,j+1, records.get(j).get("maintenancePerson")==null?"无":(records.get(j).get("maintenancePerson").toString()));
							sheet.addCell(label);
							try {
								label = new Label(11,j+1, records.get(j).get("date1")==null?"无":(records.get(j).get("date1").toString().equals("")?"":(records.get(j).get("date1").toString().substring(0, 10))));
							} catch (Exception e) {
								// TODO: handle exception
								label = new Label(11,j+1,"");
							}
							
							sheet.addCell(label);
							try {
								label = new Label(12,j+1, records.get(j).get("date1")==null?"无":(records.get(j).get("date1").toString().equals("")?"":(records.get(j).get("date1").toString().substring(10))));
							} catch (Exception e) {
								// TODO: handle exception
								label = new Label(12,j+1,"");
							}
							label = new Label(13,j+1, records.get(j).get("person2")==null?"无":(records.get(j).get("person2").toString()));
							sheet.addCell(label);
							label = new Label(14,j+1, records.get(j).get("date2")==null?"无":(records.get(j).get("date2").toString()));
							sheet.addCell(label);
							label = new Label(15,j+1, records.get(j).get("className")==null?"无":(records.get(j).get("className").toString()));
							sheet.addCell(label);
							label = new Label(16,j+1, records.get(j).get("person")==null?"无":(records.get(j).get("person").toString()));
							sheet.addCell(label);
							label = new Label(17,j+1, records.get(j).get("date")==null?"无":(records.get(j).get("date").toString()));
							sheet.addCell(label);
							label = new Label(18,j+1, records.get(j).get("faultCategory")==null?"无":(records.get(j).get("faultCategory").toString()));
							sheet.addCell(label);
							label = new Label(19,j+1, records.get(j).get("reviewer")==null?"无":(records.get(j).get("reviewer").toString()));
							sheet.addCell(label);
							label = new Label(20,j+1, records.get(j).get("mark")==null?"无":(records.get(j).get("mark").toString()));
							sheet.addCell(label);
							label = new Label(21,j+1, records.get(j).get("deviceNo")==null?"无":(records.get(j).get("deviceNo").toString()));
							sheet.addCell(label);
							label = new Label(22,j+1, records.get(j).get("deviceinfoname")==null?"无":(records.get(j).get("deviceinfoname").toString()));
							sheet.addCell(label);
						}
						workbook.write();
						workbook.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.getWriter().println("file/报修记录表"+subffix+".xls");
				}
				else {
					response.getWriter().println("empty");
				}
	}

}
