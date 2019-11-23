package com.zhuoer.device.util;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;

public class QRCode {

	/**
	 * 生成二维码用具类
	 * @param text 要生成二维码图片信息的文字
	 * @param path 二维码要存放的全路径
	 * @param imgName 二维码名称
	 */
	public static void createQR(String text,String path,String imgName){
		int width = 200;   
        int height = 200;   
        String format = "png";   
        Hashtable hints= new Hashtable();   
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
        BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
         File outputFile = new File(path+imgName); 
         if(!outputFile.exists()){  
        	 outputFile.mkdirs();  
        } 
         try {
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
}
