package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.AirCompressorInfo;
import com.zhuoer.careDevice.util.GetPageSize;

@WebServlet("/careDevice/GetACI")
public class GetACI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetACI() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String qname = request.getParameter("qname");
		String pageAPSI = request.getParameter("page");
		int page = 1;
		if(pageAPSI == null || pageAPSI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageACI") == null) {
				request.getSession().setAttribute("thisPageACI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageACI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageAPSI);
		}
		List<AirCompressorInfo> list = null;
		if(qname == null && request.getSession().getAttribute("queryACI")!=null) {
			qname = request.getSession().getAttribute("queryACI").toString();
		}
		
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list = GetcareDeviceInformation.getAirCompressorInfo(name, qname, role, page);

		
		request.getSession().setAttribute("queryACI", qname);
		request.getSession().setAttribute("listACI", list);
		request.getSession().setAttribute("thisPageACI", page);
		request.getSession().setAttribute("pageSizeACI", GetPageSize.getPageSize("aircompressorinfo", role, name, qname));
		response.sendRedirect(request.getContextPath()+"/aircompressorinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
