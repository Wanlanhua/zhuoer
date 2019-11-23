package com.zhuoer.careDevice.servlet.updateCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.UpdataCareDeviceInformation;
import com.zhuoer.careDevice.entity.AirPressureStationInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class UpdateAPSI
 */
@WebServlet("/careDevice/UpdateAPSI")
public class UpdateAPSI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAPSI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
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
		aspi.setId(Integer.parseInt(id));
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
		UpdataCareDeviceInformation ucdi = new UpdataCareDeviceInformation();
		ucdi.updateAirPressureStationInfo(aspi, request.getSession().getAttribute("name").toString());
		
		String qname = request.getSession().getAttribute("queryAPSI").toString();
		request.getRequestDispatcher("/careDevice/GetAPSI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
