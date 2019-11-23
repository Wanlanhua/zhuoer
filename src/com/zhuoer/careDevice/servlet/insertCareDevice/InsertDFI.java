package com.zhuoer.careDevice.servlet.insertCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.InsertCareDeviceInformation;
import com.zhuoer.careDevice.entity.DailyFlowInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class InsertDFI
 */
@WebServlet("/careDevice/InsertDFI")
public class InsertDFI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDFI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerNo = request.getParameter("customerNo");
		String address = request.getParameter("address");
		String oxygenReading = request.getParameter("oxygenReading");
		String oxygenConsumption = request.getParameter("oxygenConsumption");
		String oxygenPressure = request.getParameter("oxygenPressure");
		String propaneReading = request.getParameter("propaneReading");
		String propaneConsumption = request.getParameter("propaneConsumption");
		String propanePressure = request.getParameter("propanePressure");
		String dioxideReading = request.getParameter("dioxideReading");
		String dioxideConsumption = request.getParameter("dioxideConsumption");
		String dioxidePressure = request.getParameter("dioxidePressure");
		String maintenanceDate = request.getParameter("maintenanceDate");
		String recorder = request.getParameter("recorder");
		String person = request.getParameter("person");
		String mark = request.getParameter("mark");
		DailyFlowInfo dfi = new DailyFlowInfo();
		dfi.setCustomerNo(customerNo);
		dfi.setAddress(address);
		dfi.setOxygenReading(oxygenReading);
		dfi.setOxygenPressure(oxygenPressure);
		dfi.setOxygenConsumption(oxygenConsumption);
		dfi.setPropaneReading(propaneReading);
		dfi.setPropanePressure(propanePressure);
		dfi.setPropaneConsumption(propaneConsumption);
		dfi.setDioxideReading(dioxideReading);
		dfi.setDioxideConsumption(dioxideConsumption);
		dfi.setDioxidePressure(dioxidePressure);
		dfi.setMaintenanceDate(TimeUtilToSql.getStringUtilDate(maintenanceDate));
		dfi.setRecorder(recorder);
		dfi.setPerson(person);
		dfi.setMark(mark);
		InsertCareDeviceInformation icdi = new InsertCareDeviceInformation();
		icdi.insertDailyFlowInfo(dfi,request.getSession().getAttribute("name").toString());
		Object qnameObject = request.getSession().getAttribute("queryDFI");
		String qname = "";
		if(qnameObject!=null) {
			qname = qnameObject.toString();
		}
		request.getSession().setAttribute("thisPageDFI", 1);
		request.getRequestDispatcher("/careDevice/GetDFI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
