package com.zhuoer.qmaintance.beans;

import java.util.Date;

/**
 * 操作日志信息表
 * @author Administrator
 *
 */
public class OpLogInfo {
	private int id;
	private String no;
	private String content;
	private Date stamp;
	private String mark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	

}
