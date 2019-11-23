package com.zhuoer.careDevice.servlet.getCareDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.GetcareDeviceInformation;
import com.zhuoer.careDevice.entity.DailyFlowInfo;
import com.zhuoer.careDevice.util.GetPageSize;

/**
 * Servlet implementation class GetDFI
 */
@WebServlet("/careDevice/GetDFI")
public class GetDFI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDFI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qname = request.getParameter("qname");
		String pageDFI = request.getParameter("page");
		int page = 1;
		if(pageDFI == null || pageDFI.isEmpty()) {
			if(request.getSession().getAttribute("thisPageDFI") == null) {
				request.getSession().setAttribute("thisPageDFI", 1);
			}
			else {
				page = Integer.parseInt(request.getSession().getAttribute("thisPageDFI").toString());
			}
		}
		else {
			page = Integer.parseInt(pageDFI);
		}
		List<DailyFlowInfo> list = null;
		if(qname == null && request.getSession().getAttribute("queryDFI")!=null) {
			qname = request.getSession().getAttribute("queryDFI").toString();
		}
		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());
		String name = request.getSession().getAttribute("name").toString();
		list = GetcareDeviceInformation.getDailyFlowInfo(name, qname, role, page);
		
		request.getSession().setAttribute("queryDFI", qname);
		request.getSession().setAttribute("listDFI", list);
		request.getSession().setAttribute("thisPageDFI", page);
		request.getSession().setAttribute("pageSizeDFI", GetPageSize.getPageSize("dailyflowinfo", role, name, qname));
		response.sendRedirect(request.getContextPath()+"/dailyflowinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
