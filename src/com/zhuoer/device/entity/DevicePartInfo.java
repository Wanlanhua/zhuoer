package com.zhuoer.device.entity;


/*
 *  设备部位信息表*/
public class DevicePartInfo {
	private int id;            //主键
	private String customerNo; //部门编号
	private String name;		//设备部位名称
	private String stamp;		//修改日期
	private String mark;		//备注
	
	
	public int getId() {
		return id;
	}
	public String getCustomerNo() {
		return customerNo;
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
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
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
