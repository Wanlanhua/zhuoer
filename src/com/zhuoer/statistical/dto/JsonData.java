package com.zhuoer.statistical.dto;

import java.util.List;

public class JsonData<T> {

	private boolean status;
	
	private String message;
	
	private List<T> content;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	
}
