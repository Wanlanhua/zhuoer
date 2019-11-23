package com.zhuoer.aJsonAPI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
import com.zhuoer.aJsonAPI.Util.ToJson;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;


public class Power {
	
	private static DataBaseAccess dataBase = new DataBaseAccess();
	private static PreparedStatement ps;
	private static ResultSet rs;

	/**
	 * 添加员工，将员工在权限表注册，除了查看权限，其它都为0
	 * @author purple
	 * @method addUserPower
	 * @see (一句话描述方法的作用)
	 * @param no
	 * @return
	 * @throws Exception
	 * @retrun boolean
	 * @date 2018年6月16日
	 */
	public static boolean addUserPower(String no){
		String menu = "select * from menu2info";
		List<String> menuName = new ArrayList<String>();
		try {
			ps = dataBase.getPreparedStatement(menu);
			rs = ps.executeQuery();
			while(rs.next()) {
				menuName.add(rs.getString("no"));
			}
			rs.close();
			ps.close();
			String addPower = "insert into powerinfo(no,m2no,visible,input,modify,auditing,auditing2,del,find) value(?,?,0,0,0,0,0,0,1)";
			for(int i=0; i<menuName.size(); i++) {
				ps = dataBase.getPreparedStatement(addPower);
				ps.setString(1, no);
				ps.setString(2, menuName.get(i));
				ps.execute();
			}
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 客户添加更新为 看护都为1
	 * @author purple
	 * @method updateCustomerInfo
	 * @retrun boolean
	 * @date 2018年6月24日
	 */
	public static boolean updateCustomerInfo(String no) {
		String sql = "select m2no from powerinfo where m2no like 'm006%' group by m2no";
		List<String> list = new ArrayList<String>();
		try {
			ResultSet rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				list.add(rs.getString("m2no"));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String usql = "update powerinfo set visible='1' where no=? and m2no=?";
		PreparedStatement ps = null;
		for(int i=0; i<list.size(); i++) {
			try {
				ps = new DataBaseAccess().getPreparedStatement(usql);
				ps.setString(1, no);
				ps.setString(2, list.get(i));
				ps.execute();
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 获得用户权限以及用户模块的是否可见信息
	 * @author purple
	 * @method getPower
	 * @see (一句话描述方法的作用)
	 * @param no
	 * @return
	 * @retrun String
	 * @date 2018年6月17日
	 */
	public static String getPower(String no, String name) {
		Map<String, Map<String, String>> model = getUserModel(no, name);
		Map<String, Map<String, String>> power = getUserPower(no, name);
		Map<String, String> username = getUserName(no, name);
		Map<String, Object> userPower = new LinkedHashMap<String, Object>();
		userPower.put("model",model);
		userPower.put("power", power);
		userPower.put("name", username);
		String json = ToJson.toJson(userPower);
		return json;
	}
	
	/**
	 * 获得用户模块的是否可见
	 * @author purple
	 * @method getUserModel
	 * @see (一句话描述方法的作用)
	 * @param no
	 * @return
	 * @retrun Map<String,String>
	 * @date 2018年6月17日
	 */
	public static Map<String, Map<String, String>> getUserModel(String no, String name) {
		String sql;
		if (!name.equals("admin")) {
			sql = "select p.visible,m.m1no,p.no from employeeinfo e, powerinfo p,menu2info m,logininfo l where p.no in( select no from employeeinfo where department in(select department from employeeinfo where no='"+name+"')) and p.no like ? and p.m2no=m.no and p.no=l.name and l.role!='0' and p.no!='admin' group by m.m1no,p.no";
		} else {
			sql = "select p.visible,m.m1no,p.no from employeeinfo e, powerinfo p,menu2info m,logininfo l where p.no like ? and p.m2no=m.no and e.no = p.no and p.no=l.name and l.role!='0' and p.no!='admin' group by m.m1no,p.no";
		}
		Map<String, Map<String, String>> list = new LinkedHashMap<String, Map<String, String>>();
		//拿到所有模块权限
		List<Map<String, String>> alist = new ArrayList<Map<String, String>>();
		
		List<String> nomap = new ArrayList<String>();
		try {
			ps = dataBase.getPreparedStatement(sql);
			ps.setString(1, "%"+no+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> model = new LinkedHashMap<String,String>();
				String m1no = rs.getString("m1no");
				String visible = rs.getString("visible");
				model.put(m1no, visible);
				alist.add(model);
				String id = rs.getString("no");
				nomap.add(id);
			}
			String count = "select count(id) from menu1info";
			ResultSet rs = dataBase.query(count);
			int m1count = 8;
			if(rs.next()) {
				m1count = rs.getInt("count(id)");
			}
			int size = alist.size()/m1count;
			for(int j=0; j<size; j++) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for(int i=j; i<alist.size(); i+=size) {
					Iterator<String> iterator = alist.get(i).keySet().iterator();
					while(iterator.hasNext()) {
						String m1no = iterator.next();
						String visible = alist.get(i).get(m1no);
						map.put(m1no, visible);
						list.put(nomap.get(j), map);
					}
				}
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static Map<String, String> getUserName (String no, String name) {
		String sql = "";
		if(!name.equals("admin")) {
			sql = "select a.name, b.no from employeeinfo a, powerinfo b where a.no = b.no and b.no like '%"+no+"%' and a.no in( select no from employeeinfo where department in(select department from employeeinfo where no like'%"+name+"%')) GROUP BY b.no";
		} else {
			sql = "select a.name, b.no from employeeinfo a, powerinfo b where a.no = b.no and b.no like '%"+no+"%' GROUP BY b.no";
		}
		ResultSet rs;
		Map<String, String> map = new HashMap<>();
		try {
			rs = new DataBaseAccess().query(sql);
			while(rs.next()) {
				map.put(rs.getString("no"), rs.getString("name"));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取用户权限，增删查改审核，二级审核
	 * @author purple
	 * @method getUserPower
	 * @see (一句话描述方法的作用)
	 * @retrun void
	 * @date 2018年6月17日
	 */
	public static Map<String, Map<String,String>> getUserPower(String no, String name) {
		String sql = "";
		if(!name.equals("admin")) {
			sql = "select p.no,p.input,p.modify,p.auditing,p.auditing2,p.del,p.find from employeeinfo e, powerinfo p,logininfo l where p.no in( select no from employeeinfo where department in(select department from employeeinfo where no='"+name+"')) and p.no like ? and p.no!='admin' and l.name=p.no and l.role!='0' group by no";
		} else {
			sql = "select p.no,p.input,p.modify,p.auditing,p.auditing2,p.del,p.find from employeeinfo e, powerinfo p,logininfo l where p.no like ? and p.no!='admin' and l.name=p.no and l.role!='0' group by no";
		}
		Map<String, Map<String, String>> list = new LinkedHashMap<String, Map<String, String>>();
		try {
			ps = dataBase.getPreparedStatement(sql);
			ps.setString(1, "%"+no+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("input", rs.getString("input"));
				map.put("modify", rs.getString("modify"));
				map.put("auditing", rs.getString("auditing"));
				map.put("auditing2", rs.getString("auditing2"));
				map.put("del", rs.getString("del"));
				map.put("find", rs.getString("find"));
				list.put(rs.getString("no"), map);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//获取用户权限
	public static Map<String,String> getOperate(String no) {
		String sql = "select no,input,modify,auditing,auditing2,del,find from powerinfo where no = ? group by no";
		Map<String, String> map = new LinkedHashMap<String, String>();
		try {
			ps = dataBase.getPreparedStatement(sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				map.put("input", rs.getString("input"));
				map.put("modify", rs.getString("modify"));
				map.put("auditing", rs.getString("auditing"));
				map.put("auditing2", rs.getString("auditing2"));
				map.put("del", rs.getString("del"));
				map.put("find", rs.getString("find"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	//用户权限json版本
	public static String getJsonOperate(String no) {
		Map<String, String> operate = getOperate(no);
		String json = ToJson.toJson(operate);
		return json;
	}
	
	/**
	 * 删除用户的权限，在用户删除时
	 * @author purple
	 * @method deletePower
	 * @see (一句话描述方法的作用)
	 * @param no
	 * @retrun boolean
	 * @date 2018年6月17日
	 */
	public static boolean deletePower(String no) {
		String sql = "delete from powerinfo where no = ?";
		try {
			ps = dataBase.getPreparedStatement(sql);
			ps.setString(1, no);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean updatePower(String no,String m001, String m002, String m003, String m004, String m005, String m006, String m007
			,String m008, String input, String modify, String auditing, String auditing2, String find, String del, String n) {
		List<String> visible = new ArrayList<String>();
		List<String> menu2 = new ArrayList<String>();
		List<String> menu1 = new ArrayList<String>();
		visible.add(m001);
		visible.add(m002);
		visible.add(m003);
		visible.add(m004);
		visible.add(m005);
		visible.add(m006);
		visible.add(m007);
		visible.add(m008);
		//权限更新
		String sql = "update powerinfo set input=?,modify=?,auditing=?,auditing2=?,find=?,del=? where no=?";
		try {
			ps = dataBase.getPreparedStatement(sql);
			ps.setString(1, input);
			ps.setString(2, modify);
			ps.setString(3, auditing);
			ps.setString(4, auditing2);
			ps.setString(5, find);
			ps.setString(6, del);
			ps.setString(7, no);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		//获取所有1级菜单
		String m1no = "select distinct m1no from menu2info";
		try {
			ps = dataBase.getPreparedStatement(m1no);
			rs = ps.executeQuery();
			while(rs.next()) {
				menu1.add(rs.getString("m1no"));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		String m2no = "select no from menu2info where m1no=?";
		//更新对应二级菜单是否可见
		for(int i=0; i<visible.size(); i++) {
			menu2.clear();
			try {
				ps = dataBase.getPreparedStatement(m2no);
				ps.setString(1, menu1.get(i));
				rs = ps.executeQuery();
				while(rs.next()) {
					menu2.add(rs.getString("no"));
				}
				rs.close();
				ps.close();
				for(int j=0; j<menu2.size(); j++) {
					String vb = "update powerinfo set visible=? where no =? and m2no=?";
					ps = dataBase.getPreparedStatement(vb);
					ps.setString(1, visible.get(i));
					ps.setString(2, no);
					ps.setString(3, menu2.get(j));
					ps.execute();
					ps.close();
					OpLogInfoTools.insertOpLogInfo(n, "员工管理=>用户权限=>更新操作: 员工编号"+no);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
}
