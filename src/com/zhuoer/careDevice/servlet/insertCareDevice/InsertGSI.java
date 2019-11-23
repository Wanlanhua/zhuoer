package com.zhuoer.careDevice.servlet.insertCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.InsertCareDeviceInformation;
import com.zhuoer.careDevice.entity.GasStationInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class InsertGSI
 */
@WebServlet("/careDevice/InsertGSI")
public class InsertGSI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGSI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerNo = request.getParameter("customerNo");
		String maintenanceDate = request.getParameter("maintenanceDate");
		String oxygen = request.getParameter("oxygen");
		String acetylene = request.getParameter("acetylene");
		String argon = request.getParameter("argon");
		String valve = request.getParameter("valve");
		String station = request.getParameter("station");
		String conduit = request.getParameter("conduit");
		String content = request.getParameter("content");
		String maintenancePerson = request.getParameter("maintenancePerson");
		String mark = request.getParameter("mark");
		
		GasStationInfo gsi = new GasStationInfo();
		gsi.setCustomerNo(customerNo);
		gsi.setMaintenanceDate(TimeUtilToSql.getStringUtilDate(maintenanceDate));
		gsi.setOxygen(oxygen);
		gsi.setAcetylene(acetylene);
		gsi.setArgon(argon);
		gsi.setValve(valve);
		gsi.setStation(station);
		gsi.setConduit(conduit);
		gsi.setContent(content);
		gsi.setMaintenancePerson(maintenancePerson);
		gsi.setMark(mark);
		InsertCareDeviceInformation icdi = new InsertCareDeviceInformation();
		icdi.insertGasStationInfo(gsi,request.getSession().getAttribute("name").toString());
		
		Object qnameObject = request.getSession().getAttribute("queryGSI");
		String qname = "";
		if(qnameObject!=null) {
			qname = qnameObject.toString();
		}
		request.getSession().setAttribute("thisPageGSI", 1);
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
