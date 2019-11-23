package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.ElectricityRunLInfo;
import com.zhuoer.careDevice.util.GetPageSize;

/**
 * Servlet implementation class GetECRLIServlet
 */
@WebServlet("/careDevice/GetERLI")
public class GetERLI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetERLI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qname = request.getParameter("qname");
		String pageERLI = request.getParameter("page");
		int page = 1;
		if(pageERLI == null || pageERLI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageERLI") == null) {
				request.getSession().setAttribute("thisPageERLI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageERLI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageERLI);
		}
		List<ElectricityRunLInfo> list = null;
		if(qname == null) {
			qname = request.getSession().getAttribute("queryERLI").toString();
		}
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list = GetcareDeviceInformation.getElectricityRunLInfo(name, qname, role, page);
		
		request.getSession().setAttribute("queryERLI", qname);
		request.getSession().setAttribute("listERLI", list);
		request.getSession().setAttribute("thisPageERLI", page);
		request.getSession().setAttribute("pageSizeERLI", GetPageSize.getPageSize("electricityrunlinfo", role, name, qname));
		response.sendRedirect(request.getContextPath()+"/electricityrunlinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
