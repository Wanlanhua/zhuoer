package com.zhuoer.careDevice.servlet.updateCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.UpdataCareDeviceInformation;
import com.zhuoer.careDevice.entity.ElectricityRunLInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class UpdateECRLI
 */
@WebServlet("/careDevice/UpdateERLI")
public class UpdateERLI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateERLI() {
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
		String EDRoom = request.getParameter("EDRoom");
		String current1 = request.getParameter("current1");
		String electricity1 = request.getParameter("electricity1");
		String current2 = request.getParameter("current2");
		String electricity2 = request.getParameter("electricity2");
		String current3 = request.getParameter("current3");
		String electricity3 = request.getParameter("electricity3");
		String current4 = request.getParameter("current4");
		String electricity4 = request.getParameter("electricity4");
		String current5 = request.getParameter("current5");
		String electricity5 = request.getParameter("electricity5");
		String current6 = request.getParameter("current6");
		String electricity6 = request.getParameter("electricity6");
		String current7 = request.getParameter("current7");
		String electricity7 = request.getParameter("electricity7");
		String current8 = request.getParameter("current8");
		String electricity8 = request.getParameter("electricity8");
		String current9 = request.getParameter("current9");
		String electricity9 = request.getParameter("electricity9");
		String current10 = request.getParameter("current10");
		String electricity10 = request.getParameter("electricity10");
		String recorder = request.getParameter("recorder");
		String watch = request.getParameter("watch");
		String person = request.getParameter("person");
		String mark = request.getParameter("mark");
		String ratio = request.getParameter("ratio");
		
		ElectricityRunLInfo erli = new ElectricityRunLInfo();
		erli.setId(Integer.parseInt(id));
		erli.setCustomerNo(customerNo);
		erli.setMaintenanceDate(TimeUtilToSql.getStringUtilDate(maintenanceDate));
		erli.setEDRoom(EDRoom);
		erli.setCurrent1(current1);
		erli.setElectricity1(electricity1);
		erli.setCurrent2(current2);
		erli.setElectricity2(electricity2);
		erli.setCurrent3(current3);
		erli.setElectricity3(electricity3);
		erli.setCurrent4(current4);
		erli.setElectricity4(electricity4);
		erli.setCurrent5(current5);
		erli.setElectricity5(electricity5);
		erli.setCurrent6(current6);
		erli.setElectricity6(electricity6);
		erli.setCurrent7(current7);
		erli.setElectricity7(electricity7);
		erli.setCurrent8(current8);
		erli.setElectricity8(electricity8);
		erli.setCurrent9(current9);
		erli.setElectricity9(electricity9);
		erli.setCurrent10(current10);
		erli.setElectricity10(electricity10);
		erli.setRecorder(recorder);
		erli.setWatch(watch);
		erli.setPerson(person);
		erli.setMark(mark);
		erli.setRatio(ratio);
		UpdataCareDeviceInformation ucdi = new UpdataCareDeviceInformation();
		ucdi.updateElectricityRunLInfo(erli, request.getSession().getAttribute("name").toString());
		String qname = request.getSession().getAttribute("queryERLI").toString();
		request.getRequestDispatcher("/careDevice/GetERLI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
