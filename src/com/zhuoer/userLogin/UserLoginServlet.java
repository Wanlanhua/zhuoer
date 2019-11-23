package com.zhuoer.userLogin;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhuoer.aJsonAPI.Power;
import com.zhuoer.employeeAndCustomerInfo.dao.PowerLogin;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet 
{

	private static final long serialVersionUID = 1L;

	public UserLoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();	
		String userName=request.getParameter("userName").trim();
		String userPassword=request.getParameter("userPassword").trim();
		UserLoginBean ulb=UserLoginDao.isUser(userName, userPassword);
		if(ulb != null ) {
			if(!ulb.getUserPassword().equals(userPassword) )
			{
				HttpSession session=request.getSession();
				session.setAttribute("name"," ");
				out.print(
						"<script>alert('用户名或密码错误...');" +"window.location='login.jsp'</script>"
						);
		        out.flush();
		        out.close();
			}
			
			else {
//				if(PowerLogin.isAllowLogin(userName)) {
					Map<String, String> operate = Power.getOperate(userName);
					HttpSession session=request.getSession();
					session.setAttribute("name",ulb.getUserName());
					session.setAttribute("role", ulb.getUserRole());
					session.setAttribute("password", ulb.getUserPassword());
					session.setAttribute("operate", operate);
					response.sendRedirect("index.jsp");
//				}
//				else {
//					out.print(
//							"<script>alert('该用户没有任何权限，请联系管理员添加权限重新登陆');" +"window.location='login.jsp'</script>"
//							);
//					out.flush();
//			        out.close();
//				}
			}
		}
		else {
			out.print(
					"<script>alert('用户名或密码错误...');" +"window.location='login.jsp'</script>"
					);
	        out.flush();
	        out.close();
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
