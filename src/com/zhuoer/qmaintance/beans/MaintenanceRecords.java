package com.zhuoer.qmaintance.beans;

import java.sql.Date;


/**
 * 保养记录表
 * @author Administrator
 *
 */
public class MaintenanceRecords {
	private int id;
	private String deviceNo;
	private String content;
	private String part;
	private String oil;
	private String type;
	private String fill;
	private String method;
	private String tpye;
	private String reason;
	private String standard;
	private String security;
	private String tool;
	private String pnumber;
	private String mnumber;
	private String cycle;
	private Date maintenanceDate;
	private String maintenancePerson;
	private String state1;
	private String date1;
	private String state2;
	private String person2;
	private String date2;
	private String state;
	private String auditOpinion1;
	private String auditOpinion2;
	private String auditOpinion4;
	private String stamp;
	private String mark;
	private String time;
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
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
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getOil() {
		return oil;
	}
	public void setOil(String oil) {
		this.oil = oil;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFill() {
		return fill;
	}
	public void setFill(String fill) {
		this.fill = fill;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getTpye() {
		return tpye;
	}
	public void setTpye(String tpye) {
		this.tpye = tpye;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getTool() {
		return tool;
	}
	public void setTool(String tool) {
		this.tool = tool;
	}
	public String getPnumber() {
		return pnumber;
	}
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	public String getMnumber() {
		return mnumber;
	}
	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	
	public String getMaintenancePerson() {
		return maintenancePerson;
	}
	public void setMaintenancePerson(String maintenancePerson) {
		this.maintenancePerson = maintenancePerson;
	}
	public String getState1() {
		return state1;
	}
	public void setState1(String state1) {
		this.state1 = state1;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getState2() {
		return state2;
	}
	public void setState2(String state2) {
		this.state2 = state2;
	}
	public String getPerson2() {
		return person2;
	}
	public void setPerson2(String person2) {
		this.person2 = person2;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAuditOpinion1() {
		return auditOpinion1;
	}
	public void setAuditOpinion1(String auditOpinion1) {
		this.auditOpinion1 = auditOpinion1;
	}
	public String getAuditOpinion2() {
		return auditOpinion2;
	}
	public void setAuditOpinion2(String auditOpinion2) {
		this.auditOpinion2 = auditOpinion2;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Date getMaintenanceDate() {
		return maintenanceDate;
		
	}
	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "MaintenanceRecords [id=" + id + ", deviceNo=" + deviceNo + ", content=" + content + ", part=" + part
				+ ", oil=" + oil + ", type=" + type + ", fill=" + fill + ", method=" + method + ", tpye=" + tpye
				+ ", reason=" + reason + ", standard=" + standard + ", security=" + security + ", tool=" + tool
				+ ", pnumber=" + pnumber + ", mnumber=" + mnumber + ", cycle=" + cycle + ", maintenanceDate="
				+ maintenanceDate + ", maintenancePerson=" + maintenancePerson + ", state1=" + state1 + ", date1="
				+ date1 + ", state2=" + state2 + ", person2=" + person2 + ", date2=" + date2 + ", state=" + state
				+ ", auditOpinion1=" + auditOpinion1 + ", auditOpinion2=" + auditOpinion2 + ", stamp=" + stamp
				+ ", mark=" + mark + "]";
	}
	public String getAuditOpinion4() {
		return auditOpinion4;
	}
	public void setAuditOpinion4(String auditOpinion4) {
		this.auditOpinion4 = auditOpinion4;
	}

}
