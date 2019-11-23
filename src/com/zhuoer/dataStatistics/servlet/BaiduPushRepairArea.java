package com.zhuoer.dataStatistics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.qmaintance.utils.RepairInfoTools;

public class BaiduPushRepairArea extends HttpServlet{
	public BaiduPushRepairArea() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();	
        String sendMsg="����ʧ�ܣ�";
        //�û����
		String no="";
		no=request.getParameter("no").trim();
		
		String sqlAll="";
		//���û���ȡ��Ҫ�������͵ı�����Ϣ
		if (no.equals("admin")) {
			sqlAll="SELECT * FROM repairinfo  WHERE state2!='1'";
			
		}else
		{
			sqlAll="SELECT r.* FROM repairinfo r,deviceinfo d,employeeinfo e WHERE r.state2!='1' AND r.deviceNo=d.no  AND d.customerNo=e.department AND e.no='"+no+"'";
		}
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlAll);
		//ѭ��ÿ�����ͱ�����Ϣ�����������͵�Ա��
		for (int i = 0; i < listAll.size(); i++) {
			//��ȡ�豸���ڵ���������
			String sqlGetArea="SELECT a.name FROM areainfo a,deviceinfo d WHERE d.area=a.no and d.no='"+listAll.get(i).get("deviceNo").toString()+"'";
			List<Map<String, Object>> listAreaName=RepairInfoTools.executeQuary(sqlGetArea);
			if (listAreaName.size()>0) {
				//���������ƻ�ȡ����������Ҫ���͵�Ա��ChannelId
				String sqlGetChannelId="SELECT p.* FROM pushanduserinfo p,employeeinfo e  WHERE p.name=e.no  and e.area LIKE'%"+listAreaName.get(0).get("name").toString()+"%' ";
				List<Map<String, Object>> listM=RepairInfoTools.executeQuary(sqlGetChannelId);
				for (int j = 0; j < listM.size(); j++) {
					//��װ������Ϣ����������
					String msg="��������-"+listAll.get(i).get("id")+"-"+listAll.get(i).get("deviceNo");
					String channelId=listM.get(j).get("channelId").toString();
					
					sendMsg="���ͳɹ���";
				}
			}
			
			
		}
		
        out.write(sendMsg);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
			doGet(request, response);

	}
}
