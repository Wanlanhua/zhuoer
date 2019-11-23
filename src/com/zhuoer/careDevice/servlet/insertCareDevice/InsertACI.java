package com.zhuoer.careDevice.servlet.insertCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.InsertCareDeviceInformation;
import com.zhuoer.careDevice.entity.AirCompressorInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class InsertACI
 */
@WebServlet("/careDevice/InsertACI")
public class InsertACI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertACI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		String customerNo = request.getParameter("customerNo");
		String maintenanceDate = request.getParameter("maintenanceDate");
		String exhaust = request.getParameter("exhaust");
		String system = request.getParameter("system");
		String coolingWater = request.getParameter("coolingWater");
		String lube = request.getParameter("lube");
		String exhaust1T = request.getParameter("exhaust1T");
		String exhaust2T = request.getParameter("exhaust2T");
		String systemT = request.getParameter("systemT");
		String ambientT = request.getParameter("ambientT");
		String lubeT = request.getParameter("lubeT");
		String coolingWaterT = request.getParameter("coolingWaterT");
		String frontBearingT = request.getParameter("frontBearingT");
		String rearBearingT = request.getParameter("rearBearingT");
		String operatingVoltage = request.getParameter("operatingVoltage");
		String currentIndication = request.getParameter("currentIndication");
		String runtime = request.getParameter("runtime");
		String loadTime = request.getParameter("loadTime");
		String recorder = request.getParameter("recorder");
		String person = request.getParameter("person");
		String mark = request.getParameter("mark");
		AirCompressorInfo aci = new AirCompressorInfo();
		aci.setCustomerNo(customerNo);
		aci.setMaintenanceDate(TimeUtilToSql.getStringUtilDate(maintenanceDate));
		aci.setExhaust(exhaust);;
		aci.setSystem(system);
		aci.setCoolingWater(coolingWater);
		aci.setLube(lube);
		aci.setExhaust1T(exhaust1T);
		aci.setExhaust2T(exhaust2T);
		aci.setSystemT(systemT);
		aci.setAmbientT(ambientT);
		aci.setLubeT(lubeT);
		aci.setCoolingWaterT(coolingWaterT);
		aci.setFrontBearingT(frontBearingT);
		aci.setRearBearingT(rearBearingT);
		aci.setOperatingVoltage(operatingVoltage);
		aci.setCurrentIndication(currentIndication);
		aci.setRuntime(runtime);
		aci.setLoadTime(loadTime);
		aci.setRecorder(recorder);
		aci.setPerson(person);
		aci.setMark(mark);
		InsertCareDeviceInformation icdi = new InsertCareDeviceInformation();
		icdi.insertAirCompressorInfo(aci,request.getSession().getAttribute("name").toString());
		request.getSession().setAttribute("thisPageACI", 1);
		String qname = "";
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
