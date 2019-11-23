package com.zhuoer.careDevice.servlet.stateCareDevice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.careDevice.dao.UpdataState;

@WebServlet("/careDevice/StateDFI")
public class StateDFI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StateDFI() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int code = Integer.parseInt(request.getParameter("code"));
		String id = request.getParameter("id");
		String auditOpinion2 = request.getParameter("auditOpinion2");
		if(code == 0) {
			UpdataState.stateN("dailyflowinfo", id, auditOpinion2, request.getSession().getAttribute("name").toString());
		}
		else if(code == 1) {
			UpdataState.stateY("dailyflowinfo", id, auditOpinion2, request.getSession().getAttribute("name").toString());
		}
		String qname = request.getSession().getAttribute("queryDFI").toString();
		request.getRequestDispatcher("/careDevice/GetDFI?qname="+qname).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
