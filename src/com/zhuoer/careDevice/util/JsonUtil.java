package com.zhuoer.careDevice.util;

import com.google.gson.Gson;

public class JsonUtil {

	public static Gson getGson() {
		return new Gson();
	}
	
	public static String toJson(Object obj) {
		return getGson().toJson(obj);
	}
	
}
