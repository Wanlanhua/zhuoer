package com.zhuoer.careDevice.servlet.insertCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.InsertCareDeviceInformation;
import com.zhuoer.careDevice.entity.ElectricityInfo;

/**
 * Servlet implementation class InsertECI
 */
@WebServlet("/careDevice/InsertECI")
public class InsertECI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertECI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerNo = request.getParameter("customerNo");
		String highVoltage = request.getParameter("highVoltage");
		String bottomPiezoelectricity = request.getParameter("bottomPiezoelectricity");
		String watch = request.getParameter("watch");
		String person = request.getParameter("person");
		String mark = request.getParameter("mark");
		
		ElectricityInfo eci = new ElectricityInfo();
		eci.setCustomerNo(customerNo);
		eci.setHighVoltage(highVoltage);
		eci.setBottomPiezoelectricity(bottomPiezoelectricity);
		eci.setWatch(watch);
		eci.setPerson(person);
		eci.setMark(mark);
		InsertCareDeviceInformation icdi = new InsertCareDeviceInformation();
		icdi.insertElectricityinfo(eci,request.getSession().getAttribute("name").toString());
		
		Object qnameObject = request.getSession().getAttribute("queryECI");
		String qname = "";
		if(qnameObject!=null) {
			qname = qnameObject.toString();
		}
		request.getSession().setAttribute("thisPageECI", 1);
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
