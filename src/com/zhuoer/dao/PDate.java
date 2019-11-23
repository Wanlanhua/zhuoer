package com.zhuoer.dao;

import java.text.DecimalFormat;

public class PDate {

	public static String  sss(String Nyr)
	
	{	

	    String nn=Nyr.substring(0, Nyr.indexOf("-"));
		String mm=Nyr.substring(Nyr.indexOf("-")+1,Nyr.lastIndexOf("-"));
        String dd=Nyr.substring(Nyr.lastIndexOf("-")+1);  
      
      
		  if(mm.length()<=1)
		  {
			  DecimalFormat df=new DecimalFormat("00"); 
			     mm=df.format(Integer.parseInt(mm));      
		  }
		  if(dd.length()<=1)
		  {	
			  DecimalFormat df=new DecimalFormat("00"); 
		         dd=df.format(Integer.parseInt(dd)); 
		  }
 
		String Realdate=nn+"-"+mm+"-"+dd;
		return Realdate;

	}

}
