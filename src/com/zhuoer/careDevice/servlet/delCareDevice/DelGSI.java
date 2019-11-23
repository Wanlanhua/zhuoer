package com.zhuoer.careDevice.servlet.delCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.DeleteCareDeviceInfomation;

/**
 * Servlet implementation class DelGSI
 */
@WebServlet("/careDevice/DelGSI")
public class DelGSI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelGSI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		DeleteCareDeviceInfomation dcdi = new DeleteCareDeviceInfomation();
		dcdi.deleteGasStationInfo(id, request.getSession().getAttribute("name").toString());
		String qname = request.getSession().getAttribute("queryGSI").toString();
		request.getRequestDispatcher("/careDevice/GetGSI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
