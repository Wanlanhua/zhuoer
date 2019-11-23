package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.AirPressureStationInfo;
import com.zhuoer.careDevice.util.GetPageSize;

/**
 * Servlet implementation class GetAPSIServlet
 */
@WebServlet("/careDevice/GetAPSI")
public class GetAPSI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAPSI() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qname = request.getParameter("qname");
		String pageAPSI = request.getParameter("page");
		int page = 1;
		if(pageAPSI == null || pageAPSI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageASPI") == null) {
				request.getSession().setAttribute("thisPageAPSI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageAPSI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageAPSI);
		}
		List<AirPressureStationInfo> list = null;
		if(qname == null && request.getSession().getAttribute("queryAPSI") !=null) {
			qname = request.getSession().getAttribute("queryAPSI").toString();
		}
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list =  GetcareDeviceInformation.getAirPressureStationInfo(name, qname, role, page);
		request.getSession().setAttribute("thisPageAPSI", page);
		request.getSession().setAttribute("pageSizeAPSI", GetPageSize.getPageSize("airpressurestationinfo", role, name, qname));
		request.getSession().setAttribute("queryAPSI", qname);
		request.getSession().setAttribute("listAPSI", list);
		response.sendRedirect(request.getContextPath()+"/airpressurestationinfo.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
