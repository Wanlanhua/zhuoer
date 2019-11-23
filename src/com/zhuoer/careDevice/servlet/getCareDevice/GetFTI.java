package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.FlushTankInfo;
import com.zhuoer.careDevice.util.GetPageSize;

/**
 * Servlet implementation class GetFTIServlet
 */
@WebServlet("/careDevice/GetFTI")
public class GetFTI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFTI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qname = request.getParameter("qname");
		String pageFTI = request.getParameter("page");
		int page = 1;
		if(pageFTI == null || pageFTI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageFTI") == null) {
				request.getSession().setAttribute("thisPageFTI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageFTI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageFTI);
		}
		List<FlushTankInfo> list = null;
		if(qname == null) {
			qname = request.getSession().getAttribute("queryFTI").toString();
		}
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list = GetcareDeviceInformation.getFlushTankInfo(name, qname, role, page);
		
		request.getSession().setAttribute("queryFTI", qname);
		request.getSession().setAttribute("listFTI", list);
		request.getSession().setAttribute("thisPageFTI", page);
		request.getSession().setAttribute("pageSizeFTI", GetPageSize.getPageSize("flushtankinfo", role, name, qname));
		response.sendRedirect(request.getContextPath()+"/flushtankinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
