package com.zhuoer.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	
    	
        //在过滤器中检查是否已经登录
        HttpServletRequest req=(HttpServletRequest) request;
        
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*"); 
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        Object name = req.getSession().getAttribute("name");
        if(name==null && !req.getRequestURI().endsWith("login.jsp") &&
        		!req.getRequestURI().endsWith("UserLoginServlet") &&
        		!req.getRequestURI().endsWith("CRUDMaintance") &&
        		!req.getRequestURI().endsWith("MaintanceFrush") &&
        		!req.getRequestURI().endsWith("MaintanceRecord") &&
        		!req.getRequestURI().endsWith("QBaiduSend") &&
        		!req.getRequestURI().endsWith("QCRUDRepairInfo") &&
        		!req.getRequestURI().endsWith("QCRUDRepairRecords") &&
        		!req.getRequestURI().endsWith("QRepairInfo") &&
        		!req.getRequestURI().endsWith("QRepairInfoFrush") &&
        		!req.getRequestURI().endsWith("QRepairRecordsFrush") &&
        		!req.getRequestURI().endsWith("QRepairRecordsInfo") &&
        		!req.getRequestURI().endsWith("QSelectById") &&
        		!req.getRequestURI().endsWith("SelectNameById") &&
        		!req.getRequestURI().endsWith("PMaintenance") &&
        		!req.getRequestURI().endsWith("PMaintenanceQ") &&
        		!req.getRequestURI().endsWith("PMaintenanceS") &&
        		!req.getRequestURI().endsWith("PMaintenanceU") &&
        		!req.getRequestURI().endsWith("PMaintenanceW") &&
        		!req.getRequestURI().endsWith("PMaintenanceX") &&
        		!req.getRequestURI().endsWith("PMaintenanceT") &&
        		!req.getRequestURI().endsWith("PRepairInfo") &&
        		!req.getRequestURI().endsWith("PRepairrecordsInfoQ") &&
        		!req.getRequestURI().endsWith("PRepairrecordsInfoU") &&
        		!req.getRequestURI().endsWith("PRepairrecordsInfoW") &&
        		!req.getRequestURI().endsWith("PRepairrecordsInfoWW") &&
        		!req.getRequestURI().endsWith("PRepairrecordsInfoX") &&
        		!req.getRequestURI().endsWith("PRepairrecordsInfoT") &&
        		!req.getRequestURI().endsWith("PSystemUpdate") &&
        		!req.getRequestURI().endsWith("QLoginServlet") &&
        		!req.getRequestURI().endsWith("Pdepartname") &&
        		!req.getRequestURI().endsWith("QPhotoServlet") &&
        		!req.getRequestURI().endsWith("QPushAndUserInfo") &&
        		!req.getRequestURI().endsWith("QPushInfo") &&
        		!req.getRequestURI().endsWith("QSelectArea") &&
        		!req.getRequestURI().endsWith("PBaiduPush") &&
        		!req.getRequestURI().endsWith("GetDepartmentAndArea") &&
        		!req.getRequestURI().endsWith("GetArea") &&
        		!req.getRequestURI().endsWith("GetShebei") &&
        		!req.getRequestURI().endsWith("GetEmployee") &&
        		!req.getRequestURI().endsWith("GetSaveDBCZJLB") &&
        		!req.getRequestURI().endsWith("GetCareDevice") &&
        		!req.getRequestURI().endsWith("InsertDBCZJLB") &&
        		!req.getRequestURI().endsWith("SHDBCZJLB") &&
        		!req.getRequestURI().endsWith("GetJQITI") &&
        		!req.getRequestURI().endsWith("AddBarcodeImage") &&
        		!req.getRequestURI().endsWith("GetMark") &&
        		!req.getServletPath().endsWith(".css") && 
        		!req.getServletPath().endsWith(".js") &&
        		!req.getServletPath().endsWith(".png") &&
        		!req.getServletPath().endsWith(".jpg") &&
        		!req.getServletPath().endsWith(".apk") &&
        		!(req.getServletPath().lastIndexOf(".")==0) &&
        		!(req.getRequestURI().indexOf("Get")!=-1) &&
        		!(req.getRequestURI().indexOf("Update")!=-1) &&
        		!(req.getRequestURI().indexOf("Insert")!=-1) &&
        		!(req.getRequestURI().indexOf("Delete")!=-1) &&
        		!(req.getRequestURI().indexOf("SH")!=-1)
        ){
        	PrintWriter pw = res.getWriter();
        	pw.println("<script type=\"text/javascript\">" + 
        			"		window.top.location='"+req.getContextPath()+"/login.jsp'" + 
        			"	</script>");
        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }

}