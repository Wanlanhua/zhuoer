package com.zhuoer.tableuse;

import java.util.Date;

public class QRecordInfo {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getRepair() {
		return repair;
	}

	public void setRepair(String repair) {
		this.repair = repair;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMaintenancePerson() {
		return maintenancePerson;
	}

	public void setMaintenancePerson(String maintenancePerson) {
		this.maintenancePerson = maintenancePerson;
	}

	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
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

	public String getAuditOpinion3() {
		return auditOpinion3;
	}

	public void setAuditOpinion3(String auditOpinion3) {
		this.auditOpinion3 = auditOpinion3;
	}

	public String getAuditOpinion4() {
		return auditOpinion4;
	}

	public void setAuditOpinion4(String auditOpinion4) {
		this.auditOpinion4 = auditOpinion4;
	}

	public String getAuditLevel() {
		return auditLevel;
	}

	public void setAuditLevel(String auditLevel) {
		this.auditLevel = auditLevel;
	}

	public Date getStamp() {
		return stamp;
	}

	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public QRecordInfo() {

	}

	private String content;
	private String cycle;
	private String result;
	private String maintenanceDate;
	private String repair;
	private String path;
	private String maintenancePerson;
	private String acceptor;
	private String state;
	private String auditOpinion2;
	private String auditOpinion3;
	private String auditOpinion4;
	private String auditLevel;
	private Date stamp;
	private String mark;
}
