package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.TankInfo;
import com.zhuoer.careDevice.util.GetPageSize;

/**
 * Servlet implementation class GetTIServlet
 */
@WebServlet("/careDevice/GetTI")
public class GetTI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qname = request.getParameter("qname");
		String pageTI = request.getParameter("page");
		int page = 1;
		if(pageTI == null || pageTI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageTI") == null) {
				request.getSession().setAttribute("thisPageTI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageTI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageTI);
		}
		List<TankInfo> list = null;
		if(qname == null) {
			qname = request.getSession().getAttribute("queryTI").toString();
		}
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list = GetcareDeviceInformation.getTankInfo(name, qname, role, page);
		
		request.getSession().setAttribute("queryTI", qname);
		request.getSession().setAttribute("listTI", list);
		request.getSession().setAttribute("thisPageTI", page);
		request.getSession().setAttribute("pageSizeTI", GetPageSize.getPageSize("tankinfo", role, name, qname));
		response.sendRedirect(request.getContextPath()+"/tankinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
