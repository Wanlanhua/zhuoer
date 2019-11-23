package com.zhuoer.device.entity;


/*
 *  保养项目信息表*/
public class MaintenanceInfo {
	private int id;            //主键
	private String customerNo; //部门编号
	private String content;		//定保项目
	private String stamp;		//修改日期
	private String mark;		//备注
	
	public int getId() {
		return id;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public String getContent() {
		return content;
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
	public void setContent(String content) {
		this.content = content;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
}
