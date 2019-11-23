package com.zhuoer.careDevice.entity;

import java.util.Date;

/**
 * 
 * Title:ElectricityRunHInfo
 * Description:配电室运行高压信息表
 * @author purple
 * date:2018年4月3日
 */

public class ElectricityRunHInfo {
	//主键
	private int id;
	//客户编号
	private String customerNo;
	//时间
	private Date maintenanceDate;
	//配电室
	private String EDRoom;
	//室内温度
	private String indoorTemperature;
	//室内湿度
	private String indoorHumidity;
	//有功表总表值读数
	private String totalNumber;
	//有功表总表值电量
	private String totalElectricity;
	//有功表需量读数
	private String demandNumber;
	//有功表需量电量
	private String demandElectricity;
	//有功表峰值读数
	private String peakValue;
	//有功表峰值电量
	private String peakValueElectricity;
	//有功表平直读数
	private String fairValue;
	//有功表平直电量
	private String fairValueElectricity;
	//有功表谷值读数
	private String Valley;
	//有功表谷值电量
	private String ValleyElectricity;
	//无功表无工1读数
	private String varmeter1;
	//无功表无工1电量
	private String varmeter1E;
	//无功表无工2读数
	private String varmeter2;
	//无功表无工2电量
	private String varmeter2E;
	//高压表电压
	private String highVoltage;
	//高压表电流
	private String highVoltageE;
	//1#变压器温度
	private String transformer1T;
	//1#变压器低压电流
	private String transformer1E;
	//1#变压器低压电压
	private String transformer1V;
	//1#变压器低压功率因数
	private String transformer1Power;
	//2#变压器温度
	private String transformer2T;
	//2#变压器低压电流
	private String transformer2E;
	//2#变压器低压电压
	private String transformer2V;
	//2#变压器低压功率因数
	private String transformer2Power;
	//互感比
	private String ratio;
	//记录人
	private String recorder;
	//值班人
	private String watch;
	//负责人
	private String person;
	//审核状态，0提交，1二级审核通过，2二级审核未通过
	private String state;
	//二级审核意见
	private String auditOpinion2;
	//获取系统当前时间，由数据库自动添加
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

	public String getEDRoom() {
		return EDRoom;
	}

	public void setEDRoom(String eDRoom) {
		EDRoom = eDRoom;
	}

	public String getIndoorTemperature() {
		return indoorTemperature;
	}

	public void setIndoorTemperature(String indoorTemperature) {
		this.indoorTemperature = indoorTemperature;
	}

	public String getIndoorHumidity() {
		return indoorHumidity;
	}

	public void setIndoorHumidity(String indoorHumidity) {
		this.indoorHumidity = indoorHumidity;
	}

