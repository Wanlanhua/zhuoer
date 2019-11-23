package com.zhuoer.userLogin;
import java.sql.*;

import com.zhuoer.DataBaseAccess.DataBaseAccess;
public class UserLoginDao 
{
	
	public static UserLoginBean isUser(String name,String password)
	{
		ResultSet rs=null;
		UserLoginBean ulb=new UserLoginBean();
		String sql="select * from logininfo where name= ?";
		try
		{
			PreparedStatement ps = new DataBaseAccess().getPreparedStatement(sql);
			ps.setString(1,name );
			rs=ps.executeQuery();
			if(!rs.next())
			{
				rs.close();
				ps.close();
				return null;	
			}
			else
			{
				ulb.setUserName(rs.getString("name"));
				ulb.setUserPassword(rs.getString("password"));
				ulb.setUserRole(rs.getString("role"));
				rs.close();
				ps.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return ulb;
	}
	
}
