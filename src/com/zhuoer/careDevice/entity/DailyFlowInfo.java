package com.zhuoer.careDevice.entity;

import java.util.Date;

/**
 * 
 * Title:DailyFlowInfo
 * Description:集中供气日流量统计表
 * @author purple
 * date:2018年4月3日
 */

public class DailyFlowInfo {
	//主键
	private int id;
	//客户编号
	private String customerNo;
	//地点
	private String address;
	//液氧气读数
	private String oxygenReading;
	//液氧气用量
	private String oxygenConsumption;
	//液氧气压力值
	private String oxygenPressure;
	//丙烷气读数
	private String propaneReading;
	//丙烷气用量
	private String propaneConsumption;
	//丙烷气压力值
	private String propanePressure;
	//二氧化碳读数
	private String dioxideReading;
	//二氧化碳用量
	private String dioxideConsumption;
	//二氧化碳压力值
	private String dioxidePressure;
	//时间
	private Date maintenanceDate;
	//记录人
	private String recorder;
	//点检人
	private String person;
	//审核状态，0提交，1二级审核通过，2二级审核未通过
	private String state;
	//二级审核意见
	private String auditOpinion2;
	//默认获取系统当前时间，由数据库自动添加
	private Date stamp;
	//备注
	private String mark;

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOxygenReading() {
		return oxygenReading;
	}

	public void setOxygenReading(String oxygenReading) {
		this.oxygenReading = oxygenReading;
	}

	public String getOxygenConsumption() {
		return oxygenConsumption;
	}

	public void setOxygenConsumption(String oxygenConsumption) {
		this.oxygenConsumption = oxygenConsumption;
	}

	public String getOxygenPressure() {
		return oxygenPressure;
	}

	public void setOxygenPressure(String oxygenPressure) {
		this.oxygenPressure = oxygenPressure;
	}

	public String getPropaneReading() {
		return propaneReading;
	}

	public void setPropaneReading(String propaneReading) {
		this.propaneReading = propaneReading;
	}

	public String getPropaneConsumption() {
		return propaneConsumption;
	}

	public void setPropaneConsumption(String propaneConsumption) {
		this.propaneConsumption = propaneConsumption;
	}

	public String getPropanePressure() {
		return propanePressure;
	}

	public void setPropanePressure(String propanePressure) {
		this.propanePressure = propanePressure;
	}

	public String getDioxideReading() {
		return dioxideReading;
	}

	public void setDioxideReading(String dioxideReading) {
		this.dioxideReading = dioxideReading;
	}

	public String getDioxideConsumption() {
		return dioxideConsumption;
	}

	public void setDioxideConsumption(String dioxideConsumption) {
		this.dioxideConsumption = dioxideConsumption;
	}

	public String getDioxidePressure() {
		return dioxidePressure;
	}

	public void setDioxidePressure(String dioxidePressure) {
		this.dioxidePressure = dioxidePressure;
	}

	public Date getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
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

	public DailyFlowInfo(int id, String customerNo, String address, String oxygenReading, String oxygenConsumption,
			String oxygenPressure, String propaneReading, String propaneConsumption, String propanePressure,
			String dioxideReading, String dioxideConsumption, String dioxidePressure, Date maintenanceDate,
			String recorder, String person, String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.address = address;
		this.oxygenReading = oxygenReading;
		this.oxygenConsumption = oxygenConsumption;
		this.oxygenPressure = oxygenPressure;
		this.propaneReading = propaneReading;
		this.propaneConsumption = propaneConsumption;
		this.propanePressure = propanePressure;
		this.dioxideReading = dioxideReading;
		this.dioxideConsumption = dioxideConsumption;
		this.dioxidePressure = dioxidePressure;
		this.maintenanceDate = maintenanceDate;
		this.recorder = recorder;
		this.person = person;
		this.state = state;
	}

	public DailyFlowInfo(int id, String customerNo, String address, String oxygenReading, String oxygenConsumption,
			String oxygenPressure, String propaneReading, String propaneConsumption, String propanePressure,
			String dioxideReading, String dioxideConsumption, String dioxidePressure, Date maintenanceDate,
			String recorder, String person, String state, String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.address = address;
		this.oxygenReading = oxygenReading;
		this.oxygenConsumption = oxygenConsumption;
		this.oxygenPressure = oxygenPressure;
		this.propaneReading = propaneReading;
		this.propaneConsumption = propaneConsumption;
		this.propanePressure = propanePressure;
		this.dioxideReading = dioxideReading;
		this.dioxideConsumption = dioxideConsumption;
		this.dioxidePressure = dioxidePressure;
		this.maintenanceDate = maintenanceDate;
		this.recorder = recorder;
		this.person = person;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public DailyFlowInfo() {
		super();
	}
	
}
