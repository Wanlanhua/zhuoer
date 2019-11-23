package com.zhuoer.careDevice.servlet.updateCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.UpdataCareDeviceInformation;
import com.zhuoer.careDevice.entity.FlushTankInfo;
import com.zhuoer.careDevice.util.TimeUtilToSql;

/**
 * Servlet implementation class UpdateFTI
 */
@WebServlet("/careDevice/UpdateFTI")
public class UpdateFTI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFTI() {
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
		String gasTank = request.getParameter("gasTank");
		String code = request.getParameter("code");
		String finishCode = request.getParameter("finishCode");
		String weight = request.getParameter("weight");
		String recorder = request.getParameter("recorder");
		String mark = request.getParameter("mark");
		
		FlushTankInfo fti = new FlushTankInfo();
		fti.setId(Integer.parseInt(id));
		fti.setCustomerNo(customerNo);
		fti.setMaintenanceDate(TimeUtilToSql.getStringUtilDate(maintenanceDate));
		fti.setGasTank(gasTank);
		fti.setCode(code);
		fti.setFinishCode(finishCode);
		fti.setWeight(weight);
		fti.setRecorder(recorder);
		fti.setMark(mark);
		UpdataCareDeviceInformation ucdi = new UpdataCareDeviceInformation();
		ucdi.updateFlushTankInfo(fti, request.getSession().getAttribute("name").toString());
		String qname = request.getSession().getAttribute("queryFTI").toString();
		request.getRequestDispatcher("/careDevice/GetFTI?qname="+qname).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
