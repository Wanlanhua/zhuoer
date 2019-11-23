package com.zhuoer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhuoer.qmaintance.utils.DBTools;
import com.zhuoer.util.util;
public class Tools {
	
	
	
	
	public static ResultSet executeQuary(String sql,Object...objects)
	{
		
		ResultSet rs = null;
		Connection conn=util.getconn();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			if(objects!=null)
			{
				for(int i=0;i<objects.length;i++)
				{
					ps.setObject(i+1, objects[i]);
					
				}
			}
			 rs= ps.executeQuery();
	    return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
      
		return null;	
	}
	
	
	public static int executeUpdate(String sql,Object...objects)
	{
		int s = -1;
		//Connection conn=util.getconn();
		
		 PreparedStatement ps=null;
		 try {
			 ps=DBTools.getConnection().prepareStatement(sql);
			 
			 if(objects!=null)
				 for(int i=0;i<objects.length;i++)
				 {
					 ps.setObject(i+1, objects[i]);
					 
				 }
			 
			 s= ps.executeUpdate();
			 ps.close();
			 //return s;
		 	} catch (SQLException e) {
		 			e.printStackTrace();
		 	}
		    finally {
				try {
					if (ps!=null) {
						ps.close();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 return s;
	}
	
}

