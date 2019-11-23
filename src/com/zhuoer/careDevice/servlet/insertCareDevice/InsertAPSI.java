package com.zhuoer.careDevice.servlet.insertCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.InsertCareDeviceInformation;
import com.zhuoer.careDevice.entity.AirPressureStationInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class InsertAPSI
 */
@WebServlet("/careDevice/InsertAPSI")
public class InsertAPSI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAPSI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String customerNo = request.getParameter("customerNo");
		String maintenanceDate = request.getParameter("maintenanceDate");
		String situation = request.getParameter("situation");
		String pressure = request.getParameter("pressure");
		String lube = request.getParameter("lube");
		String coolingWater = request.getParameter("coolingWater");
		String station = request.getParameter("station");
		String oilLevel = request.getParameter("oilLevel");
		String content = request.getParameter("content");
		String maintenancePerson = request.getParameter("maintenancePerson");
		String mark = request.getParameter("mark");
		
		AirPressureStationInfo aspi = new AirPressureStationInfo();
		aspi.setCustomerNo(customerNo);
		aspi.setMaintenanceDate(TimeUtilToSql.getStringUtilDate(maintenanceDate));
		aspi.setSituation(situation);
		aspi.setPressure(pressure);
		aspi.setLube(lube);
		aspi.setCoolingWater(coolingWater);
		aspi.setStation(station);
		aspi.setOiLevel(oilLevel);
		aspi.setContent(content);
		aspi.setMaintenancePerson(maintenancePerson);
		aspi.setMark(mark);
		InsertCareDeviceInformation icdi = new InsertCareDeviceInformation();
		icdi.insertAirPressureStationInfo(aspi,request.getSession().getAttribute("name").toString());
		Object qnameObject = request.getSession().getAttribute("queryAPSI");
		String qname = "";
		if(qnameObject!=null) {
			qname = qnameObject.toString();
		}
		request.getSession().setAttribute("thisPageAPSI", 1);
		request.getRequestDispatcher("/careDevice/GetAPSI?qname="+qname).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
