package com.zhuoer.careDevice.servlet.delCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.DeleteCareDeviceInfomation;

/**
 * Servlet implementation class DelACI
 */
@WebServlet("/careDevice/DelACI")
public class DelACI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelACI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		DeleteCareDeviceInfomation dcdi = new DeleteCareDeviceInfomation();
		dcdi.deleteAirCompressorInfo(id, request.getSession().getAttribute("name").toString());
		String qname = "";
		if(request.getSession().getAttribute("queryACI")!=null)
			qname = request.getSession().getAttribute("queryACI").toString();
		request.getRequestDispatcher("/careDevice/GetACI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
