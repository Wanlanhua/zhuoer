package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.GasStationInfo;
import com.zhuoer.careDevice.util.GetPageSize;

/**
 * Servlet implementation class GetGSIServlet
 */
@WebServlet("/careDevice/GetGSI")
public class GetGSI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGSI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qname = request.getParameter("qname");
		String pageGSI = request.getParameter("page");
		int page = 1;
		if(pageGSI == null || pageGSI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageGSI") == null) {
				request.getSession().setAttribute("thisPageGSI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageGSI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageGSI);
		}
		List<GasStationInfo> list = null;
		if(qname == null) {
			qname = request.getSession().getAttribute("queryGSI").toString();
		}
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list = GetcareDeviceInformation.getGasStationInfo(name, qname, role, page);
		
		request.getSession().setAttribute("queryGSI", qname);
		request.getSession().setAttribute("listGSI", list);
		request.getSession().setAttribute("thisPageGSI", page);
		request.getSession().setAttribute("pageSizeGSI", GetPageSize.getPageSize("gasstationinfo", role, name, qname));
		response.sendRedirect(request.getContextPath()+"/gasstationinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
