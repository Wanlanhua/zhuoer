package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.ElectricityInfo;
import com.zhuoer.careDevice.util.GetPageSize;

/**
 * Servlet implementation class GetECIServlet
 */
@WebServlet("/careDevice/GetECI")
public class GetECI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetECI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qname = request.getParameter("qname");
		String pageECI = request.getParameter("page");
		int page = 1;
		if(pageECI == null || pageECI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageECI") == null) {
				request.getSession().setAttribute("thisPageECI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageECI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageECI);
		}
		List<ElectricityInfo> list = null;
		if(qname == null && request.getSession().getAttribute("queryECI")!=null) {
			qname = request.getSession().getAttribute("queryECI").toString();
		}
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list = GetcareDeviceInformation.getElectricityInfo(name, qname, role, page);
		
		request.getSession().setAttribute("queryECI", qname);
		request.getSession().setAttribute("listECI", list);
		request.getSession().setAttribute("thisPageECI", page);
		request.getSession().setAttribute("pageSizeECI", GetPageSize.getPageSize("electricityinfo", role, name, qname));
		response.sendRedirect(request.getContextPath()+"/electricityinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
