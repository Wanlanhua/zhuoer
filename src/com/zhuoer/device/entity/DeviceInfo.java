package com.zhuoer.device.entity;

import java.sql.Date;

/*
 *  设备基本信息表*/
public class DeviceInfo {
	private int id;            //主键
	private String customerNo; //部门编号
	private String area;		//区域
	private String no;			//设备编号
	private String name;		//设备名称
	private String type;		//设备型号
	private String address;		//设备地点
	private Date createDate;	//设备生产日期	
	private String stamp;		//修改日期
	private String mark;		//备注
	
	
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public int getId() {
		return id;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public String getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getAddress() {
		return address;
	}
	public Date getCreateDate() {
		return createDate;
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
	public void setNo(String no) {
		this.no = no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
