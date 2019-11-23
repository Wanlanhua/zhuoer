package com.zhuoer.aJsonAPI.Util;

import com.google.gson.Gson;

public class ToJson {

	public static String toJson(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	
}
