package com.zhuoer.employeeAndCustomerInfo.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.zhuoer.DataBaseAccess.DataBaseAccess;


public class PowerMenuDao {

	/**
	 * 传递员工编号，返回员工可以看到的菜单
	 * @author purple
	 * @method getMenu
	 * @param id
	 * @return 封装好的菜单
	 * @throws Exception
	 * @retrun Map<String,ArrayList<LinkedHashMap<String,String>>>
	 * @date 2018年6月15日
	 */
	public static Map<String,ArrayList<LinkedHashMap<String,String>>> getMenu(String id) throws Exception {
		
		//创建菜单集合，Map<一级菜单名称，二级菜单List<二级菜单集合<二级菜单名称，二级菜单链接>>>
		Map<String,ArrayList<LinkedHashMap<String,String>>> menu = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		//获得菜单1的名称和编号
		String getMenu1 = "select name,id,no from menu1info where no in(select m1no from menu2info m2,powerinfo p where p.no=? and p.visible=1 and p.m2no=m2.no)";
		 
		//获取菜单2名字，编号，链接
		String getMenu2 = "select m.name,m.no,m.href from menu2info m,powerinfo p where m.no=p.m2no and p.no=? and p.visible=1";
		
		//执行sql语句，得到一级菜单信息
		ps = new DataBaseAccess().getPreparedStatement(getMenu1);
		ps.setString(1, id);
		rs = ps.executeQuery();
		
		//一级菜单集合
		Map<String, String> menu1 = new LinkedHashMap<String, String>();
		while(rs.next()) {
			String name = rs.getString("name");
			String no = rs.getString("no");
			menu1.put(no, name);
		}
		rs.close();
		ps.close();
		
		//执行sql语句，得到二级菜单信息
		ps = new DataBaseAccess().getPreparedStatement(getMenu2);
		ps.setString(1, id);
		rs = ps.executeQuery();
		
		//创建二级菜单集合
		Map<String, LinkedHashMap<String,String>> menu2 = new LinkedHashMap<String, LinkedHashMap<String,String>>();
		while(rs.next()) {
			String name = rs.getString("name");
			String no = rs.getString("no");
			String href = rs.getString("href");
			//封装二级菜单的名称和链接
			LinkedHashMap<String,String> menu2NH = new LinkedHashMap<String,String>();
			menu2NH.put(name, href);
			//将二级菜单名称和链接的集合，继续和名称进行封装
			menu2.put(no, menu2NH);
		}
		rs.close();
		ps.close();
		//得到一级菜单编号的数组
		Object[] menu1No = menu1.keySet().toArray();
		
		//遍历menu1的编号
		for(int i=0; i<menu1No.length; i++) {
			//得到二级菜单编号集合
			Set<String> mSet = menu2.keySet();
			//得到二级菜单编号的迭代器
			Iterator<String> iterator = mSet.iterator();
			//创建二级菜单集合，准备进行二级菜单和一级菜单的对应
			ArrayList<LinkedHashMap<String,String>> list = new ArrayList<LinkedHashMap<String, String>>();
			//遍历二级菜单名字
			while(iterator.hasNext()) {
				//遍历menu2的编号,先得到menu2编号
				String no = iterator.next();
				/**
				 * 二级菜单和一级菜单前四位相同，进行拆分匹配
				 */
				String key = no.substring(0, 4);
				//判断拆分得到的编号是否为当前一级菜单下的二级菜单，如果是放入到一个list集合中，进行对应二级菜单的封装
				if(menu1No[i].equals(key)) {
					//如果相同，将二级菜单封装到一个list集合中，list集合放一个LinkedHashMap<name,href>
					list.add(menu2.get(no));
				}
			}
			//将封装好的二级菜单继续和一级菜单名字封装成一个HashMap
			menu.put(menu1.get(menu1No[i]),list);
		}
		
		return menu;
	}
	
