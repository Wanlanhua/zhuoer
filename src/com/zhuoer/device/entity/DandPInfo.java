package com.zhuoer.device.entity;

import java.sql.Date;

/*
 * 设备与部位信息关联表*/

public class DandPInfo {

	private int id;				//id 主键
	private String deviceNo;	//设备编号，关联设备基本信息表中的设备编号
	private String name;		//设备部位名称
	private String stamp;			//当前修改时间
	private String mark;		//备注
	
	
	public int getId() {
		return id;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public String getName() {
		return name;
	}
	public String getStamp() {
		return stamp;
	}
	public String getMark() {
		return mark;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
	
	
	
	
}
