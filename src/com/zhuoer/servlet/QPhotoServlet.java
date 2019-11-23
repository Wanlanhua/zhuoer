package com.zhuoer.servlet;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/QPhotoServlet")
public class QPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("content-type", "text/html;charset=UTF-8");
		
		String savePath = req.getRealPath("/")+"photos/";
		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("utf-8");

		try {
			List<FileItem> itemList = sfu.parseRequest(req);
			List<String> list=new ArrayList<String>();
			 JSONObject jss=new JSONObject();
			for (FileItem fileItem : itemList) {
				int i=1;
				String filedName = fileItem.getFieldName();
				if (fileItem.isFormField()) {
					String value = fileItem.getString();
					value = new String(value.getBytes("iso-8859-1"), "utf-8");
				} else {
					Long size = fileItem.getSize();
					String filetype = ".jpg";
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String namess=fileItem.getName() ;
					String names=namess.substring(0,namess.indexOf("."));
					String fileNames =names+ sdf.format(date) + filetype;
					String fileName = fileNames.substring(fileNames.lastIndexOf("\\") + 1);
					//返回的路径
					String getPath = savePath + fileName;
					
					File file = new File(savePath, fileName);
					try {
						fileItem.write(file);
						//list.add(getPath);
						list.add(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
			for (int i = 1; i <= 5; i++) {
				if (i>list.size()) {
					list.add("");
				}
			}
//			if(list.size()==1)
//			{
//				for(int i=1;i<=4;i++)
//				{
//					list.add("");
//				}
//			}else if(list.size()==2)
//			{
//				for(int i=1;i<=3;i++)
//				{
//					list.add("");
//				}
//			}else if(list.size()==3)
//			{
//				for(int i=1;i<=2;i++)
//				{
//					list.add("");
//				}
//			}else if(list.size()==4)
//			{
//					list.add("");
//				
//			}else
//			{
//				System.out.println("五张");
//			}
			int i=1;
				for (String string : list) {
//					String sss=string.replace("//", "/");
//					sss=string.replace("\\", "/");
					 if(!string.equals(""))
					 {
						 string="http://47.94.160.197:8080/photos/"+string;
					 }
					
					 jss.put("path"+i, string);
					
				      i++;
				}
				jss.put("result","1");
				resp.getWriter().write(jss.toString());
	
		} catch (FileSizeLimitExceededException e) {
			req.setAttribute("msg", "文件太大");
		} catch (FileUploadException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
