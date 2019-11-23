package com.zhuoer.careDevice.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 空气压缩机运行信息
 * Title:AirCompressorInfo
 * Description:
 * @author purple
 * date:2018年4月3日
 */

/**
 * 
 * Title:AirCompressorInfo
 * Description:
 * @author purple
 * date:2018年4月10日
 */
public class AirCompressorInfo implements Serializable{
	private static final long serialVersionUID = -400448650610895610L;
	//主键
	private int id;
	//客户编号
	private String customerNo;
	//时间
	private Date maintenanceDate;
	//排气压力
	private String exhaust;
	//系统压力
	private String system;
	//冷却水压力
	private String coolingWater;
	//润滑油压力
	private String lube;
	//1#排气温度
	private String exhaust1T;
	//2#排气温度
	private String exhaust2T;
	//系统温度
	private String systemT;
	//环境温度
	private String ambientT;
	//润滑油温度
	private String lubeT;
	//冷却水温度
	private String coolingWaterT;
	//主电机前轴承温度
	private String frontBearingT;
	//主电机后轴承温度
	private String rearBearingT;
	//运转电压
	private String operatingVoltage;
	//电流指示
	private String currentIndication;
	//累计运行时间
	private String runtime;
	//累计负荷时间
	private String loadTime;
	//记录人
	private String recorder;
	//负责人
	private String person;
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

	public String getExhaust() {
		return exhaust;
	}

	public void setExhaust(String exhaust) {
		this.exhaust = exhaust;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getCoolingWater() {
		return coolingWater;
	}

	public void setCoolingWater(String coolingWater) {
		this.coolingWater = coolingWater;
	}

	public String getLube() {
		return lube;
	}

	public void setLube(String lube) {
		this.lube = lube;
	}

	public String getExhaust1T() {
		return exhaust1T;
	}

	public void setExhaust1T(String exhaust1t) {
		exhaust1T = exhaust1t;
	}

	public String getExhaust2T() {
		return exhaust2T;
	}

	public void setExhaust2T(String exhaust2t) {
		exhaust2T = exhaust2t;
	}

	public String getSystemT() {
		return systemT;
	}

	public void setSystemT(String systemT) {
		this.systemT = systemT;
	}

	public String getAmbientT() {
		return ambientT;
	}

	public void setAmbientT(String ambientT) {
		this.ambientT = ambientT;
	}

	public String getLubeT() {
		return lubeT;
	}

	public void setLubeT(String lubeT) {
		this.lubeT = lubeT;
	}

	public String getCoolingWaterT() {
		return coolingWaterT;
	}

	public void setCoolingWaterT(String coolingWaterT) {
		this.coolingWaterT = coolingWaterT;
	}

	public String getFrontBearingT() {
		return frontBearingT;
	}

	public void setFrontBearingT(String fontBearingT) {
		this.frontBearingT = fontBearingT;
	}

	public String getRearBearingT() {
		return rearBearingT;
	}

	public void setRearBearingT(String rearBearingT) {
		this.rearBearingT = rearBearingT;
	}

	public String getOperatingVoltage() {
		return operatingVoltage;
	}

	public void setOperatingVoltage(String operatingVoltage) {
		this.operatingVoltage = operatingVoltage;
	}

	public String getCurrentIndication() {
		return currentIndication;
	}

	public void setCurrentIndication(String currentIndication) {
		this.currentIndication = currentIndication;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(String loadTime) {
		this.loadTime = loadTime;
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

	public AirCompressorInfo(int id, String customerNo, Date maintenanceDate, String exhaust, String system,
			String coolingWater, String lube, String exhaust1t, String exhaust2t, String systemT, String ambientT,
			String lubeT, String coolingWaterT, String frontBearingT, String rearBearingT, String operatingVoltage,
			String currentIndication, String runtime, String loadTime, String recorder, String person, String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.exhaust = exhaust;
		this.system = system;
		this.coolingWater = coolingWater;
		this.lube = lube;
		exhaust1T = exhaust1t;
		exhaust2T = exhaust2t;
		this.systemT = systemT;
		this.ambientT = ambientT;
		this.lubeT = lubeT;
		this.coolingWaterT = coolingWaterT;
		this.frontBearingT = frontBearingT;
		this.rearBearingT = rearBearingT;
		this.operatingVoltage = operatingVoltage;
		this.currentIndication = currentIndication;
		this.runtime = runtime;
		this.loadTime = loadTime;
		this.recorder = recorder;
		this.person = person;
		this.state = state;
	}

	public AirCompressorInfo(int id, String customerNo, Date maintenanceDate, String exhaust, String system,
			String coolingWater, String lube, String exhaust1t, String exhaust2t, String systemT, String ambientT,
			String lubeT, String coolingWaterT, String frontBearingT, String rearBearingT, String operatingVoltage,
			String currentIndication, String runtime, String loadTime, String recorder, String person, String state,
			String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.exhaust = exhaust;
		this.system = system;
		this.coolingWater = coolingWater;
		this.lube = lube;
		exhaust1T = exhaust1t;
		exhaust2T = exhaust2t;
		this.systemT = systemT;
		this.ambientT = ambientT;
		this.lubeT = lubeT;
		this.coolingWaterT = coolingWaterT;
		this.frontBearingT = frontBearingT;
		this.rearBearingT = rearBearingT;
		this.operatingVoltage = operatingVoltage;
		this.currentIndication = currentIndication;
		this.runtime = runtime;
		this.loadTime = loadTime;
		this.recorder = recorder;
		this.person = person;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public AirCompressorInfo() {
		super();
	}
	
}
