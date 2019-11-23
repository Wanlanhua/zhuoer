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
 * Servlet implementation class uploadImage
 */
@WebServlet("/uploadImage")
public class uploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public uploadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean state = true;
		SmartUpload supload = new SmartUpload();
		supload.initialize(this.getServletConfig(), request, response);
		String filename = "";
		try {
			supload.upload();
			Request res = supload.getRequest();
			String cardID = res.getParameter("cardID");
			String id = res.getParameter("id");
			String realPath = request.getSession().getServletContext().getRealPath("/")+"/ephoto";
			File file = new File(realPath);
			if(!file.exists())
				file.mkdir();
			com.jspsmart.upload.File f = supload.getFiles().getFile(0);
			String path = "/ephoto/"+cardID+".jpg";
			filename = realPath+"/"+cardID+".jpg";
			f.saveAs(filename);
			realPath = filename;
			state = EmploeeDao.updateImage(path,id);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		response.getWriter().println(state);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
