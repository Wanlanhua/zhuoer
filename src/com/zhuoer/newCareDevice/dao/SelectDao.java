package com.zhuoer.newCareDevice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.newCareDevice.bean.Dbczjlb;
import com.zhuoer.newCareDevice.bean.Jzgqzlljlb;
import com.zhuoer.newCareDevice.bean.Kqysjyxjlb;
import com.zhuoer.newCareDevice.bean.Pdsdy;
import com.zhuoer.newCareDevice.bean.Pdsgy;
import com.zhuoer.newCareDevice.bean.Qiti;
import com.zhuoer.newCareDevice.bean.Qzxjjlb;
import com.zhuoer.newCareDevice.bean.Wnsdlq;

public class SelectDao {
	
	public static ResultSet baseDao(int id, String tableName) {
		String sql = "select * from "+tableName+" where id="+id;
		ResultSet rs = null;
		try {
			rs = new DataBaseAccess().query(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet baseDao(String departmentid, String shebei, String tableName,String starttime, String endtime, int page) {
		starttime = starttime.trim()+" 00:00:00";
		endtime = endtime.trim() + " 23:59:59";
		//String sql = "select * from "+tableName;
		String sql = "select * from "+tableName+" where shebei in (select no from deviceinfo where customerno='"+departmentid
				+"' and no like '%"+shebei+"%') and datetime >='"+starttime+"' and datetime<='"+endtime+"' order by id desc limit "+(page-1)*15+",15";
		ResultSet rs = null;
		try {
			rs = new DataBaseAccess().query(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet baseDao(String departmentid, String shebei, String tableName,String starttime, String endtime) {
		starttime = starttime.trim()+" 00:00:00";
		endtime = endtime.trim() + " 23:59:59";
		String sql = "select * from "+tableName+" where shebei in (select no from deviceinfo where customerno='"+departmentid
				+"' and no like '%"+shebei+"%') and datetime >='"+starttime+"' and datetime<='"+endtime+"' order by id desc";
		System.out.println(sql);
		ResultSet rs = null;
		try {
			rs = new DataBaseAccess().query(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static List<Dbczjlb> getDbczjlb(String departmentid, String shebei, String starttime, String endtime, int page) {
		ResultSet rs = baseDao(departmentid, shebei, "dbczjlb", starttime, endtime, page);
		List<Dbczjlb> list = new ArrayList<Dbczjlb>();
		try {
			while(rs.next()) {
				Dbczjlb obj = new Dbczjlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setHjine(rs.getFloat("hjine"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setMark(rs.getString("mark"));
				obj.setQjine(rs.getFloat("qjine"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJine(rs.getFloat("jine"));
				obj.setShebei(rs.getString("shebei"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Jzgqzlljlb> getJzgqzlljlb(String departmentid, String shebei, String starttime, String endtime, int page) {
		ResultSet rs = baseDao(departmentid, shebei, "jzgqzlljlb", starttime, endtime, page);
		List<Jzgqzlljlb> list = new ArrayList<Jzgqzlljlb>();
		try {
			while(rs.next()) {
				Jzgqzlljlb obj = new Jzgqzlljlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setLiuliangshuzhi(rs.getString("liuliangshuzhi"));
				obj.setMark(rs.getString("mark"));
				obj.setQiti(rs.getString("qiti"));
				obj.setShebei(rs.getString("shebei"));
				obj.setYalizhi(rs.getString("yalizhi"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Pdsdy> getPdsdy(String departmentid, String shebei, String starttime, String endtime, int page) {
		ResultSet rs = baseDao(departmentid, shebei, "pdsdy", starttime, endtime, page);
		List<Pdsdy> list = new ArrayList<Pdsdy>();
		try {
			while(rs.next()) {
				Pdsdy obj = new Pdsdy();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setBeilv(rs.getString("beilv"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setDianya(rs.getString("dianya"));
				obj.setGonglvyinshu(rs.getFloat("gonglvyinshu"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setJine(rs.getString("jine"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setShijigonglvyinshu(rs.getFloat("shijigonglvyinshu"));
				obj.setWugong3(rs.getFloat("wugong3"));
				obj.setWugong2(rs.getFloat("wugong2"));
				obj.setWugong1(rs.getFloat("wugong1"));
				obj.setWugong4(rs.getFloat("wugong4"));
				obj.setYougongzong(rs.getFloat("yougongzong"));
				obj.setYougongfengzhi(rs.getFloat("yougongfengzhi"));
				obj.setYougongguzhi(rs.getFloat("yougongguzhi"));
				obj.setYougongjianzhi(rs.getFloat("yougongjianzhi"));
				obj.setYougongpingzhi(rs.getFloat("yougongpingzhi"));
				obj.setYougongxuliang(rs.getFloat("yougongxuliang"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Pdsgy> getPdsgy(String departmentid, String shebei, String starttime, String endtime, int page){
		ResultSet rs = baseDao(departmentid, shebei, "pdsgy", starttime, endtime, page);
		List<Pdsgy> list = new ArrayList<Pdsgy>();
		try {
			while(rs.next()) {
				Pdsgy obj = new Pdsgy();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setBeilv(rs.getString("beilv"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setDianya(rs.getString("dianya"));
				obj.setGonglvyinshu(rs.getFloat("gonglvyinshu"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setJine(rs.getString("jine"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setShijigonglvyinshu(rs.getFloat("shijigonglvyinshu"));
				obj.setWugong3(rs.getFloat("wugong3"));
				obj.setWugong2(rs.getFloat("wugong2"));
				obj.setWugong1(rs.getFloat("wugong1"));
				obj.setWugong4(rs.getFloat("wugong4"));
				obj.setYougongzong(rs.getFloat("yougongzong"));
				obj.setYougongfengzhi(rs.getFloat("yougongfengzhi"));
				obj.setYougongguzhi(rs.getFloat("yougongguzhi"));
				obj.setYougongjianzhi(rs.getFloat("yougongjianzhi"));
				obj.setYougongpingzhi(rs.getFloat("yougongpingzhi"));
				obj.setYougongxuliang(rs.getFloat("yougongxuliang"));
				obj.setWendu(rs.getFloat("wendu"));
				obj.setShidu(rs.getString("shidu"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Qzxjjlb> getQzxjjlb(String departmentid, String shebei, String starttime, String endtime, int page){
		ResultSet rs = baseDao(departmentid, shebei, "qzxjjlb", starttime, endtime, page);
		List<Qzxjjlb> list = new ArrayList<Qzxjjlb>();
		try {
			while(rs.next()) {
				Qzxjjlb obj = new Qzxjjlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setChukoufamen(rs.getString("chukoufamen"));
				obj.setCunguanyali(rs.getString("cunguanyali"));
				obj.setDaguanshiyongqingkuang(rs.getString("daguanshiyongqingkuang"));
				obj.setDiyayali(rs.getString("diyayali"));
				obj.setFamenlianjiechu(rs.getString("famenlianjiechu"));
				obj.setFureqishuiwei(rs.getString("fureqishuiwei"));
				obj.setFureqiwendu(rs.getString("fureqiwendu"));
				obj.setKongyashiqihuaqiyunxingzhuangtai(rs.getString("kongyashiqihuaqiyunxingzhuangtai"));
				obj.setMark(rs.getString("mark"));
				obj.setQiti(rs.getString("qiti"));
				obj.setQixiangfamen(rs.getString("qixiangfamen"));
				obj.setShebei(rs.getString("shebei"));
				obj.setTiaoyaxiangshuiwei(rs.getString("tiaoyaxiangshuiwei"));
				obj.setTiaoyaxiangwendu(rs.getString("tiaoyaxiangwendu"));
				obj.setTiaoyaxiangyali(rs.getString("tiaoyaxiangyali"));
				obj.setYeweidushu(rs.getString("yeweidushu"));
				obj.setYexiangfamen(rs.getString("yexiangfamen"));
				obj.setZengxiaoxiangyali(rs.getString("zengxiaoxiangyali"));
				obj.setZhongliang(rs.getString("zhongliang"));
				obj.setZongliuliangbiaokuang(rs.getString("zongliuliangjibiaokuang"));
				obj.setZongliuliangbiaoma(rs.getString("zongliuliangjibiaoma"));
				obj.setZongliuliangwendu(rs.getString("zongliuliangjiwendu"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Wnsdlq> getWnsdlq(String departmentid, String shebei, String starttime, String endtime, int page) {
		ResultSet rs = baseDao(departmentid, shebei, "wnsdlq", starttime, endtime, page);
		List<Wnsdlq> list = new ArrayList<Wnsdlq>();
		try {
			while(rs.next()) {
				Wnsdlq obj = new Wnsdlq();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setDianliang(rs.getFloat("dianliang"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Kqysjyxjlb> getKqysjyxjlb(String departmentid, String shebei, String starttime, String endtime, int page) {
		ResultSet rs = baseDao(departmentid, shebei, "kqysjyxjlb", starttime, endtime, page);
		List<Kqysjyxjlb> list = new ArrayList<Kqysjyxjlb>();
		try {
			while(rs.next()) {
				Kqysjyxjlb obj = new Kqysjyxjlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setShebei(rs.getString("shebei"));
				obj.setPaiqiyali(rs.getFloat("paiqiyali"));
				obj.setXitongyali(rs.getFloat("xitongyali"));
				obj.setLengqueshuiyali(rs.getFloat("lengqueshuiyali"));
				obj.setRunhuayouyali(rs.getFloat("runhuayouyali"));
				obj.setPaiqiwendu1(rs.getFloat("paiqiwendu1"));
				obj.setPaiqiwendu2(rs.getFloat("paiqiwendu2"));
				obj.setXitongwendu(rs.getFloat("xitongwendu"));
				obj.setHuanjingwendu(rs.getFloat("huanjingwendu"));
				obj.setRunhuayouwendu(rs.getFloat("runhuayouwendu"));
				obj.setLengqueshuiwendu(rs.getFloat("lengqueshuiwendu"));
				obj.setQzhudianjiwendu(rs.getFloat("qzhudianjiwendu"));
				obj.setHzhudianjiwendu(rs.getFloat("hzhudianjiwendu"));
				obj.setYunzhuandianya(rs.getFloat("yunzhuandianya"));
				obj.setDianliuzhishi(rs.getFloat("dianliuzhishi"));
				obj.setLeijiyunxingshijian(rs.getFloat("leijiyunxingshijian"));
				obj.setLeijifuheshijian(rs.getFloat("leijifuheshijian"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setMark(rs.getString("mark"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Qiti> getQiti(String name, int page) {
		String sql ="select * from qiti where name like '%"+name+"%' limit "+(page-1)+",15";
		ResultSet rs = null;
		List<Qiti> list = new ArrayList<Qiti>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				Qiti qt = new Qiti();
				qt.setId(rs.getInt("id"));
				qt.setName(rs.getString("name"));
				list.add(qt);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Dbczjlb> getDbczjlb(String id) {
		ResultSet rs = baseDao(Integer.valueOf(id), "dbczjlb");
		List<Dbczjlb> list = new ArrayList<Dbczjlb>();
		try {
			while(rs.next()) {
				Dbczjlb obj = new Dbczjlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setHjine(rs.getFloat("hjine"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setMark(rs.getString("mark"));
				obj.setQjine(rs.getFloat("qjine"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJine(rs.getFloat("jine"));
				obj.setShebei(rs.getString("shebei"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Jzgqzlljlb> getJzgqzlljlb(String id) {
		ResultSet rs = baseDao(Integer.valueOf(id), "jzgqzlljlb");
		List<Jzgqzlljlb> list = new ArrayList<Jzgqzlljlb>();
		try {
			while(rs.next()) {
				Jzgqzlljlb obj = new Jzgqzlljlb();
				obj.setId(rs.getInt("id"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setLiuliangshuzhi(rs.getString("liuliangshuzhi"));
				obj.setMark(rs.getString("mark"));
				obj.setQiti(rs.getString("qiti"));
				obj.setShebei(rs.getString("shebei"));
				obj.setYalizhi(rs.getString("yalizhi"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Pdsdy> getPdsdy(String id) {
		ResultSet rs = baseDao(Integer.valueOf(id), "pdsdy");
		List<Pdsdy> list = new ArrayList<Pdsdy>();
		try {
			while(rs.next()) {
				Pdsdy obj = new Pdsdy();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setBeilv(rs.getString("beilv"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setDianya(rs.getString("dianya"));
				obj.setGonglvyinshu(rs.getFloat("gonglvyinshu"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setJine(rs.getString("jine"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setShijigonglvyinshu(rs.getFloat("shijigonglvyinshu"));
				obj.setWugong3(rs.getFloat("wugong3"));
				obj.setWugong2(rs.getFloat("wugong2"));
				obj.setWugong1(rs.getFloat("wugong1"));
				obj.setWugong4(rs.getFloat("wugong4"));
				obj.setYougongzong(rs.getFloat("yougongzong"));
				obj.setYougongfengzhi(rs.getFloat("yougongfengzhi"));
				obj.setYougongguzhi(rs.getFloat("yougongguzhi"));
				obj.setYougongjianzhi(rs.getFloat("yougongjianzhi"));
				obj.setYougongpingzhi(rs.getFloat("yougongpingzhi"));
				obj.setYougongxuliang(rs.getFloat("yougongxuliang"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Pdsgy> getPdsgy(String id){
		ResultSet rs = baseDao(Integer.valueOf(id), "pdsgy");
		List<Pdsgy> list = new ArrayList<Pdsgy>();
		try {
			while(rs.next()) {
				Pdsgy obj = new Pdsgy();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setBeilv(rs.getString("beilv"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setDianya(rs.getString("dianya"));
				obj.setGonglvyinshu(rs.getFloat("gonglvyinshu"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setJine(rs.getString("jine"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setShijigonglvyinshu(rs.getFloat("shijigonglvyinshu"));
				obj.setWugong3(rs.getFloat("wugong3"));
				obj.setWugong2(rs.getFloat("wugong2"));
				obj.setWugong1(rs.getFloat("wugong1"));
				obj.setWugong4(rs.getFloat("wugong4"));
				obj.setYougongzong(rs.getFloat("yougongzong"));
				obj.setYougongfengzhi(rs.getFloat("yougongfengzhi"));
				obj.setYougongguzhi(rs.getFloat("yougongguzhi"));
				obj.setYougongjianzhi(rs.getFloat("yougongjianzhi"));
				obj.setYougongpingzhi(rs.getFloat("yougongpingzhi"));
				obj.setYougongxuliang(rs.getFloat("yougongxuliang"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setWendu(rs.getFloat("wendu"));
				obj.setShidu(rs.getString("shidu"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Qzxjjlb> getQzxjjlb(String id){
		ResultSet rs = baseDao(Integer.valueOf(id), "qzxjjlb");
		List<Qzxjjlb> list = new ArrayList<Qzxjjlb>();
		try {
			while(rs.next()) {
				Qzxjjlb obj = new Qzxjjlb();
				obj.setId(rs.getInt("id"));
				obj.setChukoufamen(rs.getString("chukoufamen"));
				obj.setCunguanyali(rs.getString("cunguanyali"));
				obj.setDaguanshiyongqingkuang(rs.getString("daguanshiyongqingkuang"));
				obj.setDiyayali(rs.getString("diyayali"));
				obj.setFamenlianjiechu(rs.getString("famenlianjiechu"));
				obj.setFureqishuiwei(rs.getString("fureqishuiwei"));
				obj.setFureqiwendu(rs.getString("fureqiwendu"));
				obj.setKongyashiqihuaqiyunxingzhuangtai(rs.getString("kongyashiqihuaqiyunxingzhuangtai"));
				obj.setMark(rs.getString("mark"));
				obj.setQiti(rs.getString("qiti"));
				obj.setQixiangfamen(rs.getString("qixiangfamen"));
				obj.setShebei(rs.getString("shebei"));
				obj.setTiaoyaxiangshuiwei(rs.getString("tiaoyaxiangshuiwei"));
				obj.setTiaoyaxiangwendu(rs.getString("tiaoyaxiangwendu"));
				obj.setTiaoyaxiangyali(rs.getString("tiaoyaxiangyali"));
				obj.setYeweidushu(rs.getString("yeweidushu"));
				obj.setYexiangfamen(rs.getString("yexiangfamen"));
				obj.setZengxiaoxiangyali(rs.getString("zengxiaoxiangyali"));
				obj.setZhongliang(rs.getString("zhongliang"));
				obj.setZongliuliangbiaokuang(rs.getString("zongliuliangjibiaokuang"));
				obj.setZongliuliangbiaoma(rs.getString("zongliuliangjibiaoma"));
				obj.setZongliuliangwendu(rs.getString("zongliuliangjiwendu"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Wnsdlq> getWnsdlq(String id) {
		ResultSet rs = baseDao(Integer.valueOf(id), "wnsdlq");
		List<Wnsdlq> list = new ArrayList<Wnsdlq>();
		try {
			while(rs.next()) {
				Wnsdlq obj = new Wnsdlq();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setDianliang(rs.getFloat("dianliang"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Kqysjyxjlb> getKqysjyxjlb(int id) {
		ResultSet rs = baseDao(id, "kqysjyxjlb");
		List<Kqysjyxjlb> list = new ArrayList<Kqysjyxjlb>();
		try {
			while(rs.next()) {
				Kqysjyxjlb obj = new Kqysjyxjlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setShebei(rs.getString("shebei"));
				obj.setPaiqiyali(rs.getFloat("paiqiyali"));
				obj.setXitongyali(rs.getFloat("xitongyali"));
				obj.setLengqueshuiyali(rs.getFloat("lengqueshuiyali"));
				obj.setRunhuayouyali(rs.getFloat("runhuayouyali"));
				obj.setPaiqiwendu1(rs.getFloat("paiqiwendu1"));
				obj.setPaiqiwendu2(rs.getFloat("paiqiwendu2"));
				obj.setXitongwendu(rs.getFloat("xitongwendu"));
				obj.setHuanjingwendu(rs.getFloat("huanjingwendu"));
				obj.setRunhuayouwendu(rs.getFloat("runhuayouwendu"));
				obj.setLengqueshuiwendu(rs.getFloat("lengqueshuiwendu"));
				obj.setQzhudianjiwendu(rs.getFloat("qzhudianjiwendu"));
				obj.setHzhudianjiwendu(rs.getFloat("hzhudianjiwendu"));
				obj.setYunzhuandianya(rs.getFloat("yunzhuandianya"));
				obj.setDianliuzhishi(rs.getFloat("dianliuzhishi"));
				obj.setLeijiyunxingshijian(rs.getFloat("leijiyunxingshijian"));
				obj.setLeijifuheshijian(rs.getFloat("leijifuheshijian"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setMark(rs.getString("mark"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Dbczjlb> getDbczjlb(String departmentid, String shebei, String starttime, String endtime) {
		ResultSet rs = baseDao(departmentid, shebei, "dbczjlb", starttime, endtime);
		List<Dbczjlb> list = new ArrayList<Dbczjlb>();
		try {
			while(rs.next()) {
				Dbczjlb obj = new Dbczjlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setHjine(rs.getFloat("hjine"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setMark(rs.getString("mark"));
				obj.setQjine(rs.getFloat("qjine"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJine(rs.getFloat("jine"));
				obj.setShebei(rs.getString("shebei"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Jzgqzlljlb> getJzgqzlljlb(String departmentid, String shebei, String starttime, String endtime) {
		ResultSet rs = baseDao(departmentid, shebei, "jzgqzlljlb", starttime, endtime);
		List<Jzgqzlljlb> list = new ArrayList<Jzgqzlljlb>();
		try {
			while(rs.next()) {
				Jzgqzlljlb obj = new Jzgqzlljlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setLiuliangshuzhi(rs.getString("liuliangshuzhi"));
				obj.setMark(rs.getString("mark"));
				obj.setQiti(rs.getString("qiti"));
				obj.setShebei(rs.getString("shebei"));
				obj.setYalizhi(rs.getString("yalizhi"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Pdsdy> getPdsdy(String departmentid, String shebei, String starttime, String endtime) {
		ResultSet rs = baseDao(departmentid, shebei, "pdsdy", starttime, endtime);
		List<Pdsdy> list = new ArrayList<Pdsdy>();
		try {
			while(rs.next()) {
				Pdsdy obj = new Pdsdy();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setBeilv(rs.getString("beilv"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setDianya(rs.getString("dianya"));
				obj.setGonglvyinshu(rs.getFloat("gonglvyinshu"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setJine(rs.getString("jine"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setShijigonglvyinshu(rs.getFloat("shijigonglvyinshu"));
				obj.setWugong3(rs.getFloat("wugong3"));
				obj.setWugong2(rs.getFloat("wugong2"));
				obj.setWugong1(rs.getFloat("wugong1"));
				obj.setWugong4(rs.getFloat("wugong4"));
				obj.setYougongzong(rs.getFloat("yougongzong"));
				obj.setYougongfengzhi(rs.getFloat("yougongfengzhi"));
				obj.setYougongguzhi(rs.getFloat("yougongguzhi"));
				obj.setYougongjianzhi(rs.getFloat("yougongjianzhi"));
				obj.setYougongpingzhi(rs.getFloat("yougongpingzhi"));
				obj.setYougongxuliang(rs.getFloat("yougongxuliang"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Pdsgy> getPdsgy(String departmentid, String shebei, String starttime, String endtime){
		ResultSet rs = baseDao(departmentid, shebei, "pdsgy", starttime, endtime);
		List<Pdsgy> list = new ArrayList<Pdsgy>();
		try {
			while(rs.next()) {
				Pdsgy obj = new Pdsgy();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setBeilv(rs.getString("beilv"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setDianya(rs.getString("dianya"));
				obj.setGonglvyinshu(rs.getFloat("gonglvyinshu"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setJine(rs.getString("jine"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setShijigonglvyinshu(rs.getFloat("shijigonglvyinshu"));
				obj.setWugong3(rs.getFloat("wugong3"));
				obj.setWugong2(rs.getFloat("wugong2"));
				obj.setWugong1(rs.getFloat("wugong1"));
				obj.setWugong4(rs.getFloat("wugong4"));
				obj.setYougongzong(rs.getFloat("yougongzong"));
				obj.setYougongfengzhi(rs.getFloat("yougongfengzhi"));
				obj.setYougongguzhi(rs.getFloat("yougongguzhi"));
				obj.setYougongjianzhi(rs.getFloat("yougongjianzhi"));
				obj.setYougongpingzhi(rs.getFloat("yougongpingzhi"));
				obj.setYougongxuliang(rs.getFloat("yougongxuliang"));
				obj.setWendu(rs.getFloat("wendu"));
				obj.setShidu(rs.getString("shidu"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Qzxjjlb> getQzxjjlb(String departmentid, String shebei, String starttime, String endtime){
		ResultSet rs = baseDao(departmentid, shebei, "qzxjjlb", starttime, endtime);
		List<Qzxjjlb> list = new ArrayList<Qzxjjlb>();
		try {
			while(rs.next()) {
				Qzxjjlb obj = new Qzxjjlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setChukoufamen(rs.getString("chukoufamen"));
				obj.setCunguanyali(rs.getString("cunguanyali"));
				obj.setDaguanshiyongqingkuang(rs.getString("daguanshiyongqingkuang"));
				obj.setDiyayali(rs.getString("diyayali"));
				obj.setFamenlianjiechu(rs.getString("famenlianjiechu"));
				obj.setFureqishuiwei(rs.getString("fureqishuiwei"));
				obj.setFureqiwendu(rs.getString("fureqiwendu"));
				obj.setKongyashiqihuaqiyunxingzhuangtai(rs.getString("kongyashiqihuaqiyunxingzhuangtai"));
				obj.setMark(rs.getString("mark"));
				obj.setQiti(rs.getString("qiti"));
				obj.setQixiangfamen(rs.getString("qixiangfamen"));
				obj.setShebei(rs.getString("shebei"));
				obj.setTiaoyaxiangshuiwei(rs.getString("tiaoyaxiangshuiwei"));
				obj.setTiaoyaxiangwendu(rs.getString("tiaoyaxiangwendu"));
				obj.setTiaoyaxiangyali(rs.getString("tiaoyaxiangyali"));
				obj.setYeweidushu(rs.getString("yeweidushu"));
				obj.setYexiangfamen(rs.getString("yexiangfamen"));
				obj.setZengxiaoxiangyali(rs.getString("zengxiaoxiangyali"));
				obj.setZhongliang(rs.getString("zhongliang"));
				obj.setZongliuliangbiaokuang(rs.getString("zongliuliangjibiaokuang"));
				obj.setZongliuliangbiaoma(rs.getString("zongliuliangjibiaoma"));
				obj.setZongliuliangwendu(rs.getString("zongliuliangjiwendu"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Wnsdlq> getWnsdlq(String departmentid, String shebei, String starttime, String endtime) {
		ResultSet rs = baseDao(departmentid, shebei, "wnsdlq", starttime, endtime);
		List<Wnsdlq> list = new ArrayList<Wnsdlq>();
		try {
			while(rs.next()) {
				Wnsdlq obj = new Wnsdlq();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setDianliang(rs.getFloat("dianliang"));
				obj.setDianliu(rs.getFloat("dianliu"));
				obj.setMark(rs.getString("mark"));
				obj.setShebei(rs.getString("shebei"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Kqysjyxjlb> getKqysjyxjlb(String departmentid, String shebei, String starttime, String endtime) {
		ResultSet rs = baseDao(departmentid, shebei, "kqysjyxjlb", starttime, endtime);
		List<Kqysjyxjlb> list = new ArrayList<Kqysjyxjlb>();
		try {
			while(rs.next()) {
				Kqysjyxjlb obj = new Kqysjyxjlb();
				obj.setId(rs.getInt("id"));
				obj.setDatetime(rs.getString("datetime"));
				obj.setShebei(rs.getString("shebei"));
				obj.setPaiqiyali(rs.getFloat("paiqiyali"));
				obj.setXitongyali(rs.getFloat("xitongyali"));
				obj.setLengqueshuiyali(rs.getFloat("lengqueshuiyali"));
				obj.setRunhuayouyali(rs.getFloat("runhuayouyali"));
				obj.setPaiqiwendu1(rs.getFloat("paiqiwendu1"));
				obj.setPaiqiwendu2(rs.getFloat("paiqiwendu2"));
				obj.setXitongwendu(rs.getFloat("xitongwendu"));
				obj.setHuanjingwendu(rs.getFloat("huanjingwendu"));
				obj.setRunhuayouwendu(rs.getFloat("runhuayouwendu"));
				obj.setLengqueshuiwendu(rs.getFloat("lengqueshuiwendu"));
				obj.setQzhudianjiwendu(rs.getFloat("qzhudianjiwendu"));
				obj.setHzhudianjiwendu(rs.getFloat("hzhudianjiwendu"));
				obj.setYunzhuandianya(rs.getFloat("yunzhuandianya"));
				obj.setDianliuzhishi(rs.getFloat("dianliuzhishi"));
				obj.setLeijiyunxingshijian(rs.getFloat("leijiyunxingshijian"));
				obj.setLeijifuheshijian(rs.getFloat("leijifuheshijian"));
				obj.setStatus(rs.getString("status"));
				obj.setShenheyijian(rs.getString("shenheyijian"));
				obj.setJiluren(rs.getString("jiluren"));
				obj.setMark(rs.getString("mark"));
				list.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
