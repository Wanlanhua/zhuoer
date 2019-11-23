package com.zhuoer.careDevice.entity;

import java.util.Date;

/**
 * 
 * Title:ElectricityInfo
 * Description:配电室看护信息
 * @author purple
 * date:2018年4月3日
 */

public class ElectricityInfo {
	//主键
	private int id;
	//客户编号
	private String customerNo;
	//高压配电，该字段包括：时间，电压，电流，变压器温度，油位，卫生。各个数据之间使用逗号相连
	private String highVoltage;
	//低压配电，该该字段包含：电压，电流。各个数据使用逗号相连
	private String bottomPiezoelectricity;
	//值班人
	private String watch;
	//负责人
	private String person;
	//审核状态，0提交，1二级审核通过，2二级审核未通过
	private String state;
	//默认获取系统当前时间，由数据库自动添加
	private String auditOpinion2;
	//备注
	private Date stamp;
	
	private String mark;

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getHighVoltage() {
		return highVoltage;
	}

	public void setHighVoltage(String highVoltage) {
		this.highVoltage = highVoltage;
	}

	public String getBottomPiezoelectricity() {
		return bottomPiezoelectricity;
	}

	public void setBottomPiezoelectricity(String bottomPiezoelectricity) {
		this.bottomPiezoelectricity = bottomPiezoelectricity;
	}

	public String getWatch() {
		return watch;
	}

	public void setWatch(String watch) {
		this.watch = watch;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAuditOpinion2() {
		return auditOpinion2;
	}

	public void setAuditOpinion2(String auditOpinion2) {
		this.auditOpinion2 = auditOpinion2;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getId() {
		return id;
	}

	public Date getStamp() {
		return stamp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

	public ElectricityInfo(int id, String customerNo, String highVoltage, String bottomPiezoelectricity, String watch,
			String person, String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.highVoltage = highVoltage;
		this.bottomPiezoelectricity = bottomPiezoelectricity;
		this.watch = watch;
		this.person = person;
		this.state = state;
	}

	public ElectricityInfo(int id, String customerNo, String highVoltage, String bottomPiezoelectricity, String watch,
			String person, String state, String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.highVoltage = highVoltage;
		this.bottomPiezoelectricity = bottomPiezoelectricity;
		this.watch = watch;
		this.person = person;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public ElectricityInfo() {
		super();
	}

}
