package com.zhuoer.careDevice.servlet.insertCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.InsertCareDeviceInformation;
import com.zhuoer.careDevice.entity.ElectricityRunHInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class InsertECRHI
 */
@WebServlet("/careDevice/InsertERHI")
public class InsertERHI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertERHI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerNo = request.getParameter("customerNo");
		String maintenanceDate = request.getParameter("maintenanceDate");
		String EDRoom = request.getParameter("EDRoom");
		String indoorTemperature = request.getParameter("indoorTemperature");
		String indoorHumidity = request.getParameter("indoorHumidity");
		String totalNumber = request.getParameter("totalNumber");
		String totalElectricity = request.getParameter("totalElectricity");
		String demandNumber = request.getParameter("demandNumber");
		String demandElectricity = request.getParameter("demandElectricity");
		String peakValue = request.getParameter("peakValue");
		String peakValueElectricity = request.getParameter("peakValueElectricity");
		String fairValue = request.getParameter("fairValue");
		String fairValueElectricity = request.getParameter("fairValueElectricity");
		String Valley = request.getParameter("Valley");
		String ValleyElectricity = request.getParameter("ValleyElectricity");
		String varmeter1 = request.getParameter("varmeter1");
		String varmeter1E = request.getParameter("varmeter1E");
		String varmeter2 = request.getParameter("varmeter2");
		String varmeter2E = request.getParameter("varmeter2E");
		String highVoltage = request.getParameter("highVoltage");
		String highVoltageE = request.getParameter("highVoltageE");
		String transformer1T = request.getParameter("transformer1T");
		String transformer1E = request.getParameter("transformer1E");
		String transformer1V = request.getParameter("transformer1V");
		String transformer1Power = request.getParameter("transformer1Power");
		String transformer2T = request.getParameter("transformer2T");
		String transformer2E = request.getParameter("transformer2E");
		String transformer2V = request.getParameter("transformer2V");
		String transformer2Power = request.getParameter("transformer2Power");
		String ratio = request.getParameter("ratio");
		String  recorder = request.getParameter("recorder");
		String watch = request.getParameter("watch");
		String person = request.getParameter("person");
		String mark = request.getParameter("mark");
		
		ElectricityRunHInfo erhi = new ElectricityRunHInfo();
		erhi.setCustomerNo(customerNo);
		erhi.setMaintenanceDate(TimeUtilToSql.getStringUtilDate(maintenanceDate));
		erhi.setEDRoom(EDRoom);
		erhi.setIndoorTemperature(indoorTemperature);
		erhi.setIndoorHumidity(indoorHumidity);
		erhi.setTotalNumber(totalNumber);
		erhi.setTotalElectricity(totalElectricity);
		erhi.setDemandNumber(demandNumber);
		erhi.setDemandElectricity(demandElectricity);
		erhi.setPeakValue(peakValue);
		erhi.setPeakValueElectricity(peakValueElectricity);
		erhi.setFairValue(fairValue);
		erhi.setFairValueElectricity(fairValueElectricity);
		erhi.setValley(Valley);
		erhi.setValleyElectricity(ValleyElectricity);
		erhi.setVarmeter1(varmeter1);
		erhi.setVarmeter1E(varmeter1E);
		erhi.setVarmeter2(varmeter2);
		erhi.setVarmeter2E(varmeter2E);
		erhi.setHighVoltage(highVoltage);
		erhi.setHighVoltageE(highVoltageE);
		erhi.setTransformer1T(transformer1T);
		erhi.setTransformer1E(transformer1E);
		erhi.setTransformer1V(transformer1V);
		erhi.setTransformer1Power(transformer1Power);
		erhi.setRatio(ratio);
		erhi.setTransformer2T(transformer2T);
		erhi.setTransformer2E(transformer2E);
		erhi.setTransformer2V(transformer2V);
		erhi.setTransformer2Power(transformer2Power);
		erhi.setRecorder(recorder);
		erhi.setWatch(watch);
		erhi.setPerson(person);
		erhi.setMark(mark);
		InsertCareDeviceInformation icdi = new InsertCareDeviceInformation();
		icdi.insertElectricityRunHInfo(erhi,request.getSession().getAttribute("name").toString());
		
		Object qnameObject = request.getSession().getAttribute("queryERHI");
		String qname = "";
		if(qnameObject!=null) {
			qname = qnameObject.toString();
		}
		request.getSession().setAttribute("thisPageERHI", 1);
		request.getRequestDispatcher("/careDevice/GetERHI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
