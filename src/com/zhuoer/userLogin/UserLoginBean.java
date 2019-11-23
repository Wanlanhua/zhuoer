package com.zhuoer.userLogin;

import java.sql.Timestamp;

public class UserLoginBean 
{
	private String userName; //�û���¼��
	private String userPassword;//�û���¼����
	private String userRole;//�û�Ȩ��
	private Timestamp lastLogin;//���һ�ε�¼ʱ��
	private Timestamp stamp;
	private String userMark;
	
	public UserLoginBean()
	{
		this.userName="";
		this.userPassword="";
		this.userRole="";
		
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserPassword() 
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword) 
	{
		this.userPassword = userPassword;
	}

	public String getUserRole() 
	{
		return userRole;
	}

	public void setUserRole(String userRole) 
	{
		this.userRole = userRole;
	}

	
	public String getUserMark() {
		return userMark;
	}

	public void setUserMark(String userMark) {
		this.userMark = userMark;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Timestamp getStamp() {
		return stamp;
	}

	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}
	
	
}
