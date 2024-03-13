package com.model2.mvc.common;


public class SearchVO {
	
	private int page;
	String showOption;
	String orderByOption;
	String searchCondition;
	String searchKeyword;
	int pageUnit;
	int pageSize;
	
	boolean fixedSearchRangeOne;
	boolean fixedSearchRangeTwo;
	boolean fixedSearchRangeThree;
	
	int searchRangeLow;
	int searchRangeHigh;
	
	
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

	public boolean isFixedSearchRangeOne() {
		return fixedSearchRangeOne;
	}

	public boolean isFixedSearchRangeTwo() {
		return fixedSearchRangeTwo;
	}

	public boolean isFixedSearchRangeThree() {
		return fixedSearchRangeThree;
	}

	public int getSearchRangeLow() {
		return searchRangeLow;
	}

	public int getSearchRangeHigh() {
		return searchRangeHigh;
	}

	public void setFixedSearchRangeOne(boolean fixedSearchRangeOne) {
		this.fixedSearchRangeOne = fixedSearchRangeOne;
	}

	public void setFixedSearchRangeTwo(boolean fixedSearchRangeTwo) {
		this.fixedSearchRangeTwo = fixedSearchRangeTwo;
	}

	public void setFixedSearchRangeThree(boolean fixedSearchRangeThree) {
		this.fixedSearchRangeThree = fixedSearchRangeThree;
	}

	public void setSearchRangeLow(int searchRangeLow) {
		this.searchRangeLow = searchRangeLow;
	}

	public void setSearchRangeHigh(int searchRangeHigh) {
		this.searchRangeHigh = searchRangeHigh;
	}

	@Override
	public String toString() {
		return "SearchVO [page=" + page + ", showOption=" + showOption + ", orderByOption=" + orderByOption
				+ ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword + ", pageUnit=" + pageUnit
				+ ", pageSize=" + pageSize + ", fixedSearchRangeOne=" + fixedSearchRangeOne + ", fixedSearchRangeTwo="
				+ fixedSearchRangeTwo + ", fixedSearchRangeThree=" + fixedSearchRangeThree + ", searchRangeLow="
				+ searchRangeLow + ", searchRangeHigh=" + searchRangeHigh + "]";
	}
	
	
	
	
}