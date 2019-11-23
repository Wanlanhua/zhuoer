package com.zhuoer.careDevice.servlet.updateCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.UpdataCareDeviceInformation;
import com.zhuoer.careDevice.entity.TankInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class UpdateTI
 */
@WebServlet("/careDevice/UpdateTI")
public class UpdateTI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTI() {
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
		String oxygenCode1 = request.getParameter("oxygenCode1");
		String oxygenWeight1 = request.getParameter("oxygenWeight1");
		String oxygenCode2 = request.getParameter("oxygenCode2");
		String oxygenWeight2 = request.getParameter("oxygenWeight2");
		String propaneCode1 = request.getParameter("propaneCode1");
		String propaneWeight1 = request.getParameter("propaneWeight1");
		String propaneCode2 = request.getParameter("propaneCode2");
		String propaneWeight2 = request.getParameter("propaneWeight2");
		String dioxideCode1 = request.getParameter("dioxideCode1");
		String dioxideWeight1 = request.getParameter("dioxideWeight1");
		String dioxideCode2 = request.getParameter("dioxideCode2");
		String dioxideWeight2 = request.getParameter("dioxideWeight2");
		String recorder = request.getParameter("recorder");
		String person = request.getParameter("person");
		String mark = request.getParameter("mark");
		
		TankInfo ti = new TankInfo();
		ti.setId(Integer.parseInt(id));
		ti.setCustomerNo(customerNo);
		ti.setMaintenanceDate(TimeUtilToSql.getStringUtilDate(maintenanceDate));
		ti.setOxygenCode1(oxygenCode1);
		ti.setOxygenWeight1(oxygenWeight1);
		ti.setOxygenCode2(oxygenCode2);
		ti.setOxygenWeight2(oxygenWeight2);
		ti.setPropaneCode1(propaneCode1);
		ti.setPropaneWeight1(propaneWeight1);
		ti.setPropaneCode2(propaneCode2);
		ti.setPropaneWeight2(propaneWeight2);
		ti.setDioxideCode1(dioxideCode1);
		ti.setDioxideWeight1(dioxideWeight1);
		ti.setDioxideCode2(dioxideCode2);
		ti.setDioxideWeight2(dioxideWeight2);
		ti.setRecorder(recorder);
		ti.setPerson(person);
		ti.setMark(mark);
		
		UpdataCareDeviceInformation ucdi = new UpdataCareDeviceInformation();
		ucdi.updateTankInfo(ti, request.getSession().getAttribute("name").toString());
		String qname = request.getSession().getAttribute("queryTI").toString();
		request.getRequestDispatcher("/careDevice/GetTI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
