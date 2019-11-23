package com.zhuoer.device.entity;

import java.sql.Date;

/*
 * 设备与配件信息关联表*/

public class DandFInfo {

	private int id;				//id 主键
	private String deviceNo;	//设备编号，关联设备基本信息表中的设备编号
	private String no;			//配件编号
	private String partName;    //部位名称
	private Date stamp;			//当前修改时间
	private String mark;		//备注
	
	
	
	public int getId() {
		return id;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public String getNo() {
		return no;
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
	public void setNo(String no) {
		this.no = no;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	
	
	
}
