package com.zhuoer.device.operation.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhuoer.device.dao.Z_DandFInfoCrudDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;

/**
 * Servlet implementation class Z_DandMInfoSelectServlet
 */
@WebServlet("/Z_DandFInfoInsertServlet")
public class Z_DandFInfoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Z_DandFInfoInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *表DandFInfo的插入添加
	 *接收  deviceNo , no , mark
	 *返回： 添加成功or添加失败
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String deviceNo = request.getParameter("deviceNo").trim();
		String deviceNo1 = request.getParameter("deviceNo1").trim();
		String[] no = request.getParameterValues("no");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stamp = sdf.format(date).trim();
		String mark = request.getParameter("mark").trim();
		int count = 0;
		for (int i = 0; i < no.length; i++) {
			String selectDeviceFit= "select count(*) from DandFInfo where deviceNo=? and deviceNo1=? and no=?";
			boolean cunzai = Z_DandFInfoCrudDao.selectDeviceFittingOnly(selectDeviceFit, deviceNo,deviceNo1,no[i]);
			if (!cunzai) {
				List<Object> p = new ArrayList<>();
				p.add(deviceNo);
				p.add(deviceNo1);
				p.add(no[i]);
				p.add(stamp);
				p.add(mark);
				String sql = "insert into DandFInfo(deviceNo,deviceNo1,no,stamp,mark)values(?,?,?,?,?)";
				boolean insertCount = Z_DandFInfoCrudDao.insertDandFInfo(sql,p);
				if (insertCount) {
					count++;
				}
			}
		}
		Map<String, Object> map=new HashMap<String, Object>();
		if(count>0){
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), 
					"设备信息=>配件关联=>添加操作");
			map.put("message", "添加成功");
			String message = gson.toJson(map);
			out.println(message);
		}else{
			map.put("message", "添加失败或数据已存在");
			String message2 = gson.toJson(map);
			out.println(message2);
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
