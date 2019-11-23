package com.zhuoer.device.entity;

import java.util.List;

public class PageBean<T> {
	private int pageNumber;//当前页
	private int pageSize = 15;//每页显示个数
	private int totalRecord;//总记录数
	private int totalPage;//总分页数
	private int startIndex;//开始索引
	private List<T> data;//分页数据
	public PageBean(int pageNumber, int totalRecord) {
		super();
		this.pageNumber = pageNumber;
		this.totalRecord = totalRecord;
		this.totalPage = this.totalRecord/this.pageSize;
		if (this.totalRecord%this.pageSize!=0) {
			this.totalPage++;
		}
		this.startIndex = (this.pageNumber-1)*this.pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.totalPage = this.totalRecord/this.pageSize;
		if (this.totalRecord%this.pageSize!=0) {
			this.totalPage++;
		}
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
