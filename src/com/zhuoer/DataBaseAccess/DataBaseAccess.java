package com.zhuoer.DataBaseAccess;
import java.sql.*;

public class DataBaseAccess
{
	private static String DBDriver="com.mysql.jdbc.Driver";
    private static String DBUrl="jdbc:mysql://localhost:3306/zhuoer_database?zeroDateTimeBehavior=round";
    private static String DBUser="root";
//    private static String DBPassword="123456";
    private static String DBPassword="3183328947";
    
    public static Connection conn=null;
    public static ResultSet rs=null;
    public static PreparedStatement ps;
    
    static {
    	try {
			Class.forName(DBDriver);
			conn=DriverManager.getConnection(DBUrl,DBUser,DBPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public ResultSet query(String sql)throws Exception
    {
    	isConnection();
        try{
            Statement stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            return rs;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public int  update(String sql)throws Exception
    {
    	isConnection();
  	  int count=0;
  	  try{
            Statement stmt=conn.createStatement();
            count=stmt.executeUpdate(sql);
            stmt.close();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
  	  return count;
    }
    
    public PreparedStatement getPreparedStatement(String sql) throws Exception {
    	isConnection();
      	return conn.prepareStatement(sql);
    }
    
    public void isConnection() {
    	try {
			if(conn == null || conn.isClosed()) {
				conn=DriverManager.getConnection(DBUrl,DBUser,DBPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}