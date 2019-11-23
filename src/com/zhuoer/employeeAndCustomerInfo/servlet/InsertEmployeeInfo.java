package com.zhuoer.employeeAndCustomerInfo.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.zhuoer.employeeAndCustomerInfo.dao.EmploeeDao;
import com.zhuoer.employeeAndCustomerInfo.entity.EmployeeInfo;

/**
 * Servlet implementation class InsertEmployeeInfo
 */
@WebServlet("/InsertEmployeeInfo")
public class InsertEmployeeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEmployeeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload supload = new SmartUpload();
		supload.initialize(this.getServletConfig(), request, response);
		String filename = "";
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
			String phone = res.getParameter("phone");
			String state = res.getParameter("state");
			String schrecord = res.getParameter("schrecord");
			String certificate = res.getParameter("certificate");
			String department = res.getParameter("department");
			String record = res.getParameter("record");
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
			String realPath = request.getSession().getServletContext().getRealPath("/")+"/ephoto";
			File file = new File(realPath);
			if(!file.exists())
				file.mkdir();
			com.jspsmart.upload.File f = supload.getFiles().getFile(0);
			String path = "/ephoto/"+cardID+"."+f.getFileExt();
			filename = realPath+"/"+cardID+"."+f.getFileExt();
			f.saveAs(filename);
			realPath = filename;
			EmployeeInfo ei = new EmployeeInfo();
			ei.setName(name);
			ei.setNo(no);
			ei.setSex(sex);
			ei.setBirthday(birthday);
			ei.setCardID(cardID);
			ei.setLeavedate(leavedate);
			ei.setContacts(contacts);
			ei.setPhone(phone);
			ei.setPath(path);
			ei.setState(state);
			ei.setSchrecord(schrecord);
			ei.setCertificate(certificate);
			ei.setDepartment(department);
			ei.setRecord(record);
			ei.setArea(area);
			ei.setFactorytime(factorytime);
			EmploeeDao.insertEmployee(ei, request.getSession().getAttribute("name").toString());
			response.sendRedirect(request.getContextPath()+"/GetEmployeeInfo?name=&page=1");
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
