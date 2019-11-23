package com.zhuoer.newCareDevice.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.newCareDevice.bean.Dbczjlb;
import com.zhuoer.newCareDevice.bean.Jzgqzlljlb;
import com.zhuoer.newCareDevice.bean.Kqysjyxjlb;
import com.zhuoer.newCareDevice.bean.Pdsdy;
import com.zhuoer.newCareDevice.bean.Pdsgy;
import com.zhuoer.newCareDevice.bean.Qzxjjlb;
import com.zhuoer.newCareDevice.bean.Wnsdlq;

public class UpdateDao {
	
	public static boolean updateDbczjlb(Dbczjlb qt){
		String sql = "update Dbczjlb set datetime=?,shebei=?,qjine=?,jine=?,hjine=?,jiluren=?,mark=?,status=?,shenheyijian=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, qt.getDatetime());
			ps.setString(2, qt.getShebei());
			ps.setFloat(3,qt.getQjine());
			ps.setFloat(4, qt.getJine());
			ps.setFloat(5, qt.getHjine());
			ps.setString(6, qt.getJiluren());
			ps.setString(7, qt.getMark());
			ps.setString(8, "0");
			ps.setString(9, "");
			ps.setInt(10, qt.getId());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static boolean updateJzgqzlljlb(Jzgqzlljlb qt) {
		String sql = "update Jzgqzlljlb set datetime=?,qiti=?,shebei=?,liuliangshuzhi=?,yalizhi=?,jiluren=?,mark=?,status=?,shenheyijian=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, qt.getDatetime());
			ps.setString(2, qt.getQiti());
			ps.setString(3,qt.getShebei());
			ps.setString(4, qt.getLiuliangshuzhi());
			ps.setString(5, qt.getYalizhi());
			ps.setString(6, qt.getJiluren());
			ps.setString(7, qt.getMark());
			ps.setString(8, "0");
			ps.setString(9, "");
			ps.setInt(10, qt.getId());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static boolean updatePdsdy(Pdsdy qt){
		String sql = "update Pdsdy set datetime=?,shebei=?,jine=?,yougongzong=?,yougongxuliang=?,"
				+ "yougongjianzhi=?,yougongfengzhi=?,yougongpingzhi=?,yougongguzhi=?,wugong1=?,wugong2=?,wugong3=?,wugong4=?,"
				+ "gonglvyinshu=?,shijigonglvyinshu=?,beilv=?,dianya=?,dianliu=?,jiluren=?,mark=?,status=?,shenheyijian=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, qt.getDatetime());
			ps.setString(2,qt.getShebei());
			ps.setString(3, qt.getJine());
			ps.setFloat(4, qt.getYougongzong());
			ps.setFloat(5, qt.getYougongxuliang());
			ps.setFloat(6, qt.getYougongjianzhi());
			ps.setFloat(7, qt.getYougongfengzhi());
			ps.setFloat(8, qt.getYougongpingzhi());
			ps.setFloat(9, qt.getYougongguzhi());
			ps.setFloat(10, qt.getWugong1());
			ps.setFloat(11, qt.getWugong2());
			ps.setFloat(12, qt.getWugong3());
			ps.setFloat(13, qt.getWugong4());
			ps.setFloat(14, qt.getGonglvyinshu());
			ps.setFloat(15, qt.getShijigonglvyinshu());
			ps.setString(16, qt.getBeilv());
			ps.setString(17, qt.getDianya());
			ps.setFloat(18, qt.getDianliu());
			ps.setString(19, qt.getJiluren());
			ps.setString(20, qt.getMark());
			ps.setString(21, "0");
			ps.setString(22, "");
			ps.setInt(23, qt.getId());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static boolean updatePdsgy(Pdsgy qt) {
		String sql = "update Pdsgy set datetime=?,shebei=?,jine=?,yougongzong=?,yougongxuliang=?,"
				+ "yougongjianzhi=?,yougongfengzhi=?,yougongpingzhi=?,yougongguzhi=?,wugong1=?,wugong2=?,wugong3=?,wugong4=?,"
				+ "gonglvyinshu=?,shijigonglvyinshu=?,beilv=?,dianya=?,dianliu=?,jiluren=?,mark=?,status=?,shenheyijian=?,wendu=?,shidu=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, qt.getDatetime());
			ps.setString(2,qt.getShebei());
			ps.setString(3, qt.getJine());
			ps.setFloat(4, qt.getYougongzong());
			ps.setFloat(5, qt.getYougongxuliang());
			ps.setFloat(6, qt.getYougongjianzhi());
			ps.setFloat(7, qt.getYougongfengzhi());
			ps.setFloat(8, qt.getYougongpingzhi());
			ps.setFloat(9, qt.getYougongguzhi());
			ps.setFloat(10, qt.getWugong1());
			ps.setFloat(11, qt.getWugong2());
			ps.setFloat(12, qt.getWugong3());
			ps.setFloat(13, qt.getWugong4());
			ps.setFloat(14, qt.getGonglvyinshu());
			ps.setFloat(15, qt.getShijigonglvyinshu());
			ps.setString(16, qt.getBeilv());
			ps.setString(17, qt.getDianya());
			ps.setFloat(18, qt.getDianliu());
			ps.setString(19, qt.getJiluren());
			ps.setString(20, qt.getMark());
			ps.setString(21, "0");
			ps.setString(22, "");
			ps.setFloat(23, qt.getWendu());
			ps.setString(24, qt.getShidu());
			ps.setInt(25, qt.getId());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static boolean updateQzxjjlb(Qzxjjlb qt) {
		String sql = "update Qzxjjlb set datetime=?,qiti=?,shebei=?,cunguanyali=?,yeweidushu=?,zhongliang=?,"
				+ "zongliuliangjibiaoma=?,diyayali=?,zongliuliangjibiaokuang=?,zongliuliangjiwendu=?,tiaoyaxiangshuiwei=?,"
				+ "tiaoyaxiangwendu=?,tiaoyaxiangyali=?,fureqishuiwei=?,zengxiaoxiangyali=?,fureqiwendu=?,kongyashiqihuaqiyunxingzhuangtai=?,"
				+ "qixiangfamen=?,yexiangfamen=?,chukoufamen=?,famenlianjiechu=?,daguanshiyongqingkuang=?,mark=?,"
				+ "status=?,shenheyijian=?,jiluren=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, qt.getDatetime());
			ps.setString(2,qt.getQiti());
			ps.setString(3, qt.getShebei());
			ps.setString(4, qt.getCunguanyali());
			ps.setString(5, qt.getYeweidushu());
			ps.setString(6, qt.getZhongliang());
			ps.setString(7, qt.getZongliuliangbiaoma());
			ps.setString(8, qt.getDiyayali());
			ps.setString(9, qt.getZongliuliangbiaokuang());
			ps.setString(10, qt.getZongliuliangwendu());
			ps.setString(11, qt.getTiaoyaxiangshuiwei());
			ps.setString(12, qt.getTiaoyaxiangwendu());
			ps.setString(13, qt.getTiaoyaxiangyali());
			ps.setString(14, qt.getFureqishuiwei());
			ps.setString(15, qt.getZengxiaoxiangyali());
			ps.setString(16, qt.getFureqiwendu());
			ps.setString(17, qt.getKongyashiqihuaqiyunxingzhuangtai());
			ps.setString(18, qt.getQixiangfamen());
			ps.setString(19, qt.getYexiangfamen());
			ps.setString(20, qt.getChukoufamen());
			ps.setString(21, qt.getFamenlianjiechu());
			ps.setString(22, qt.getDaguanshiyongqingkuang());
			ps.setString(23, qt.getMark());
			ps.setString(24, "0");
			ps.setString(25, "");
			ps.setString(26, qt.getJiluren());
			ps.setInt(27, qt.getId());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static boolean updateWnsdlq(Wnsdlq qt) {
		String sql = "update Wnsdlq set dianliu=?,dianliang=?,mark=?,datetime=?,shebei=?,status=?,shenheyijian=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setFloat(1, qt.getDianliu());
			ps.setFloat(2,qt.getDianliang());
			ps.setString(3, qt.getMark());
			ps.setString(4, qt.getDatetime());
			ps.setString(5, qt.getShebei());
			ps.setString(6, "0");
			ps.setString(7, "");
			ps.setString(8, qt.getJiluren());
			ps.setInt(9, qt.getId());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static boolean updateKqysjyxjlb(Kqysjyxjlb qt) {
		String sql = "update kqysjyxjlb set datetime=?,shebei=?,paiqiyali=?,xitongyali=?,lengqueshuiyali=?,runhuayouyali=?,paiqiwendu1=?,paiqiwendu2=?,xitongwendu=?,"
				+ "huanjingwendu=?,runhuayouwendu=?,lengqueshuiwendu=?,qzhudianjiwendu=?,hzhudianjiwendu=?,yunzhuandianya=?,dianliuzhishi=?,"
				+ "leijiyunxingshijian=?,leijifuheshijian=?,mark=?,status=?,shenheyijian=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1, qt.getDatetime());
			ps.setString(2, qt.getShebei());
			ps.setFloat(3, qt.getPaiqiyali());
			ps.setFloat(4, qt.getXitongyali());
			ps.setFloat(5, qt.getLengqueshuiyali());
			ps.setFloat(6, qt.getRunhuayouyali());
			ps.setFloat(7, qt.getPaiqiwendu1());
			ps.setFloat(8, qt.getPaiqiwendu2());
			ps.setFloat(9, qt.getXitongwendu());
			
			ps.setFloat(10, qt.getHuanjingwendu());
			ps.setFloat(11, qt.getRunhuayouwendu());
			ps.setFloat(12, qt.getLengqueshuiwendu());
			ps.setFloat(13, qt.getQzhudianjiwendu());
			ps.setFloat(14, qt.getHzhudianjiwendu());
			ps.setFloat(15, qt.getYunzhuandianya());
			ps.setFloat(16, qt.getDianliuzhishi());
			ps.setFloat(17, qt.getLeijiyunxingshijian());
			ps.setFloat(18, qt.getLeijifuheshijian());
			ps.setString(19, qt.getMark());
			ps.setString(20, "0");
			ps.setString(21, "");
			ps.setInt(22, qt.getId());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
