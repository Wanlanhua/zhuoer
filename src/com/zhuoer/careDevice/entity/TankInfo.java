package com.zhuoer.careDevice.entity;

import java.util.Date;

/**
 * 
 * Title:TankInfo
 * Description:集中供气气体罐体信息
 * @author purple
 * date:2018年4月3日
 */

public class TankInfo {

	private int id;
	
	private String customerNo;
	
	private Date maintenanceDate;
	
	private String oxygenCode1;
	
	private String oxygenWeight1;
	
	private String oxygenCode2;
	
	private String oxygenWeight2;
	
	private String propaneCode1;
	
	private String propaneWeight1;
	
	private String propaneCode2;
	
	private String propaneWeight2;
	
	private String dioxideCode1;
	
	private String dioxideWeight1;
	
	private String dioxideCode2;
	
	private String dioxideWeight2;
	
	private String recorder;
	
	private String person;
	
	private String state;
	
	private String auditOpinion2;
	
	private Date stamp;
	
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

	public String getOxygenCode1() {
		return oxygenCode1;
	}

	public void setOxygenCode1(String oxygenCode1) {
		this.oxygenCode1 = oxygenCode1;
	}

	public String getOxygenWeight1() {
		return oxygenWeight1;
	}

	public void setOxygenWeight1(String oxygenWeight1) {
		this.oxygenWeight1 = oxygenWeight1;
	}

	public String getOxygenCode2() {
		return oxygenCode2;
	}

	public void setOxygenCode2(String oxygenCode2) {
		this.oxygenCode2 = oxygenCode2;
	}

	public String getOxygenWeight2() {
		return oxygenWeight2;
	}

	public void setOxygenWeight2(String oxygenWeight2) {
		this.oxygenWeight2 = oxygenWeight2;
	}

	public String getPropaneCode1() {
		return propaneCode1;
	}

	public void setPropaneCode1(String propaneCode1) {
		this.propaneCode1 = propaneCode1;
	}

	public String getPropaneWeight1() {
		return propaneWeight1;
	}

	public void setPropaneWeight1(String propaneWeight1) {
		this.propaneWeight1 = propaneWeight1;
	}

	public String getPropaneCode2() {
		return propaneCode2;
	}

	public void setPropaneCode2(String propaneCode2) {
		this.propaneCode2 = propaneCode2;
	}

	public String getPropaneWeight2() {
		return propaneWeight2;
	}

	public void setPropaneWeight2(String propaneWeight2) {
		this.propaneWeight2 = propaneWeight2;
	}

	public String getDioxideCode1() {
		return dioxideCode1;
	}

	public void setDioxideCode1(String dioxideCode1) {
		this.dioxideCode1 = dioxideCode1;
	}

	public String getDioxideWeight1() {
		return dioxideWeight1;
	}

	public void setDioxideWeight1(String dioxideWeight1) {
		this.dioxideWeight1 = dioxideWeight1;
	}

	public String getDioxideCode2() {
		return dioxideCode2;
	}

	public void setDioxideCode2(String dioxideCode2) {
		this.dioxideCode2 = dioxideCode2;
	}

	public String getDioxideWeight2() {
		return dioxideWeight2;
	}

	public void setDioxideWeight2(String dioxideWeight2) {
		this.dioxideWeight2 = dioxideWeight2;
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

	public TankInfo(int id, String customerNo, Date maintenanceDate, String oxygenCode1, String oxygenWeight1,
			String oxygenCode2, String oxygenWeight2, String propaneCode1, String propaneWeight1, String propaneCode2,
			String propaneWeight2, String dioxideCode1, String dioxideWeight1, String dioxideCode2,
			String dioxideWeight2, String recorder, String person, String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.oxygenCode1 = oxygenCode1;
		this.oxygenWeight1 = oxygenWeight1;
		this.oxygenCode2 = oxygenCode2;
		this.oxygenWeight2 = oxygenWeight2;
		this.propaneCode1 = propaneCode1;
		this.propaneWeight1 = propaneWeight1;
		this.propaneCode2 = propaneCode2;
		this.propaneWeight2 = propaneWeight2;
		this.dioxideCode1 = dioxideCode1;
		this.dioxideWeight1 = dioxideWeight1;
		this.dioxideCode2 = dioxideCode2;
		this.dioxideWeight2 = dioxideWeight2;
		this.recorder = recorder;
		this.person = person;
		this.state = state;
	}

	public TankInfo(int id, String customerNo, Date maintenanceDate, String oxygenCode1, String oxygenWeight1,
			String oxygenCode2, String oxygenWeight2, String propaneCode1, String propaneWeight1, String propaneCode2,
			String propaneWeight2, String dioxideCode1, String dioxideWeight1, String dioxideCode2,
			String dioxideWeight2, String recorder, String person, String state, String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		this.oxygenCode1 = oxygenCode1;
		this.oxygenWeight1 = oxygenWeight1;
		this.oxygenCode2 = oxygenCode2;
		this.oxygenWeight2 = oxygenWeight2;
		this.propaneCode1 = propaneCode1;
		this.propaneWeight1 = propaneWeight1;
		this.propaneCode2 = propaneCode2;
		this.propaneWeight2 = propaneWeight2;
		this.dioxideCode1 = dioxideCode1;
		this.dioxideWeight1 = dioxideWeight1;
		this.dioxideCode2 = dioxideCode2;
		this.dioxideWeight2 = dioxideWeight2;
		this.recorder = recorder;
		this.person = person;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public TankInfo() {
		super();
	}
	
}
