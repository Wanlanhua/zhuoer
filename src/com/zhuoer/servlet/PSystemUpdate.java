package com.zhuoer.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 *
 */
@WebServlet("/PSystemUpdate")
public class PSystemUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PSystemUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		StringBuffer json1 = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			if ((line = reader.readLine()) != null) {
				json1.append(line);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//接受数据
		JSONObject jsonObject = JSONObject.fromObject(json1.toString());
		String version=jsonObject.getString("version");
		if(version.equals("1"))
		{
			JSONObject jo1=new JSONObject();
			jo1.put("version",3);
			
			System.out.println(jo1.toString());
			response.getWriter().write(jo1.toString());
		}
		
	}

}