	/**
	 * 从数据查询，到菜单封装以及解析共5毫秒
	 * @author purple
	 * @method TestCaseMenu
	 * @see (一句话描述方法的作用)
	 * @throws Exception
	 * @retrun void
	 * @date 2018年6月15日
	 */
	@Test
	public void TestCaseMenu() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zhuoer_database", "root", "g,2?x!8;y");
		String getMenu1 = "select name,id,no from menu1info where no in(select m1no from menu2info m2,powerinfo p where p.no=? and p.visible=1 and p.m2no=m2.no)";
		String getMenu2 = "select m.name,m.no,m.href from menu2info m,powerinfo p where m.no=p.m2no and p.no=? and p.visible=1";
		PreparedStatement ps = conn.prepareStatement(getMenu1);
		ps.setString(1, "admin");
		ResultSet rs = ps.executeQuery();
		
		Map<String, String> menu1 = new LinkedHashMap<String, String>();
		
		while(rs.next()) {
			String name = rs.getString("name");
			String no = rs.getString("no");
			menu1.put(no, name);
		}
		rs.close();
		ps.close();
		
		ps = conn.prepareStatement(getMenu2);
		ps.setString(1, "admin");
		rs = ps.executeQuery();
		
		Map<String, LinkedHashMap<String,String>> menu2 = new LinkedHashMap<String, LinkedHashMap<String,String>>();
		while(rs.next()) {
			String name = rs.getString("name");
			String no = rs.getString("no");
			String href = rs.getString("href");
			LinkedHashMap<String,String> menu2NH = new LinkedHashMap<String,String>();
			menu2NH.put(name, href);
			menu2.put(no, menu2NH);
		}
		
		Object[] menu1No = menu1.keySet().toArray();
		Map<String,ArrayList<LinkedHashMap<String,String>>> menu = new LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>>();
		long begin = System.currentTimeMillis();
		//遍历menu1的编号
		for(int i=0; i<menu1No.length; i++) {
			Set<String> mSet = menu2.keySet();
			Iterator<String> iterator = mSet.iterator();
			ArrayList<LinkedHashMap<String,String>> list = new ArrayList<LinkedHashMap<String, String>>();
			while(iterator.hasNext()) {
				//遍历menu2的编号,先得到menu2编号
				String no = iterator.next();
				//将menu2编号，进行拆分，去和menu1匹配。匹配成功进行封装
				String key = no.substring(0, 4);
				//判断拆分后的二级菜单前4位是否和1级菜单相同
				if(menu1No[i].equals(key)) {
					//如果相同，将二级菜单封装到一个list集合中，list集合放一个LinkedHashMap<name,href>
					list.add(menu2.get(no));
				}
			}
			//将封装好的二级菜单继续和一级菜单名字封装成一个HashMap
			menu.put(menu1.get(menu1No[i]),list);
		}
		printMenu(menu);
	}
	
	/**
	 * 解析整个菜单
	 * @author purple
	 * @method printMenu
	 * @param menu
	 * @retrun void
	 * @date 2018年6月15日
	 */
	public void printMenu(Map<String,ArrayList<LinkedHashMap<String,String>>> menu) {
		Iterator<String> iterator = menu.keySet().iterator();
		while(iterator.hasNext()) {
			//获得一级菜单名字
			String name = iterator.next().toString();
			System.out.println("一级菜单名字"+name); 
			//获得一级菜单对应的二级菜单
			//html
			ArrayList<LinkedHashMap<String, String>> list = menu.get(name);
			//遍历二级菜单
			for(int i=0; i<list.size(); i++) {
				//得到一级菜单对应的二级菜单集合
				LinkedHashMap<String, String> map = list.get(i);
				//得到二级菜单名字的数组
				Object[] array = map.keySet().toArray();
				for(Object obj : array) {
					//得到二级菜单名字
					String mname = obj.toString();
					//得到二级菜单链接
					String href = map.get(mname);
					System.out.println(name);
				}
			}
		}
	}
}
