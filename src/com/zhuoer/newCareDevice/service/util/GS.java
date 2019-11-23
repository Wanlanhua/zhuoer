package com.zhuoer.newCareDevice.service.util;

import com.google.gson.Gson;

public class GS {

	public static String ToJson(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}
	
}
