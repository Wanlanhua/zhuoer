package com.zhuoer.newCareDevice.util;

import java.util.List;

public class DataJson<T> {

	private int status;
	
	private String message;
	
	private List<T> content;

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<T> getContent() {
		return content;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	
}
