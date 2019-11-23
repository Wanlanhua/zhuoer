package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.qmaintance.utils.BaiDuTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

/**自动推送润滑记录接口
 * Servlet implementation class AutoLubricationRecordsInfo
 */
@WebServlet("/AutoLubricationRecordsInfo")
public class AutoLubricationRecordsInfo extends HttpServlet {
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
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域
		
		String sendMsg="推送失败！";
		//用户编号
		String no="";
		no=(String) request.getSession().getAttribute("name");
		
		String sqlAll="";
		//分用户获取需要进行推送的报修信息
		if (no.equals("admin")) {
			sqlAll="SELECT * FROM lubricationRecordsInfo  WHERE state2!='1' and DATEDIFF(CURDATE(),maintenanceDate) BETWEEN -2 and 2";
			
		}else
		{
			sqlAll="SELECT l.* FROM lubricationRecordsInfo l,deviceinfo d,employeeinfo e WHERE l.state2!='1' and DATEDIFF(CURDATE(),maintenanceDate) BETWEEN -2 and 2 AND l.deviceNo=d.no  AND d.customerNo=e.department AND e.no='"+no+"'";
		}
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlAll);
		//循环按指派人进行推送
				for (int i = 0; i < listAll.size(); i++) {
					//指派人
					String reason=listAll.get(i).get("reason").toString();
					if (reason.contains(",")) {
						String []reasonS=reason.split(",");
						for (int j = 0; j < reasonS.length; j++) {
							String sqlGetChannelId="SELECT pushanduserinfo.channelId FROM pushanduserinfo,employeeinfo WHERE pushanduserinfo.name=employeeinfo.no and employeeinfo.name='"+reasonS[j]+"'";
							List<Map<String, Object>> listM=RepairInfoTools.executeQuary(sqlGetChannelId);
							if (listM.size()>0) {			
								String msg="以下人员:"+reason+"定保认领~"+listAll.get(i).get("id").toString()+"~"+listAll.get(i).get("deviceNo").toString()+"~"+listAll.get(i).get("state1").toString()+"~"+"0"+"~"+listAll.get(i).get("state2").toString();
								String channelId=listM.get(0).get("channelId").toString();
								if (!channelId.equals("")) {
									BaiDuTools.sendBaidu(channelId, msg);						
									sendMsg="推送成功！";
								}
								
							}
						}
						
					}
					else if (!reason.equals("")) {
						String sqlGetChannelId="SELECT pushanduserinfo.channelId FROM pushanduserinfo,employeeinfo WHERE pushanduserinfo.name=employeeinfo.no and employeeinfo.name='"+reason+"'";
						List<Map<String, Object>> listM=RepairInfoTools.executeQuary(sqlGetChannelId);
						if (listM.size()>0) {			
							String msg="以下人员:"+reason+"定保认领~"+listAll.get(i).get("id").toString()+"~"+listAll.get(i).get("deviceNo").toString()+"~"+listAll.get(i).get("state1").toString()+"~"+"0"+"~"+listAll.get(i).get("state2").toString();
							String channelId=listM.get(0).get("channelId").toString();
							if (!channelId.equals("")) {
								BaiDuTools.sendBaidu(channelId, msg);						
								sendMsg="推送成功！";
							}
							
						}
					}
				}
				response.getWriter().write(sendMsg);
		//循环每条推送报修信息，按区域推送到员工
//		for (int i = 0; i < listAll.size(); i++) {
//			//获取设备所在的区域名称
//			String sqlGetArea="SELECT a.name FROM areainfo a,deviceinfo d WHERE d.area=a.no and d.no='"+listAll.get(i).get("deviceNo").toString()+"'";
//			List<Map<String, Object>> listAreaName=RepairInfoTools.executeQuary(sqlGetArea);
//			if (listAreaName.size()>0) {
//				//按区域名称获取该区域内需要推送的员工ChannelId
//				String sqlGetChannelId="SELECT p.* FROM pushanduserinfo p,employeeinfo e  WHERE p.name=e.no  and e.area LIKE'%"+listAreaName.get(0).get("name").toString()+"%' ";
//				List<Map<String, Object>> listM=RepairInfoTools.executeQuary(sqlGetChannelId);
//				for (int j = 0; j < listM.size(); j++) {
//					//封装推送消息，逐条推送
//					if(!(listAll.get(i).get("reason").toString().equals("")))//有指派人条件下
//					{
//						String reason=listAll.get(i).get("reason").toString();//取每条记录的指派人
//						String msg="以下人员:"+reason+"润滑认领~"+listAll.get(i).get("id").toString()+"~"+listAll.get(i).get("deviceNo").toString()+"~"+listAll.get(i).get("state1").toString()+"~"+"2"+"~"+listAll.get(i).get("state2").toString();
//						String channelId=listM.get(j).get("channelId").toString();
//						BaiDuTools.sendBaidu(channelId, msg);
//						
//						sendMsg="推送成功！";
//						
//					}
//					else//没有指派人条件下
//					{
//						String msg="润滑认领~"+listAll.get(i).get("id").toString()+"~"+listAll.get(i).get("deviceNo").toString()+"~"+listAll.get(i).get("state1").toString()+"~"+"2"+"~"+listAll.get(i).get("state2").toString();
//						String channelId=listM.get(j).get("channelId").toString();
//						BaiDuTools.sendBaidu(channelId, msg);
//						sendMsg="推送成功！";
//						
//					}
//					
//				}
//				response.getWriter().write(sendMsg);
//			}
//			
//			
//		}
	}

}
