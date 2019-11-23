package com.zhuoer.careDevice.dao;

import java.sql.PreparedStatement;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.careDevice.entity.*;
import com.zhuoer.careDevice.util.TimeUtilToSql;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class UpdataCareDeviceInformation {

	private PreparedStatement ps;
	
	private boolean flag;
	
	public boolean updateAirCompressorInfo(AirCompressorInfo aci, String no) {
		flag = true;
		String sql = "update aircompressorinfo set customerNo=?,maintenanceDate=?,exhaust=?,system=?,coolingWater=?,"
				+ "lube=?,exhaust1T=?,exhaust2T=?,systemT=?,ambientT=?,lubeT=?,coolingWaterT=?,frontBearingT=?,rearBearingT=?,"
				+ "operatingVoltage=?,currentIndication=?,runtime=?,loadTime=?,recorder=?,person=?,mark=? where id=?";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, aci.getCustomerNo());
			ps.setTimestamp(2, TimeUtilToSql.getTimeStamp(aci.getMaintenanceDate()));
			ps.setString(3, aci.getExhaust());
			ps.setString(4, aci.getSystem());
			ps.setString(5, aci.getCoolingWater());
			ps.setString(6, aci.getLube());
			ps.setString(7, aci.getExhaust1T());
			ps.setString(8, aci.getExhaust2T());
			ps.setString(9, aci.getSystemT());
			ps.setString(10, aci.getAmbientT());
			ps.setString(11, aci.getLubeT());
			ps.setString(12, aci.getCoolingWaterT());
			ps.setString(13, aci.getFrontBearingT());
			ps.setString(14, aci.getRearBearingT());
			ps.setString(15, aci.getOperatingVoltage());
			ps.setString(16, aci.getCurrentIndication());
			ps.setString(17, aci.getRuntime());
			ps.setString(18, aci.getLoadTime());
			ps.setString(19, aci.getRecorder());
			ps.setString(20, aci.getPerson());
			ps.setString(21, aci.getMark());
			ps.setInt(22,aci.getId());
			ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>空气压缩机运行信息=>更新操作:"+aci.getId());
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateAirPressureStationInfo(AirPressureStationInfo apsi, String no) {
		
		flag = true;
		String sql = "update airpressurestationinfo set customerNo=?,maintenanceDate=?,situation=?,pressure=?,lube=?,coolingWater=?,station=?,oiLevel=?,"
				+ "content=?,maintenancePerson=?,mark=? where id=?";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, apsi.getCustomerNo());
			ps.setTimestamp(2, TimeUtilToSql.getTimeStamp(apsi.getMaintenanceDate()));
			ps.setString(3, apsi.getSituation());
			ps.setString(4, apsi.getPressure());
			ps.setString(5, apsi.getLube());
			ps.setString(6, apsi.getCoolingWater());
			ps.setString(7, apsi.getStation());
			ps.setString(8, apsi.getOiLevel());
			ps.setString(9, apsi.getContent());
			ps.setString(10, apsi.getMaintenancePerson());
			ps.setString(11, apsi.getMark());
			ps.setInt(12, apsi.getId());
			ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>空压站看护信息=>更新操作:"+apsi.getId());
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	
	public boolean updateDailyFlowInfo(DailyFlowInfo dfi, String no) {
		
		flag = true;
		String sql = "update dailyflowinfo set customerNo=?,address=?,oxygenReading=?,oxygenConsumption=?,oxygenPressure=?,propaneReading=?,"
				+ "propaneConsumption=?,propanePressure=?,dioxideReading=?,dioxideConsumption=?,dioxidePressure=?,maintenanceDate=?,"
				+ "recorder=?,person=?,mark=? where id=?";
		
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, dfi.getCustomerNo());
			ps.setString(2, dfi.getAddress());
			ps.setString(3, dfi.getOxygenReading());
			ps.setString(4, dfi.getOxygenConsumption());
			ps.setString(5, dfi.getOxygenPressure());
			ps.setString(6, dfi.getPropaneReading());
			ps.setString(7, dfi.getPropaneConsumption());
			ps.setString(8, dfi.getPropanePressure());
			ps.setString(9, dfi.getDioxideReading());
			ps.setString(10, dfi.getDioxideConsumption());
			ps.setString(11, dfi.getDioxidePressure());
			ps.setTimestamp(12, TimeUtilToSql.getTimeStamp(dfi.getMaintenanceDate()));
			ps.setString(13, dfi.getRecorder());
			ps.setString(14, dfi.getPerson());
			ps.setString(15, dfi.getMark());
			ps.setInt(16, dfi.getId());
			ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气日流量统计=>更新操作:"+dfi.getId());
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public boolean updateElectricityInfo(ElectricityInfo ei, String no) {
		
		flag = true;
		String sql = "update electricityinfo set customerNo=?,highVoltage=?,bottomPiezoelectricity=?,watch=?,person=?,mark=? where id=?";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, ei.getCustomerNo());
			ps.setString(2, ei.getHighVoltage());
			ps.setString(3, ei.getBottomPiezoelectricity());
			ps.setString(4, ei.getWatch());
			ps.setString(5, ei.getPerson());
			ps.setString(6, ei.getMark());
			ps.setInt(7, ei.getId());
			flag = ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室看护信息=>更新操作:"+ei.getId());
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public boolean updateElectricityRunHInfo(ElectricityRunHInfo erhi, String no) {
		
		flag = true;
		String sql = "update electricityrunhinfo set customerNo=?,maintenanceDate=?,EDRoom=?,indoorTemperature=?,indoorHumidity=?,"
				+ "totalNumber=?,totalElectricity=?,demandNumber=?,demandElectricity=?,peakValue=?,peakValueElectricity=?,fairValue=?,"
				+ "fairValueElectricity=?,Valley=?,ValleyElectricity=?,varmeter1=?,varmeter1E=?,varmeter2=?,varmeter2E=?,highVoltage=?,"
				+ "highVoltageE=?,transformer1T=?,transformer1E=?,transformer1V=?,transformer1Power=?,transformer2T=?,transformer2E=?,"
				+ "transformer2V=?,transformer2Power=?,recorder=?,watch=?,person=?,mark=?,ratio=? where id=?";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, erhi.getCustomerNo());
			ps.setTimestamp(2, TimeUtilToSql.getTimeStamp(erhi.getMaintenanceDate()));
			ps.setString(3, erhi.getEDRoom());
			ps.setString(4, erhi.getIndoorTemperature());
			ps.setString(5, erhi.getIndoorHumidity());
			ps.setString(6, erhi.getTotalNumber());
			ps.setString(7, erhi.getTotalElectricity());
			ps.setString(8, erhi.getDemandNumber());
			ps.setString(9, erhi.getDemandElectricity());
			ps.setString(10, erhi.getPeakValue());
			ps.setString(11, erhi.getPeakValueElectricity());
			ps.setString(12, erhi.getFairValue());
			ps.setString(13, erhi.getFairValueElectricity());
			ps.setString(14, erhi.getValley());
			ps.setString(15, erhi.getValleyElectricity());
			ps.setString(16, erhi.getVarmeter1());
			ps.setString(17, erhi.getVarmeter1E());
			ps.setString(18, erhi.getVarmeter2());
			ps.setString(19, erhi.getVarmeter2E());
			ps.setString(20, erhi.getHighVoltage());
			ps.setString(21, erhi.getHighVoltageE());
			ps.setString(22, erhi.getTransformer1T());
			ps.setString(23, erhi.getTransformer1E());
			ps.setString(24, erhi.getTransformer1V());
			ps.setString(25, erhi.getTransformer1Power());
			ps.setString(26, erhi.getTransformer2T());
			ps.setString(27, erhi.getTransformer2E());
			ps.setString(28, erhi.getTransformer2V());
			ps.setString(29, erhi.getTransformer2Power());
			ps.setString(30, erhi.getRecorder());
			ps.setString(31, erhi.getWatch());
			ps.setString(32, erhi.getPerson());
			ps.setString(33, erhi.getMark());
			ps.setString(34, erhi.getRatio());
			ps.setInt(35, erhi.getId());
			flag = ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室运行高压信息=>更新操作:"+erhi.getId());
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateElectricityRunLInfo(ElectricityRunLInfo erli, String no) {
		
		flag = true;
		String sql = "update electricityrunlinfo set customerNo=?,maintenanceDate=?,EDRoom=?,current1=?,electricity1=?,current2=?,electricity2=?,current3=?,"
				+ "electricity3=?,current4=?,electricity4=?,current5=?,electricity5=?,current6=?,electricity6=?,current7=?,electricity7=?,current8=?,electricity8=?,"
				+ "current9=?,electricity9=?,current10=?,electricity10=?,recorder=?,watch=?,person=?,mark=?,ratio=? where id=?";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, erli.getCustomerNo());
			ps.setTimestamp(2, TimeUtilToSql.getTimeStamp(erli.getMaintenanceDate()));
			ps.setString(3, erli.getEDRoom());
			ps.setString(4, erli.getCurrent1());
			ps.setString(5, erli.getElectricity1());
			ps.setString(6, erli.getCurrent2());
			ps.setString(7, erli.getElectricity2());
			ps.setString(8, erli.getCurrent3());
			ps.setString(9, erli.getElectricity3());
			ps.setString(10, erli.getCurrent4());
			ps.setString(11, erli.getElectricity4());
			ps.setString(12, erli.getCurrent5());
			ps.setString(13, erli.getElectricity5());
			ps.setString(14, erli.getCurrent6());
			ps.setString(15, erli.getElectricity6());
			ps.setString(16, erli.getCurrent7());
			ps.setString(17, erli.getElectricity7());
			ps.setString(18, erli.getCurrent8());
			ps.setString(19, erli.getElectricity8());
			ps.setString(20, erli.getCurrent9());
			ps.setString(21, erli.getElectricity9());
			ps.setString(22, erli.getCurrent10());
			ps.setString(23, erli.getElectricity10());
			ps.setString(24, erli.getRecorder());
			ps.setString(25, erli.getWatch());
			ps.setString(26, erli.getPerson());
			ps.setString(27, erli.getMark());
			ps.setString(28, erli.getRatio());
			ps.setInt(29, erli.getId());
			flag = ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室运行底压信息=>更新操作:"+erli.getId());
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public boolean updateFlushTankInfo(FlushTankInfo fti, String no) {	
		
		flag = true;
		String sql = "update flushtankinfo set customerNo=?,maintenanceDate=?,gasTank=?,code=?,finishCode=?,weight=?,recorder=?,mark=? where id=?";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, fti.getCustomerNo());
			ps.setTimestamp(2, TimeUtilToSql.getTimeStamp(fti.getMaintenanceDate()));
			ps.setString(3, fti.getGasTank());
			ps.setString(4, fti.getCode());
			ps.setString(5, fti.getFinishCode());
			ps.setString(6, fti.getWeight());
			ps.setString(7, fti.getRecorder());
			ps.setString(8, fti.getMark());
			ps.setInt(9, fti.getId());
			flag = ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气气体罐冲=>更新操作:"+fti.getId());
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateGasStationInfo(GasStationInfo gsi, String no) {		
		
		flag = true;
		String sql = "update gasstationinfo set customerNo=?,maintenanceDate=?,oxygen=?,acetylene=?,argon=?,valve=?,station=?,"
				+ "conduit=?,content=?,maintenancePerson=?,mark=? where id=?";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, gsi.getCustomerNo());
			ps.setTimestamp(2, TimeUtilToSql.getTimeStamp(gsi.getMaintenanceDate()));
			ps.setString(3, gsi.getOxygen());
			ps.setString(4, gsi.getAcetylene());
			ps.setString(5, gsi.getArgon());
			ps.setString(6, gsi.getValve());
			ps.setString(7, gsi.getStation());
			ps.setString(8, gsi.getConduit());
			ps.setString(9, gsi.getContent());
			ps.setString(10, gsi.getMaintenancePerson());
			ps.setString(11, gsi.getMark());
			ps.setInt(12, gsi.getId());
			flag = ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>供气站看护信息=>更新操作:"+gsi.getId());
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public boolean updateTankInfo(TankInfo ti, String no) {		
		
		flag = true;
		String sql = "update tankinfo set customerNo=?,maintenanceDate=?,oxygenCode1=?,oxygenWeight1=?,oxygenCode2=?,oxygenWeight2=?,"
				+ "propaneCode1=?,propaneWeight1=?,propaneCode2=?,propaneWeight2=?,dioxideCode1=?,dioxideWeight1=?,dioxideCode2=?,"
				+ "dioxideWeight2=?,recorder=?,person=?,mark=? where id=?";
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, ti.getCustomerNo());
			ps.setTimestamp(2, TimeUtilToSql.getTimeStamp(ti.getMaintenanceDate()));
			ps.setString(3, ti.getOxygenCode1());
			ps.setString(4, ti.getOxygenWeight1());
			ps.setString(5, ti.getOxygenCode2());
			ps.setString(6, ti.getOxygenWeight2());
			ps.setString(7, ti.getPropaneCode1());
			ps.setString(8, ti.getPropaneWeight1());
			ps.setString(9, ti.getPropaneCode2());
			ps.setString(10, ti.getPropaneWeight2());
			ps.setString(11, ti.getDioxideCode1());
			ps.setString(12, ti.getDioxideWeight1());
			ps.setString(13, ti.getDioxideCode2());
			ps.setString(14, ti.getDioxideWeight2());
			ps.setString(15, ti.getRecorder());
			ps.setString(16, ti.getPerson());
			ps.setString(17, ti.getMark());
			ps.setInt(18, ti.getId());
			flag = ps.execute();
			ps.close();
			OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气气体罐体信息=>更新操作:"+ti.getId());
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
}
