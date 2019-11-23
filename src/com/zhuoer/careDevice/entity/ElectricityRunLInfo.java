package com.zhuoer.careDevice.entity;

import java.util.Date;

/**
 * 
 * Title:ElectricityRunLInfo
 * Description:配电室运行低压信息
 * @author purple
 * date:2018年4月3日
 */

public class ElectricityRunLInfo {
	//主键
	private int id;
	//客户编号
	private String customerNo;
	//时间
	private Date maintenanceDate;
	//配电室
	private String EDRoom;
	//分开关1电流
	private String current1;
	//分开关1电量
	private String electricity1;
	//分开关2电流
	private String current2;
	//分开关2电量
	private String electricity2;
	//分开关3电流
	private String current3;
	//分开关3电量
	private String electricity3;
	//分开关4电流
	private String current4;
	//分开关4电量
	private String electricity4;
	//分开关5电流
	private String current5;
	//分开关5电量
	private String electricity5;
	//分开关6电流
	private String current6;
	//分开关6电量
	private String electricity6;
	//分开关7电流
	private String current7;
	//分开关7电量
	private String electricity7;
	//分开关8电流
	private String current8;
	//分开关8电量
	private String electricity8;
	//分开关9电流
	private String current9;
	//分开关9电量
	private String electricity9;
	//分开关10电流
	private String current10;
	//分开关10电量
	private String electricity10;
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

	public String getCurrent1() {
		return current1;
	}

	public void setCurrent1(String current1) {
		this.current1 = current1;
	}

	public String getElectricity1() {
		return electricity1;
	}

	public void setElectricity1(String electricity1) {
		this.electricity1 = electricity1;
	}

	public String getCurrent2() {
		return current2;
	}

	public void setCurrent2(String current2) {
		this.current2 = current2;
	}

	public String getElectricity2() {
		return electricity2;
	}

	public void setElectricity2(String electricity2) {
		this.electricity2 = electricity2;
	}

	public String getCurrent3() {
		return current3;
	}

	public void setCurrent3(String current3) {
		this.current3 = current3;
	}

	public String getElectricity3() {
		return electricity3;
	}

	public void setElectricity3(String electricity3) {
		this.electricity3 = electricity3;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getCurrent4() {
		return current4;
	}

	public void setCurrent4(String current4) {
		this.current4 = current4;
	}

	public String getElectricity4() {
		return electricity4;
	}

	public void setElectricity4(String electricity4) {
		this.electricity4 = electricity4;
	}

	public String getCurrent5() {
		return current5;
	}

	public void setCurrent5(String current5) {
		this.current5 = current5;
	}

	public String getElectricity5() {
		return electricity5;
	}

	public void setElectricity5(String electricity5) {
		this.electricity5 = electricity5;
	}

	public String getCurrent6() {
		return current6;
	}

	public void setCurrent6(String current6) {
		this.current6 = current6;
	}

	public String getElectricity6() {
		return electricity6;
	}

	public void setElectricity6(String electricity6) {
		this.electricity6 = electricity6;
	}

	public String getCurrent7() {
		return current7;
	}

	public void setCurrent7(String current7) {
		this.current7 = current7;
	}

	public String getElectricity7() {
		return electricity7;
	}

	public void setElectricity7(String electricity7) {
		this.electricity7 = electricity7;
	}

	public String getCurrent8() {
		return current8;
	}

	public void setCurrent8(String current8) {
		this.current8 = current8;
	}

	public String getElectricity8() {
		return electricity8;
	}

	public void setElectricity8(String electricity8) {
		this.electricity8 = electricity8;
	}

	public String getCurrent9() {
		return current9;
	}

	public void setCurrent9(String current9) {
		this.current9 = current9;
	}

	public String getElectricity9() {
		return electricity9;
	}

	public void setElectricity9(String electricity9) {
		this.electricity9 = electricity9;
	}

	public String getCurrent10() {
		return current10;
	}

	public void setCurrent10(String current10) {
		this.current10 = current10;
	}

	public String getElectricity10() {
		return electricity10;
	}

	public void setElectricity10(String electricity10) {
		this.electricity10 = electricity10;
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

	public ElectricityRunLInfo(int id, String customerNo, Date maintenanceDate, String eDRoom, String current1,
			String electricity1, String current2, String electricity2, String current3, String electricity3,
			String current4, String electricity4, String current5, String electricity5, String current6,
			String electricity6, String current7, String electricity7, String current8, String electricity8,
			String current9, String electricity9, String current10, String electricity10, String recorder, String watch,
			String person, String state) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		EDRoom = eDRoom;
		this.current1 = current1;
		this.electricity1 = electricity1;
		this.current2 = current2;
		this.electricity2 = electricity2;
		this.current3 = current3;
		this.electricity3 = electricity3;
		this.current4 = current4;
		this.electricity4 = electricity4;
		this.current5 = current5;
		this.electricity5 = electricity5;
		this.current6 = current6;
		this.electricity6 = electricity6;
		this.current7 = current7;
		this.electricity7 = electricity7;
		this.current8 = current8;
		this.electricity8 = electricity8;
		this.current9 = current9;
		this.electricity9 = electricity9;
		this.current10 = current10;
		this.electricity10 = electricity10;
		this.recorder = recorder;
		this.watch = watch;
		this.person = person;
		this.state = state;
	}

	public ElectricityRunLInfo(int id, String customerNo, Date maintenanceDate, String eDRoom, String current1,
			String electricity1, String current2, String electricity2, String current3, String electricity3,
			String current4, String electricity4, String current5, String electricity5, String current6,
			String electricity6, String current7, String electricity7, String current8, String electricity8,
			String current9, String electricity9, String current10, String electricity10, String recorder, String watch,
			String person, String state, String auditOpinion2) {
		super();
		this.id = id;
		this.customerNo = customerNo;
		this.maintenanceDate = maintenanceDate;
		EDRoom = eDRoom;
		this.current1 = current1;
		this.electricity1 = electricity1;
		this.current2 = current2;
		this.electricity2 = electricity2;
		this.current3 = current3;
		this.electricity3 = electricity3;
		this.current4 = current4;
		this.electricity4 = electricity4;
		this.current5 = current5;
		this.electricity5 = electricity5;
		this.current6 = current6;
		this.electricity6 = electricity6;
		this.current7 = current7;
		this.electricity7 = electricity7;
		this.current8 = current8;
		this.electricity8 = electricity8;
		this.current9 = current9;
		this.electricity9 = electricity9;
		this.current10 = current10;
		this.electricity10 = electricity10;
		this.recorder = recorder;
		this.watch = watch;
		this.person = person;
		this.state = state;
		this.auditOpinion2 = auditOpinion2;
	}

	public ElectricityRunLInfo() {
		super();
	}
	
}
