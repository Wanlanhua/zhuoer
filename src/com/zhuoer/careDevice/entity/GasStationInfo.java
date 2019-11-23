package com.zhuoer.careDevice.entity;

import java.util.Date;

/**
 * 
 * Title:GasStationInfo
 * Description:供气站看护信息表
 * @author purple
 * date:2018年4月3日
 */
public class GasStationInfo {
	//主键
	private int id;
	//客户编号
	private String customerNo;
	//时间
	private Date maintenanceDate;
	//氧气压力
	private String oxygen;
	//乙炔压力
	private String acetylene;
	//氩气压力
	private String argon;
	//阀门关闭情况
	private String valve;
	//站房温度
	private String station;
	//供气管道
	private String conduit;
	//异常情况说明
	private String content;
	//巡检人
	private String maintenancePerson;
	//审核状态，0提交，1二级审核通过，2二级审核未通过
	private String state;
	//二级审核意见
	private String auditOpinion2;
	//默认获取系统当前时间，数据库自动添加
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
	
	public String getOxygen() {
		return oxygen;
	}
	
	public void setOxygen(String oxygen) {
		this.oxygen = oxygen;
	}
	
	public String getAcetylene() {
		return acetylene;
	}
	
	public void setAcetylene(String acetylene) {
		this.acetylene = acetylene;
	}
	
	public String getArgon() {
		return argon;
	}
	
	public void setArgon(String argon) {
		this.argon = argon;
	}
	
	public String getValve() {
		return valve;
	}
	
	public void setValve(String valve) {
		this.valve = valve;
	}
	
	public String getStation() {
		return station;
	}
	
	public void setStation(String station) {
		this.station = station;
	}
	
	public String getConduit() {
		return conduit;
	}
	
	public void setConduit(String conduit) {
		this.conduit = conduit;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMaintenancePerson() {
		return maintenancePerson;
	}
	
	public void setMaintenancePerson(String maintenancePerson) {
		this.maintenancePerson = maintenancePerson;
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

	public GasStationInfo(int id, String customerNo, Date maintenanceDate, String oxygen, String acetylene,
			String argon, String valve, String station, String conduit, String content, String maintenancePerson,
			String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.oxygen = oxygen;
		this.acetylene = acetylene;
		this.argon = argon;
		this.valve = valve;
		this.station = station;
		this.conduit = conduit;
		this.content = content;
		this.maintenancePerson = maintenancePerson;
		this.state = state;
	}

	public GasStationInfo(int id, String customerNo, Date maintenanceDate, String oxygen, String acetylene,
			String argon, String valve, String station, String conduit, String content, String maintenancePerson,
			String state, String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.oxygen = oxygen;
		this.acetylene = acetylene;
		this.argon = argon;
		this.valve = valve;
		this.station = station;
		this.conduit = conduit;
		this.content = content;
		this.maintenancePerson = maintenancePerson;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public GasStationInfo() {
		super();
	}
	
}
