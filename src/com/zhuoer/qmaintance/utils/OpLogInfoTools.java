package com.zhuoer.qmaintance.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuoer.qmaintance.beans.OpLogInfo;

/**
 * 日志表工具类
 * @author Administrator
 *
 */
public class OpLogInfoTools {
	public static void main(String[] args) {
		queryOpLogInfo();
	}
	/**
	 * 插入日志信息,时间为插入时的系统时间
	 * @return
	 */
	public static String insertOpLogInfo(String no,String content,String mark)
	{
		String result="";
		//接收数据封装对象
		OpLogInfo ol=new OpLogInfo();
		ol.setNo(no);
		ol.setContent(content);
		//转换日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=new Date();
		String stamp;
		
		try {
			stamp = sdf.format(d);//获取到当前日期的字符串
			ol.setStamp(sdf.parse(stamp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ol.setMark(mark);
		//开始执行数据库操作
		String sql="insert into OpLogInfo(no,content,stamp,mark) values(?,?,?,?)";
		int i=RepairInfoTools.executeUpdate(sql, ol.getNo(),ol.getContent(),ol.getStamp(),ol.getMark());
		if(i>0)
		{
			result="true";
		}
		else
		{
			result="false";
		}
		return result;
	}
	public static String insertOpLogInfo(String no,String content)
	{
		String result="";
		//接收数据封装对象
		OpLogInfo ol=new OpLogInfo();
		ol.setNo(no);
		ol.setContent(content);
		//转换日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=new Date();
		String stamp;
		try {
			stamp = sdf.format(d);//获取到当前日期的字符串
			ol.setStamp(sdf.parse(stamp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//开始执行数据库操作
		String sql="insert into OpLogInfo(no,content,stamp) values(?,?,?)";
		int i=RepairInfoTools.executeUpdate(sql, ol.getNo(),ol.getContent(),ol.getStamp());
		if(i>0)
		{
			result="true";
		}
		else
		{
			result="false";
		}
		return result;
	}
	/**
	 * 查询日志信息,返回部门编号,员工编号,员工姓名,操作描述,操作时间
	 * @return
	 */
	public static String queryOpLogInfo()
	{
		String no="";
		String department="";
		String starttime="";
		String endtime="";
		
		String sql="select e.no,e.name,e.department,o.content,o.stamp from OpLogInfo o,EmployeeInfo e where o.no!='admin' and o.no=e.no";
		if((no!=null && !no.equals("")))
		{
			sql+=" and o.no='"+no+"'";
		}
		if(starttime!=null && !starttime.equals(""))
		{
			sql+=" and o.stamp>='"+starttime+"'";
		}
		if(endtime!=null && !endtime.equals(""))
		{
			sql+=" and o.stamp<='"+endtime+"'";
		}
		if(department!=null && !department.equals(""))
		{
			sql+=" and e.department='"+department+"'";
		}
		
		List li=RepairInfoTools.executeQuary(sql);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json=gson.toJson(li);
		System.out.println(json);
		return json;
		
	}

}
