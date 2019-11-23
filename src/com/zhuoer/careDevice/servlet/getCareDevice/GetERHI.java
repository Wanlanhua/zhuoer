package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.ElectricityRunHInfo;
import com.zhuoer.careDevice.util.GetPageSize;

/**
 * Servlet implementation class GetECRHIServlet
 */
@WebServlet("/careDevice/GetERHI")
public class GetERHI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetERHI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qname = request.getParameter("qname");
		String pageERHI = request.getParameter("page");
		int page = 1;
		if(pageERHI == null || pageERHI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageERHI") == null) {
				request.getSession().setAttribute("thisPageERHI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageERHI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageERHI);
		}
		List<ElectricityRunHInfo> list = null;
		if(qname == null) {
			qname = request.getSession().getAttribute("queryERHI").toString();
		}
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list = GetcareDeviceInformation.getElectricityRunHInfo(name, qname, role, page);
		
		request.getSession().setAttribute("queryERHI", qname);
		request.getSession().setAttribute("listERHI", list);
		request.getSession().setAttribute("thisPageERHI", page);
		request.getSession().setAttribute("pageSizeERHI", GetPageSize.getPageSize("electricityrunhinfo", role, name, qname));
		response.sendRedirect(request.getContextPath()+"/electricityrunhinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
