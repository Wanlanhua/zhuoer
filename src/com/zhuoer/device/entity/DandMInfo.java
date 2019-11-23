package com.zhuoer.device.entity;

import java.sql.Date;

/*
 * 设备保养项目信息关联表*/

public class DandMInfo {
	private int id;				//id 主键
	private String deviceNo;	//设备编号，关联设备基本信息表中的设备编号
	private String name;		//定保项目名称
	private Date stamp;			//当前修改时间
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
	public Date getStamp() {
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
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	

}
