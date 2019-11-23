package com.zhuoer.careDevice.entity;

import java.util.Date;

/**
 * 
 * Title:FlushTankInfo
 * Description:集中供气气体罐冲表
 * @author purple
 * date:2018年4月3日
 */

public class FlushTankInfo {
	//主键
	private int id;
	//客户编号
	private String customerNo;
	//时间
	private Date maintenanceDate;
	//气体罐
	private String gasTank;
	//冲装前表码
	private String code;
	//冲装后结束表码
	private String finishCode;
	//对应重量
	private String weight;
	//记录人
	private String recorder;
	//审核状态，0提交，1二级审核通过，2二级审核未通过
	private String state;
	//二级审核意见
	private String auditOpinion2;
	//默认获取系统当前日期，由数据库自动添加
	private Date stamp;
	//备注
	private String mark;

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public Date getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getGasTank() {
		return gasTank;
	}

	public void setGasTank(String gasTank) {
		this.gasTank = gasTank;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFinishCode() {
		return finishCode;
	}

	public void setFinishCode(String finishCode) {
		this.finishCode = finishCode;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
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

	public FlushTankInfo(int id, String customerNo, Date maintenanceDate, String gasTank, String code,
			String finishCode, String weight, String recorder, String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.gasTank = gasTank;
		this.code = code;
		this.finishCode = finishCode;
		this.weight = weight;
		this.recorder = recorder;
		this.state = state;
	}

	public FlushTankInfo(int id, String customerNo, Date maintenanceDate, String gasTank, String code,
			String finishCode, String weight, String recorder, String state, String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.gasTank = gasTank;
		this.code = code;
		this.finishCode = finishCode;
		this.weight = weight;
		this.recorder = recorder;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public FlushTankInfo() {
		super();
	}
	
}