	public String getTotalNumber() {
		return totalNumber;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getTotalElectricity() {
		return totalElectricity;
	}

	public void setTotalElectricity(String totalElectricity) {
		this.totalElectricity = totalElectricity;
	}

	public String getDemandNumber() {
		return demandNumber;
	}

	public void setDemandNumber(String demandNumber) {
		this.demandNumber = demandNumber;
	}

	public String getDemandElectricity() {
		return demandElectricity;
	}

	public void setDemandElectricity(String demandElectricity) {
		this.demandElectricity = demandElectricity;
	}

	public String getPeakValue() {
		return peakValue;
	}

	public void setPeakValue(String peakValue) {
		this.peakValue = peakValue;
	}

	public String getPeakValueElectricity() {
		return peakValueElectricity;
	}

	public void setPeakValueElectricity(String peakValueElectricity) {
		this.peakValueElectricity = peakValueElectricity;
	}

	public String getFairValue() {
		return fairValue;
	}

	public void setFairValue(String fairValue) {
		this.fairValue = fairValue;
	}

	public String getFairValueElectricity() {
		return fairValueElectricity;
	}

	public void setFairValueElectricity(String fairValueElectricity) {
		this.fairValueElectricity = fairValueElectricity;
	}

	public String getValley() {
		return Valley;
	}

	public void setValley(String valley) {
		Valley = valley;
	}

	public String getValleyElectricity() {
		return ValleyElectricity;
	}

	public void setValleyElectricity(String valleyElectricity) {
		ValleyElectricity = valleyElectricity;
	}

	public String getVarmeter1() {
		return varmeter1;
	}

	public void setVarmeter1(String varmeter1) {
		this.varmeter1 = varmeter1;
	}

	public String getVarmeter1E() {
		return varmeter1E;
	}

	public void setVarmeter1E(String varmeter1e) {
		varmeter1E = varmeter1e;
	}

	public String getVarmeter2() {
		return varmeter2;
	}

	public void setVarmeter2(String varmeter2) {
		this.varmeter2 = varmeter2;
	}

	public String getVarmeter2E() {
		return varmeter2E;
	}

	public void setVarmeter2E(String varmeter2e) {
		varmeter2E = varmeter2e;
	}

	public String getHighVoltage() {
		return highVoltage;
	}

	public void setHighVoltage(String highVoltage) {
		this.highVoltage = highVoltage;
	}

	public String getHighVoltageE() {
		return highVoltageE;
	}

	public void setHighVoltageE(String highVoltageE) {
		this.highVoltageE = highVoltageE;
	}

	public String getTransformer1T() {
		return transformer1T;
	}

	public void setTransformer1T(String transformer1t) {
		transformer1T = transformer1t;
	}

	public String getTransformer1E() {
		return transformer1E;
	}

	public void setTransformer1E(String transformer1e) {
		transformer1E = transformer1e;
	}

	public String getTransformer1V() {
		return transformer1V;
	}

	public void setTransformer1V(String transformer1v) {
		transformer1V = transformer1v;
	}

	public String getTransformer1Power() {
		return transformer1Power;
	}

	public void setTransformer1Power(String transformer1Power) {
		this.transformer1Power = transformer1Power;
	}

	public String getTransformer2T() {
		return transformer2T;
	}

	public void setTransformer2T(String transformer2t) {
		transformer2T = transformer2t;
	}

	public String getTransformer2E() {
		return transformer2E;
	}

	public void setTransformer2E(String transformer2e) {
		transformer2E = transformer2e;
	}

	public String getTransformer2V() {
		return transformer2V;
	}

	public void setTransformer2V(String transformer2v) {
		transformer2V = transformer2v;
	}

	public String getTransformer2Power() {
		return transformer2Power;
	}

	public void setTransformer2Power(String transformer2Power) {
		this.transformer2Power = transformer2Power;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
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

	public ElectricityRunHInfo(int id, String customerNo, Date maintenanceDate, String eDRoom, String indoorTemperature,
			String indoorHumidity, String totalNumber, String totalElectricity, String demandNumber,
			String demandElectricity, String peakValue, String peakValueElectricity, String fairValue,
			String fairValueElectricity, String valley, String valleyElectricity, String varmeter1, String varmeter1e,
			String varmeter2, String varmeter2e, String highVoltage, String highVoltageE, String transformer1t,
			String transformer1e, String transformer1v, String transformer1Power, String transformer2t,
			String transformer2e, String transformer2v, String transformer2Power, String recorder, String watch,
			String person, String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		EDRoom = eDRoom;
		this.indoorTemperature = indoorTemperature;
		this.indoorHumidity = indoorHumidity;
		this.totalNumber = totalNumber;
		this.totalElectricity = totalElectricity;
		this.demandNumber = demandNumber;
		this.demandElectricity = demandElectricity;
		this.peakValue = peakValue;
		this.peakValueElectricity = peakValueElectricity;
		this.fairValue = fairValue;
		this.fairValueElectricity = fairValueElectricity;
		Valley = valley;
		ValleyElectricity = valleyElectricity;
		this.varmeter1 = varmeter1;
		varmeter1E = varmeter1e;
		this.varmeter2 = varmeter2;
		varmeter2E = varmeter2e;
		this.highVoltage = highVoltage;
		this.highVoltageE = highVoltageE;
		transformer1T = transformer1t;
		transformer1E = transformer1e;
		transformer1V = transformer1v;
		this.transformer1Power = transformer1Power;
		transformer2T = transformer2t;
		transformer2E = transformer2e;
		transformer2V = transformer2v;
		this.transformer2Power = transformer2Power;
		this.recorder = recorder;
		this.watch = watch;
		this.person = person;
		this.state = state;
	}

	public ElectricityRunHInfo(int id, String customerNo, Date maintenanceDate, String eDRoom, String indoorTemperature,
			String indoorHumidity, String totalNumber, String totalElectricity, String demandNumber,
			String demandElectricity, String peakValue, String peakValueElectricity, String fairValue,
			String fairValueElectricity, String valley, String valleyElectricity, String varmeter1, String varmeter1e,
			String varmeter2, String varmeter2e, String highVoltage, String highVoltageE, String transformer1t,
			String transformer1e, String transformer1v, String transformer1Power, String transformer2t,
			String transformer2e, String transformer2v, String transformer2Power, String recorder, String watch,
			String person, String state, String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		EDRoom = eDRoom;
		this.indoorTemperature = indoorTemperature;
		this.indoorHumidity = indoorHumidity;
		this.totalNumber = totalNumber;
		this.totalElectricity = totalElectricity;
		this.demandNumber = demandNumber;
		this.demandElectricity = demandElectricity;
		this.peakValue = peakValue;
		this.peakValueElectricity = peakValueElectricity;
		this.fairValue = fairValue;
		this.fairValueElectricity = fairValueElectricity;
		Valley = valley;
		ValleyElectricity = valleyElectricity;
		this.varmeter1 = varmeter1;
		varmeter1E = varmeter1e;
		this.varmeter2 = varmeter2;
		varmeter2E = varmeter2e;
		this.highVoltage = highVoltage;
		this.highVoltageE = highVoltageE;
		transformer1T = transformer1t;
		transformer1E = transformer1e;
		transformer1V = transformer1v;
		this.transformer1Power = transformer1Power;
		transformer2T = transformer2t;
		transformer2E = transformer2e;
		transformer2V = transformer2v;
		this.transformer2Power = transformer2Power;
		this.recorder = recorder;
		this.watch = watch;
		this.person = person;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public ElectricityRunHInfo() {
		super();
	}
	
}
