package com.zhuoer.device.entity;

/*
 *  设备配件信息表*/
public class DeviceFittingInfo {
	private int id;            //主键
	private String customerNo; //部门编号
	private String no;			//配件编号
	private String type1;		//类型一
	private String type2;		//类型二
	private String type;		//配件型号
	private String name;		//配件名称
	private String qty;			//计量单位
	private String manufactor;	//厂家
	private String stamp;		//修改日期
	private String mark;		//备注
	
	
	
	public int getId() {
		return id;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public String getNo() {
		return no;
	}
	public String getType1() {
		return type1;
	}
	public String getType2() {
		return type2;
	}
	public String getName() {
		return name;
	}
	public String getQty() {
		return qty;
	}
	public String getManufactor() {
		return manufactor;
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
	public void setNo(String no) {
		this.no = no;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

	
}
