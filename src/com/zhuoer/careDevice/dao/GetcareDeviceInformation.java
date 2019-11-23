package com.zhuoer.careDevice.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.careDevice.entity.AirCompressorInfo;
import com.zhuoer.careDevice.entity.AirPressureStationInfo;
import com.zhuoer.careDevice.entity.DailyFlowInfo;
import com.zhuoer.careDevice.entity.ElectricityInfo;
import com.zhuoer.careDevice.entity.ElectricityRunHInfo;
import com.zhuoer.careDevice.entity.ElectricityRunLInfo;
import com.zhuoer.careDevice.entity.FlushTankInfo;
import com.zhuoer.careDevice.entity.GasStationInfo;
import com.zhuoer.careDevice.entity.TankInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * 
 * Title:Getcare_deviceInformation
 * Description:获取任意看护表信息
 * @author purple
 * date:2018年4月3日
 */

public class GetcareDeviceInformation {
	
	
	/**
	 * 
	 * @author purple
	 * @method getCareDevice
	 * @see (查询对应表的结果集)
	 * @param careName  表名
	 * @param employeeNo   工号
	 * @param name   设备名
	 * @param role   角色
	 * @return
	 * @retrun ResultSet
	 * @date 2018年4月14日
	 */
	public static ResultSet getCareDevice(String careName, String no, String name, int role, int page) {
		PreparedStatement ps = null;
		DataBaseAccess db = new DataBaseAccess();
		ResultSet rs = null;
		String departmentId = "";
		if(role==0) {
			String sd = "select departmentid from customerinfo wehre no=?";
			try {
				ps = db.getPreparedStatement(sd);
				ps.setString(1, no);
				rs = ps.executeQuery();
				if(rs.next()) {
					departmentId = rs.getString("departmentid");
				}
				rs.close();
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "select * from "+careName+" where customerno like'%"+departmentId+"%'";
			try {
				rs = db.query(sql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return rs;
		}
		else if(role==5){
			String sql ="select * from "+careName+" where customerno like '%"+departmentId+"%'";
			try {
				rs = db.query(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
		}
		else {
			String sd = "select department from employeeinfo wehre no=?";
			try {
				ps = db.getPreparedStatement(sd);
				ps.setString(1, no);
				rs = ps.executeQuery();
				if(rs.next()) {
					departmentId = rs.getString("department");
				}
				rs.close();
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "select * from "+careName+" where customerno like'%"+departmentId+"%'";
			try {
				rs = db.query(sql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return rs;
		}
	}
	
	
	/**
	 * 
	 * @author purple
	 * @method getAirCompressorInfo
	 * @see (返回空气压缩机运行信息[AirCompressorInfo]对象的结果)
	 * @param 
	 * @return List<AirCompressorInfo>
	 * @date 2018年4月3日
	 * @return
	 */
	public static List<AirCompressorInfo> getAirCompressorInfo(String no, String name, int role, int page) {
		
		ResultSet rs = null;
		rs = getCareDevice("aircompressorinfo", no, name, role, page);
		List<AirCompressorInfo> list = new ArrayList<AirCompressorInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					AirCompressorInfo aci = new AirCompressorInfo();
					aci.setId(rs.getInt("id"));
					aci.setCustomerNo(rs.getString("customerNo"));
					aci.setMaintenanceDate(TimeUtilToSql.getTimestampUtilDate(rs.getTimestamp("maintenanceDate")));
					aci.setExhaust(rs.getString("exhaust"));
					aci.setSystem(rs.getString("system"));
					aci.setCoolingWater(rs.getString("coolingWater"));
					aci.setLube(rs.getString("lube"));
					aci.setExhaust1T(rs.getString("exhaust1T"));
					aci.setExhaust2T(rs.getString("exhaust2T"));
					aci.setSystemT(rs.getString("systemT"));
					aci.setAmbientT(rs.getString("ambientT"));
					aci.setLubeT(rs.getString("lubeT"));
					aci.setCoolingWaterT(rs.getString("coolingWater"));
					aci.setFrontBearingT(rs.getString("frontBearingT"));
					aci.setRearBearingT(rs.getString("rearBearingT"));
					aci.setOperatingVoltage(rs.getString("operatingVoltage"));
					aci.setCurrentIndication(rs.getString("currentIndication"));
					aci.setRuntime(rs.getString("runtime"));
					aci.setLoadTime(rs.getString("loadTime"));
					aci.setRecorder(rs.getString("recorder"));	
					aci.setPerson(rs.getString("person"));
					aci.setState(rs.getString("state"));
					aci.setAuditOpinion2(rs.getString("auditOpinion2"));
					aci.setMark(rs.getString("mark"));
					list.add(aci);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getAirPressureStationInfo
	 * @see (返回空气站看护信息[AirPressureStationInfo]对象的结果)
	 * @param @return
	 * @return List<AirPressureStationInfo>
	 * @date 2018年4月5日
	 * @return
	 */
	public static List<AirPressureStationInfo> getAirPressureStationInfo(String no, String name, int role, int page) {
		ResultSet rs = getCareDevice("airpressurestationinfo", no, name, role, page);
		List<AirPressureStationInfo> list = new ArrayList<AirPressureStationInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					AirPressureStationInfo aci = new AirPressureStationInfo();
					aci.setId(rs.getInt("id"));
					aci.setMaintenanceDate(TimeUtilToSql.getTimestampUtilDate(rs.getTimestamp("maintenanceDate")));
					aci.setSituation(rs.getString("situation"));
					aci.setPressure(rs.getString("pressure"));
					aci.setLube(rs.getString("lube"));
					aci.setCoolingWater(rs.getString("coolingWater"));
					aci.setStation(rs.getString("station"));
					aci.setOiLevel(rs.getString("oilevel"));
					aci.setContent(rs.getString("content"));
					aci.setMaintenancePerson(rs.getString("maintenancePerson"));
					aci.setCustomerNo(rs.getString("customerNo"));
					aci.setMark(rs.getString("mark"));
					aci.setAuditOpinion2(rs.getString("auditOpinion2"));
					aci.setState(rs.getString("state"));
					list.add(aci);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getDailyFlowInfo
	 * @see (返回集中供气日流量统计[DailyFlowInfo]对象的结果)
	 * @param @return
	 * @return List<DailyFlowInfo>
	 * @date 2018年4月5日
	 * @return
	 */
	public static List<DailyFlowInfo> getDailyFlowInfo(String no, String name, int role, int page) {
		ResultSet rs = getCareDevice("dailyflowinfo", no, name, role, page);
		List<DailyFlowInfo> list = new ArrayList<DailyFlowInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					DailyFlowInfo dfi = new DailyFlowInfo();
					dfi.setId(rs.getInt("id"));
					dfi.setCustomerNo(rs.getString("customerNo"));
					dfi.setAddress(rs.getString("address"));
					dfi.setOxygenReading(rs.getString("oxygenReading"));
					dfi.setOxygenConsumption(rs.getString("oxygenConsumption"));
					dfi.setOxygenPressure(rs.getString("oxygenPressure"));
					dfi.setPropaneReading(rs.getString("propaneReading"));
					dfi.setPropaneConsumption(rs.getString("propaneConsumption"));
					dfi.setPropanePressure(rs.getString("propanePressure"));
					dfi.setDioxideReading(rs.getString("dioxideReading"));
					dfi.setDioxideConsumption(rs.getString("dioxideConsumption"));
					dfi.setDioxidePressure(rs.getString("dioxidePressure"));
					dfi.setMaintenanceDate(TimeUtilToSql.getTimestampUtilDate(rs.getTimestamp("maintenanceDate")));
					dfi.setRecorder(rs.getString("recorder"));
					dfi.setPerson(rs.getString("person"));
					dfi.setState(rs.getString("state"));
					dfi.setAuditOpinion2(rs.getString("auditOpinion2"));
					dfi.setMark(rs.getString("mark"));
					list.add(dfi);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getElectricityInfo
	 * @see (返回配电室看护信息[ElectricityInfo]对象的结果)
	 * @param @return
	 * @return List<ElectricityInfo>
	 * @date 2018年4月5日
	 * @return
	 */
	public static List<ElectricityInfo> getElectricityInfo(String no, String name, int role, int page) {
		ResultSet rs = getCareDevice("electricityinfo", no, name, role, page);
		List<ElectricityInfo> list = new ArrayList<ElectricityInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					ElectricityInfo ei = new ElectricityInfo();
					ei.setId(rs.getInt("id"));
					ei.setCustomerNo(rs.getString("customerNo"));
					ei.setHighVoltage(rs.getString("highVoltage"));
					ei.setBottomPiezoelectricity(rs.getString("bottomPiezoelectricity"));
					ei.setWatch(rs.getString("watch"));
					ei.setPerson(rs.getString("person"));
					ei.setState(rs.getString("state"));
					ei.setAuditOpinion2(rs.getString("auditOpinion2"));
					ei.setMark(rs.getString("mark"));
					list.add(ei);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getElectricityRunHInfo
	 * @see (返回配电室运行高压信息[ElectricityRunHInfo]对象的结果)
	 * @param @return
	 * @return List<ElectricityRunHInfo>
	 * @date 2018年4月5日
	 * @return
	 */
	public static List<ElectricityRunHInfo> getElectricityRunHInfo(String no, String name, int role, int page) {
		ResultSet rs = getCareDevice("electricityrunhinfo", no, name, role, page);
		List<ElectricityRunHInfo> list = new ArrayList<ElectricityRunHInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					ElectricityRunHInfo erhi = new ElectricityRunHInfo();
					erhi.setId(rs.getInt("id"));
					erhi.setCustomerNo(rs.getString("customerNo"));
					erhi.setMaintenanceDate(TimeUtilToSql.getTimestampUtilDate(rs.getTimestamp("maintenanceDate")));
					erhi.setEDRoom(rs.getString("EDRoom"));
					erhi.setIndoorTemperature(rs.getString("indoorTemperature"));
					erhi.setIndoorHumidity(rs.getString("indoorHumidity"));
					erhi.setTotalNumber(rs.getString("totalNumber"));
					erhi.setTotalElectricity(rs.getString("totalElectricity"));
					erhi.setDemandNumber(rs.getString("demandNumber"));
					erhi.setDemandElectricity(rs.getString("demandElectricity"));
					erhi.setPeakValue(rs.getString("peakValue"));
					erhi.setPeakValueElectricity(rs.getString("peakValueElectricity"));
					erhi.setFairValue(rs.getString("fairValue"));
					erhi.setFairValueElectricity(rs.getString("fairValueElectricity"));
					erhi.setValley(rs.getString("valley"));
					erhi.setValleyElectricity(rs.getString("valleyElectricity"));
					erhi.setVarmeter1(rs.getString("varmeter1"));
					erhi.setVarmeter1E(rs.getString("varmeter1E"));
					erhi.setVarmeter2(rs.getString("varmeter2"));
					erhi.setVarmeter2E(rs.getString("varmeter2E"));
					erhi.setHighVoltage(rs.getString("highVoltage"));
					erhi.setHighVoltageE(rs.getString("highVoltageE"));
					erhi.setTransformer1T(rs.getString("transformer1T"));
					erhi.setTransformer1E(rs.getString("transformer1E"));
					erhi.setTransformer1V(rs.getString("transformer1V"));
					erhi.setTransformer1Power(rs.getString("transformer1Power"));
					erhi.setTransformer2T(rs.getString("transformer2T"));
					erhi.setTransformer2E(rs.getString("transformer2E"));
					erhi.setTransformer2V(rs.getString("transformer2V"));
					erhi.setTransformer2Power(rs.getString("transformer2Power"));
					erhi.setRatio(rs.getString("ratio"));
					erhi.setRecorder(rs.getString("recorder"));
					erhi.setWatch(rs.getString("watch"));
					erhi.setPerson(rs.getString("person"));
					erhi.setState(rs.getString("state"));
					erhi.setAuditOpinion2(rs.getString("auditOpinion2"));
					list.add(erhi);
				}
				rs.close();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getElectricityRunLInfo
	 * @see (返回配电室低压运行电压表[ElectricityRunLInfo]对象的结果)
	 * @param @return
	 * @return List<ElectricityRunLInfo>
	 * @date 2018年4月5日
	 * @return
	 */
	public static List<ElectricityRunLInfo> getElectricityRunLInfo(String no, String name, int role, int page) {
		ResultSet rs = getCareDevice("electricityrunlinfo", no, name, role, page);
		List<ElectricityRunLInfo> list = new ArrayList<ElectricityRunLInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					ElectricityRunLInfo erli = new ElectricityRunLInfo();
					erli.setId(rs.getInt("id"));
					erli.setCustomerNo(rs.getString("customerNo"));
					erli.setMaintenanceDate(TimeUtilToSql.getTimestampUtilDate(rs.getTimestamp("maintenanceDate")));
					erli.setEDRoom(rs.getString("EDRoom"));
					erli.setCurrent1(rs.getString("current1"));
					erli.setElectricity1(rs.getString("electricity1"));
					erli.setCurrent2(rs.getString("current2"));
					erli.setElectricity2(rs.getString("electricity2"));
					erli.setCurrent3(rs.getString("current3"));
					erli.setElectricity3(rs.getString("electricity3"));
					erli.setCurrent4(rs.getString("current4"));
					erli.setElectricity4(rs.getString("electricity4"));
					erli.setCurrent5(rs.getString("current5"));
					erli.setElectricity5(rs.getString("electricity5"));
					erli.setCurrent6(rs.getString("current6"));
					erli.setElectricity6(rs.getString("electricity6"));
					erli.setCurrent7(rs.getString("current7"));
					erli.setElectricity7(rs.getString("electricity7"));
					erli.setCurrent8(rs.getString("current8"));
					erli.setElectricity8(rs.getString("electricity8"));
					erli.setCurrent9(rs.getString("current9"));
					erli.setElectricity9(rs.getString("electricity9"));
					erli.setCurrent10(rs.getString("current10"));
					erli.setElectricity10(rs.getString("electricity10"));
					erli.setRatio(rs.getString("ratio"));
					erli.setRecorder(rs.getString("recorder"));
					erli.setWatch(rs.getString("watch"));
					erli.setPerson(rs.getString("person"));
					erli.setState(rs.getString("state"));
					erli.setAuditOpinion2(rs.getString("auditOpinion2"));
					erli.setMark(rs.getString("mark"));
					list.add(erli);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getFlushTankInfo
	 * @see (返回集中供气气体罐信息[FlushTankInfo]对象的结果)
	 * @param @return
	 * @return List<FlushTankInfo>
	 * @date 2018年4月5日
	 * @return
	 */
	public static List<FlushTankInfo> getFlushTankInfo(String no, String name, int role, int page) {
		ResultSet rs = getCareDevice("flushtankinfo", no, name, role, page);
		List<FlushTankInfo> list = new ArrayList<FlushTankInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					FlushTankInfo fti = new FlushTankInfo();
					fti.setId(rs.getInt("id"));
					fti.setCustomerNo(rs.getString("customerNo"));
					fti.setMaintenanceDate(TimeUtilToSql.getTimestampUtilDate(rs.getTimestamp("maintenanceDate")));
					fti.setGasTank(rs.getString("gasTank"));
					fti.setCode(rs.getString("code"));
					fti.setFinishCode(rs.getString("finishCode"));
					fti.setWeight(rs.getString("weight"));
					fti.setRecorder(rs.getString("recorder"));
					fti.setState(rs.getString("state"));
					fti.setAuditOpinion2(rs.getString("auditOpinion2"));
					fti.setMark(rs.getString("mark"));
					list.add(fti);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getGasStationInfo
	 * @see (返回供气站看护信息[GasStationInfo]对象的结果)
	 * @param @return
	 * @return List<GasStationInfo>
	 * @date 2018年4月5日
	 * @return
	 */
	public static List<GasStationInfo> getGasStationInfo(String no, String name, int role, int page) {
		ResultSet rs = getCareDevice("gasstationinfo", no, name, role, page);
		List<GasStationInfo> list = new ArrayList<GasStationInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					GasStationInfo gsi = new GasStationInfo();
					gsi.setId(rs.getInt("id"));
					gsi.setCustomerNo(rs.getString("customerNo"));
					gsi.setMaintenanceDate(TimeUtilToSql.getTimestampUtilDate(rs.getTimestamp("maintenanceDate")));
					gsi.setOxygen(rs.getString("oxygen"));
					gsi.setAcetylene(rs.getString("acetylene"));
					gsi.setArgon(rs.getString("argon"));
					gsi.setStation(rs.getString("station"));
					gsi.setConduit(rs.getString("conduit"));
					gsi.setContent(rs.getString("content"));
					gsi.setValve(rs.getString("valve"));
					gsi.setMaintenancePerson(rs.getString("maintenancePerson"));
					gsi.setState(rs.getString("state"));
					gsi.setAuditOpinion2(rs.getString("auditOpinion2"));
					gsi.setMark(rs.getString("mark"));
					list.add(gsi);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @author purple
	 * @method getTankInfo
	 * @see (返回集中供气气体罐体信息[TankInfo]对象的结果)
	 * @param @return
	 * @return List<TankInfo>
	 * @date 2018年4月5日
	 * @return
	 */
	public static List<TankInfo> getTankInfo(String no, String name, int role, int page) {
		ResultSet rs = getCareDevice("tankinfo", no, name, role, page);
		List<TankInfo> list = new ArrayList<TankInfo>();
		try {
			if(rs != null) {
				while(rs.next()) {
					TankInfo ti = new TankInfo();
					ti.setId(rs.getInt("id"));
					ti.setCustomerNo(rs.getString("customerNo"));
					ti.setMaintenanceDate(TimeUtilToSql.getTimestampUtilDate(rs.getTimestamp("maintenanceDate")));
					ti.setOxygenCode1(rs.getString("oxygenCode1"));
					ti.setOxygenCode2(rs.getString("oxygenCode2"));
					ti.setOxygenWeight1(rs.getString("oxygenWeight1"));
					ti.setOxygenWeight2(rs.getString("oxygenWeight2"));
					ti.setPropaneCode1(rs.getString("propaneCode1"));
					ti.setPropaneCode2(rs.getString("propaneCode2"));
					ti.setPropaneWeight1(rs.getString("propaneWeight1"));
					ti.setPropaneWeight2(rs.getString("propaneWeight2"));
					ti.setDioxideCode1(rs.getString("dioxideCode1"));
					ti.setDioxideCode2(rs.getString("dioxideCode2"));
					ti.setDioxideWeight1(rs.getString("dioxideWeight1"));
					ti.setDioxideWeight2(rs.getString("dioxideWeight2"));
					ti.setRecorder(rs.getString("recorder"));
					ti.setPerson(rs.getString("person"));
					ti.setState(rs.getString("state"));
					ti.setAuditOpinion2(rs.getString("auditOpinion2"));
					ti.setMark(rs.getString("mark"));
					list.add(ti);
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
