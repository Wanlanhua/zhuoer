package com.zhuoer.careDevice.servlet.updateCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.UpdataCareDeviceInformation;
import com.zhuoer.careDevice.entity.ElectricityInfo;

/**
 * Servlet implementation class UpdateECI
 */
@WebServlet("/careDevice/UpdateECI")
public class UpdateECI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateECI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String customerNo = request.getParameter("customerNo");
		String highVoltage = request.getParameter("highVoltage");
		String bottomPiezoelectricity = request.getParameter("bottomPiezoelectricity");
		String watch = request.getParameter("watch");
		String person = request.getParameter("person");
		String mark = request.getParameter("mark");
		
		ElectricityInfo eci = new ElectricityInfo();
		eci.setId(Integer.parseInt(id));
		eci.setCustomerNo(customerNo);
		eci.setHighVoltage(highVoltage);
		eci.setBottomPiezoelectricity(bottomPiezoelectricity);
		eci.setWatch(watch);
		eci.setPerson(person);
		eci.setMark(mark);
		UpdataCareDeviceInformation ucdi = new UpdataCareDeviceInformation();
		ucdi.updateElectricityInfo(eci, request.getSession().getAttribute("name").toString());
		String qname = request.getSession().getAttribute("queryECI").toString();
		request.getRequestDispatcher("/careDevice/GetECI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
