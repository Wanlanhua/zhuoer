package com.zhuoer.careDevice.entity;

import java.util.Date;

/**
 * 
 * Title:AirPressureStationInfo
 * Description:空压站看护信息
 * @author purple
 * date:2018年4月3日
 */
public class AirPressureStationInfo {
	//主键
	private int id;
	//客户编号
	private String customerNo;
	//时间
	private Date maintenanceDate;
	//空压机运行情况
	private String situation;
	//储气罐压力
	private String pressure;
	//润滑油温度
	private String lube;
	//冷却水温度
	private String coolingWater;
	//站房温度
	private String station;
	//空压机油位
	private String oiLevel;
	//异常情况说明
	private String content;
	//巡检人
	private String maintenancePerson;
	//审核状态，0提交，1二级审核通过，2二级审核未通过
	private String state;
	//二级审核意见
	private String auditOpinion2;
	//默认获取系统当前日期，数据库自动添加
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
	
	public String getSituation() {
		return situation;
	}
	
	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	public String getPressure() {
		return pressure;
	}
	
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	
	public String getLube() {
		return lube;
	}
	
	public void setLube(String lube) {
		this.lube = lube;
	}
	
	public String getCoolingWater() {
		return coolingWater;
	}
	
	public void setCoolingWater(String coolingWater) {
		this.coolingWater = coolingWater;
	}
	
	public String getStation() {
		return station;
	}
	
	public void setStation(String station) {
		this.station = station;
	}
	
	public String getOiLevel() {
		return oiLevel;
	}

	public void setOiLevel(String oiLevel) {
		this.oiLevel = oiLevel;
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

	public AirPressureStationInfo(int id, String customerNo, Date maintenanceDate, String situation, String pressure,
			String lube, String coolingWater, String station, String oiLevel, String content, String maintenancePerson,
			String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.situation = situation;
		this.pressure = pressure;
		this.lube = lube;
		this.coolingWater = coolingWater;
		this.station = station;
		this.oiLevel = oiLevel;
		this.content = content;
		this.maintenancePerson = maintenancePerson;
		this.state = state;
	}

	public AirPressureStationInfo(int id, String customerNo, Date maintenanceDate, String situation, String pressure,
			String lube, String coolingWater, String station, String oiLevel, String content, String maintenancePerson,
			String state, String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.situation = situation;
		this.pressure = pressure;
		this.lube = lube;
		this.coolingWater = coolingWater;
		this.station = station;
		this.oiLevel = oiLevel;
		this.content = content;
		this.maintenancePerson = maintenancePerson;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public AirPressureStationInfo() {
		super();
	}
	
}
