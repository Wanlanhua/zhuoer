package com.zhuoer.careDevice.dao;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * 
 * Title:UpdateState
 * Description: 运行信息表审核
 * @author purple
 * date:2018年4月14日
 */
public class UpdataState {

	public static void stateY(String careName, String id, String auditOpinion2, String no) {
		String sql = "update " + careName +" set auditOpinion2='"+auditOpinion2+"', state = 1 where id =" + id;
		try {
			new DataBaseAccess().update(sql);
			switch (careName) {
			case "aircompressorinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>空气压缩机运行信息=>审核通过操作:"+id);
				break;
			case "airpressurestationinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>空压站看护信息=>审核通过操作:"+id);
				break;
			case "dailyflowinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气日流量统计=>审核通过操作:"+id);		
				break;
			case "electricityinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室看护信息=>审核通过操作:"+id);
				break;
			case "electricityrunhinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室运行高压信息=>审核通过操作:"+id);
				break;
			case "electricityrunlinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室运行底压信息=>审核通过操作:"+id);
				break;
			case "flushtankinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气气体罐冲=>审核通过操作:"+id);
				break;
			case "gasstationinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>供气站看护信息=>审核通过操作:"+id);
				break;
			case "tankinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气气体罐体信息=>审核通过操作:"+id);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void stateN(String careName, String id, String auditOpinion2, String no) {
		String sql = "update " + careName +" set auditOpinion2= '"+auditOpinion2+"',state = 2 where id =" + id;
		try {
			new DataBaseAccess().update(sql);
			switch (careName) {
			case "aircompressorinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>空气压缩机运行信息=>审核不通过操作:"+id);
				break;
			case "airpressurestationinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>空压站看护信息=>审核不通过操作:"+id);
				break;
			case "dailyflowinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气日流量统计=>审核不通过操作:"+id);		
				break;
			case "electricityinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室看护信息=>审核不通过操作:"+id);
				break;
			case "electricityrunhinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室运行高压信息=>审核不通过操作:"+id);
				break;
			case "electricityrunlinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>配电室运行底压信息=>审核不通过操作:"+id);
				break;
			case "flushtankinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气气体罐冲=>审核不通过操作:"+id);
				break;
			case "gasstationinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>供气站看护信息=>审核不通过操作:"+id);
				break;
			case "tankinfo":
				OpLogInfoTools.insertOpLogInfo(no, "看护管理=>集中供气气体罐体信息=>审核不通过操作:"+id);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
