package com.zhuoer.userLogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SafetyExist")
public class SafetyExist extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public SafetyExist() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.removeAttribute("name");
		request.removeAttribute("password");
		request.getRequestDispatcher("login.jsp").forward(request, response);	
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
