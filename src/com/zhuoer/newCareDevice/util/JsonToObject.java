package com.zhuoer.newCareDevice.util;

import com.google.gson.Gson;

public class JsonToObject {

	public <T> T jsonToObject(String json, T t) {
		Gson gson = new Gson();
		T tobj = (T) gson.fromJson(json, t.getClass());
		return tobj;
	}
	
}
