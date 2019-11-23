package com.zhuoer.employeeAndCustomerInfo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.zhuoer.employeeAndCustomerInfo.dao.EmploeeDao;
import com.zhuoer.employeeAndCustomerInfo.entity.EmployeeInfo;

/**
 * Servlet implementation class UpdateEmployeeInfo
 */
@WebServlet("/UpdateEmployeeInfo")
public class UpdateEmployeeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployeeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload supload = new SmartUpload();
		supload.initialize(this.getServletConfig(), request, response);
		try {
			supload.upload();
			Request res = supload.getRequest();
			String no = res.getParameter("no");
			String name = res.getParameter("name");
			String sex = res.getParameter("sex");
			String birthday = res.getParameter("birthday");
			String factorytime = res.getParameter("factorytime");
			String cardID = res.getParameter("cardID");
			String leavedate = res.getParameter("leavedate");
			String contacts = res.getParameter("contacts");
			String state = res.getParameter("state");
			String schrecord = res.getParameter("schrecord");
			String certificate = res.getParameter("certificate");
			String department = res.getParameter("department");
			String[] areas = res.getParameterValues("area");
			String area = "";
			if(areas==null || areas.length==0) {
			}
			else {
				for(int i=0; i<areas.length; i++) {
					area+="-"+areas[i];
				}
				area = area.substring(1);
			}
			String record = res.getParameter("record");
			String id = res.getParameter("id");
			EmployeeInfo ei = new EmployeeInfo();
			ei.setId(Integer.valueOf(id));
			ei.setName(name);
			ei.setNo(no);
			ei.setSex(sex);
			ei.setBirthday(birthday);
			ei.setCardID(cardID);
			ei.setLeavedate(leavedate);
			ei.setContacts(contacts);
			ei.setState(state);
			ei.setSchrecord(schrecord);
			ei.setCertificate(certificate);
			ei.setDepartment(department);
			ei.setArea(area);
			ei.setRecord(record);
			ei.setFactorytime(factorytime);
			EmploeeDao.updateEmployee(ei, request.getSession().getAttribute("name").toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/GetEmployeeInfo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
