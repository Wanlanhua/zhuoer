package com.zhuoer.newCareDevice.service.delete;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuoer.aJsonAPI.Util.ToJson;
import com.zhuoer.newCareDevice.dao.DeleteDao;
import com.zhuoer.qmaintance.utils.OpLogInfoTools;
import com.zhuoer.statistical.dto.JsonData;

/**
 * Servlet implementation class DeleteJZGQZLLJLB
 */
@WebServlet("/DeleteJZGQZLLJLB")
public class DeleteJZGQZLLJLB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteJZGQZLLJLB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		boolean flag = DeleteDao.deleteJzgqzlljlb(Integer.valueOf(id));
		JsonData<List> jd = new JsonData<>();
		if(flag) {
			OpLogInfoTools.insertOpLogInfo(request.getSession().getAttribute("name").toString(), "看护管理=>集中供气站流量记录表=>删除操作");
			jd.setStatus(true);
			jd.setMessage("删除成功");
		} else {
			jd.setStatus(false);
			jd.setMessage("删除失败");
		}
		response.getWriter().println(ToJson.toJson(jd));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
