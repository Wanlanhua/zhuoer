package com.zhuoer.qmaintance.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.qmaintance.utils.BaiDuTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONObject;

/**安卓定保推送接口
 * Servlet implementation class QBaiduSendMaintenanceRecords
 */
@WebServlet("/QBaiduSendMaintenanceRecords")
public class QBaiduSendMaintenanceRecords extends HttpServlet {
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
		
		//接收前台姓名和员工编号
		String names=request.getParameter("name");
		String[] split = names.split(",");
		String id=request.getParameter("id");
		for(int i=0;i<split.length;i++)
		{
			String name=split[i];
			String channelId="";
			String sql="SELECT p.channelId FROM pushanduserinfo p,employeeinfo e  WHERE p.name=e.no and e.name=?";
			List li=RepairInfoTools.executeQuary(sql, name);
			if(li.size()>0)
			{
				Map m=new HashMap();
				m=(Map) li.get(0);
				channelId=(String) m.get("channelId");
			}
			else
			{
				continue;
			}
			
			//String sql2="select * from MaintenanceRecordsInfo where deviceNo=? and state2 in(0,2)";//未领取
			String sql2="select * from MaintenanceRecordsInfo where id=? ";//所有的都可以推送
			List<Map<String, Object>> li2=RepairInfoTools.executeQuary(sql2, id);
			String deviceNo="";
			String state1="";
			String state2="";
			if(li2.size()>0)
			{
				 //id=li2.get(0).get("id").toString();
				 deviceNo=li2.get(0).get("deviceNo").toString(); 
				 state1=li2.get(0).get("state1").toString();		
				 state2=li2.get(0).get("state2").toString();//完成状态
			}
			else
			{
				continue;
			}
			
			
			//推送给指定人
				String message="定保认领";
				JSONObject json=new JSONObject();
		    	json.put("title", "定保指定认领");
		    	json.put("description", message+"~"+id+"~"+deviceNo+"~"+state1+"~"+"0"+"~"+state2);//内容+id+deviceNo+state1+type+state2
		    	String stt=json.toString();
		    	
				BaiDuTools.sendBaidu(channelId, stt);
		}
		return;
		
		
	}

}
