package com.zhuoer.statistical.dto;

public class Statistical implements Cloneable{

	private String deviceNo;
	
	private String repairDate;
	
	private String acceptance;
	
	private String startTime;
	
	private String endTime;
	
	private String acceptancePerson;
	
	private String task;
	
	private String implementationDistinction;
	
	private String finish;
	
	private String maintenanceTime;
	
	private String downTime;
	
	private String repairmanNumber;
	
	private String part;
	
	private String failureCause;
	
	private String disposalMethod;
	
	private String repairman;
	
	private String parts;
	
	private String mark;
	
	private String partsName;
	
	private String partsNumber;

	public String getPartsName() {
		return partsName;
	}

	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}

	public String getPartsNumber() {
		return partsNumber;
	}

	public void setPartsNumber(String partsNumber) {
		this.partsNumber = partsNumber;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}

	public String getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAcceptancePerson() {
		return acceptancePerson;
	}

	public void setAcceptancePerson(String acceptancePerson) {
		this.acceptancePerson = acceptancePerson;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getImplementationDistinction() {
		return implementationDistinction;
	}

	public void setImplementationDistinction(String implementationDistinction) {
		this.implementationDistinction = implementationDistinction;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getMaintenanceTime() {
		return maintenanceTime;
	}

	public void setMaintenanceTime(String maintenanceTime) {
		this.maintenanceTime = maintenanceTime;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public String getRepairmanNumber() {
		return repairmanNumber;
	}

	public void setRepairmanNumber(String repairmanNumber) {
		this.repairmanNumber = repairmanNumber;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getFailureCause() {
		return failureCause;
	}

	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}

	public String getDisposalMethod() {
		return disposalMethod;
	}

	public void setDisposalMethod(String disposalMethod) {
		this.disposalMethod = disposalMethod;
	}

	public String getRepairman() {
		return repairman;
	}

	public void setRepairman(String repairman) {
		this.repairman = repairman;
	}

	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Statistical [deviceNo=" + deviceNo + ", repairDate=" + repairDate + ", acceptance=" + acceptance
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", acceptancePerson=" + acceptancePerson
				+ ", task=" + task + ", implementationDistinction=" + implementationDistinction + ", finish=" + finish
				+ ", maintenanceTime=" + maintenanceTime + ", downTime=" + downTime + ", repairmanNumber="
				+ repairmanNumber + ", part=" + part + ", failureCause=" + failureCause + ", disposalMethod="
				+ disposalMethod + ", repairman=" + repairman + ", parts=" + parts + ", mark=" + mark + ", partsName="
				+ partsName + ", partsNumber=" + partsNumber + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
