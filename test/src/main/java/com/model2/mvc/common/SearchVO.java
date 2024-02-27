package com.model2.mvc.common;


public class SearchVO {
	
	private int page;
	String showOption;
	String orderByOption;
	String searchCondition;
	String searchKeyword;
	int pageUnit;
	int pageSize;
	
	public SearchVO(){
	}
	
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getShowOption() {
		return showOption;
	}

	public void setShowOption(String showOption) {
		this.showOption = showOption;
	}

	public String getOrderByOption() {
		return orderByOption;
	}

	public void setOrderByOption(String orderByOption) {
		this.orderByOption = orderByOption;
	}
	
	
}