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

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.google.gson.Gson;
import com.zhuoer.qmaintance.utils.BaiDuTools;
import com.zhuoer.qmaintance.utils.RepairInfoTools;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class QBaiduSend
 */
@WebServlet("/QBaiduSend")
public class QBaiduSend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");//解决跨域
		
		String names=req.getParameter("name");
		String[] split = names.split(",");
		String id=req.getParameter("id");
		for(int i=0;i<split.length;i++)
		{
			String name=split[i];
			String channelId="";
			String sql2="select * from RepairInfo where id=? ";//未领取
			//String sql4="select * from DeviceInfo where no=?";
			//设备基本信息表
			//List<Map<String, String>> li4=RepairInfoTools.executeQuary(sql4, deviceNo);
			//报修表内容
			List<Map<String, Object>> li2=RepairInfoTools.executeQuary(sql2, id);
			//System.out.println(""+li2.get(0).get("id"));
			String deviceNo="";
			if(li2.size()>0)
			{
				deviceNo=li2.get(0).get("deviceNo").toString();
			}
			else
			{
				continue;//无此记录
			}
			
			
			//int id=Integer.parseInt(li2.get(0).get("id"));
			String state1=li2.get(0).get("state1").toString();
			String state2=li2.get(0).get("state2").toString();
//			if(li4.size()>0)
//			{
//				li2.get(0).putAll(li4.get(0));//两个表的数据封装为json合在一起
//			}
//			else
//			{
//				return;
//			}
			
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
				
			JSONObject json=new JSONObject();
	    	json.put("title", "报修认领");
	    	json.put("description", "报修认领~"+id+"~"+deviceNo+"~"+state1+"~"+"1"+"~"+state2);//id+deviceNo+state1+type(报修)+state2
	    	String stt=json.toString();
	    	BaiDuTools.sendBaidu(channelId, stt);
		}
		return;
		
		
		
		
		
		
		
	}

}
