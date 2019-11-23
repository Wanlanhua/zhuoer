package com.zhuoer.dataStatistics.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhuoer.device.util.BarcodeFactory;
import com.zhuoer.qmaintance.utils.RepairInfoTools;
@WebServlet("/AddBarcodeImage")
public class AddBarcodeImage extends HttpServlet{
	public AddBarcodeImage() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();	
        
        
		String sqlAll="SELECT * FROM deviceinfo";		
		List<Map<String, Object>> listAll=RepairInfoTools.executeQuary(sqlAll);
		for (int i = 0; i < listAll.size(); i++) {
			Gson gson = new Gson();
			Map<String, Object> qr = new HashMap();
			String no=listAll.get(i).get("no").toString();
			qr.put("no",no);
			qr.put("name", listAll.get(i).get("name"));
			String json = gson.toJson(qr);
			String path = request.getSession().getServletContext().getRealPath("/")+"QR/";
			BarcodeFactory
			.encode(json,
					800, 800, path+"loginimg.png", path+no+".jpg");	
			
		}
		
		
        out.write("ÃÌº”ÕÍ±œ");
	}

}
