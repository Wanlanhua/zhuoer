package com.zhuoer.longinInfo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.util.GetPageSize;
import com.zhuoer.longinInfo.dao.LoginInfoSelect;
import com.zhuoer.longinInfo.entity.LoginInfo;

/**
 * Servlet implementation class GetLI
 */
@WebServlet("/employeeAndCustomerInfo/GetLI")
public class GetLI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "";
		int page = 1;
		if(request.getParameter("page") == null || request.getParameter("page").equals(""))
			page = 1;
		else
			page = Integer.parseInt(request.getParameter("page"));
		if(request.getParameter("qname")==null) {
			name = request.getSession().getAttribute("liqname").toString();
		} else {
			name = request.getParameter("qname");
		}
		List<LoginInfo> eaci = new LoginInfoSelect().select(name,page, request.getSession().getAttribute("name").toString());
		int pageSize = GetPageSize.getPageSize("logininfo");
		request.getSession().setAttribute("liqname", name);
		request.getSession().setAttribute("liThisPage", page);
		request.getSession().setAttribute("liPage", pageSize);
		request.getSession().setAttribute("liList", eaci);
		response.sendRedirect(request.getContextPath()+"/loginInfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
