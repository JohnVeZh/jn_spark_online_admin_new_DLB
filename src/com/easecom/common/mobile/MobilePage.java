package com.easecom.common.mobile;

import java.util.ArrayList;
import java.util.List;

public class MobilePage {
	private int startRecord = 0;
	private int totalPage = 0;
	private int rowCountPerPage = 5;
	private int currentPage = 0;
	private int totalCount = 0;
	
	private List list = new ArrayList();
	
	public MobilePage(){}
	public MobilePage(int currentPage, int rowCountPerPage) throws Exception{
		this.rowCountPerPage = rowCountPerPage;
		this.currentPage = currentPage;
		this.startRecord = (currentPage-1)*rowCountPerPage;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getRowCountPerPage() {
		return rowCountPerPage;
	}
	public void setRowCountPerPage(int rowCountPerPage) {
		this.rowCountPerPage = rowCountPerPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * 设置总记录数，顺便计算下总页数
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPage = (totalCount-1)/rowCountPerPage+1;
	}
}
