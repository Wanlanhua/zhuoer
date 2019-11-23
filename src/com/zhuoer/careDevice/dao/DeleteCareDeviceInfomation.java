package com.zhuoer.careDevice.dao;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

public class DeleteCareDeviceInfomation {

	private boolean flag;
	
	public boolean deleteCareDevice(int tId, String tName) {
		flag = true;
		String sql = "delete from " + tName +" where id = " + tId;
		try {
			new DataBaseAccess().update(sql);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public void deleteAirCompressorInfo(int id, String no) {
		deleteCareDevice(id, "aircompressorinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>空气压缩机运行信息=>删除操作:"+id);
	}
	
	public void deleteAirPressureStationInfo(int id, String no) {
		deleteCareDevice(id, "airpressurestationinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>空压站看护信息=>删除操作:"+id);
	}
	
	public void deleteDailyFlowInfo(int id, String no) {
		deleteCareDevice(id, "dailyflowinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气日流量统计=>删除操作:"+id);
	}
	
	public void deleteElectricityInfo(int id, String no) {
		deleteCareDevice(id, "electricityinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室看护信息=>删除操作:"+id);
	}
	
	public void deleteElectricityRunHInfo(int id, String no) {
		deleteCareDevice(id, "electricityrunhinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室运行高压信息=>删除操作:"+id);
	}
	
	public void deleteElectricityRunLInfo(int id, String no) {
		deleteCareDevice(id, "electricityrunlinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室运行底压信息=>删除操作:"+id);
	}
	
	public void deleteFlushTankInfo(int id, String no) {
		deleteCareDevice(id, "flushtankinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气气体罐冲=>删除操作:"+id);
	}
	
	public void deleteGasStationInfo(int id, String no) {
		deleteCareDevice(id, "gasstationinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>供气站看护信息=>删除操作:"+id);
	}
	
	public void deleteTankInfo(int id, String no) {
		deleteCareDevice(id, "tankinfo");
		OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气气体罐体信息=>删除操作:"+id);
	}
	
}
