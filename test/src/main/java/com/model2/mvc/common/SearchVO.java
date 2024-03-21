package com.model2.mvc.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchVO {
	
	private int page;
	String showOption;
	String orderByOption;
	String searchCondition;
	String searchKeyword;
	
	String inventoryValue;
	String rankingAscValue;
	String rankingDescValue;
	
	int pageUnit;
	int pageSize;
	
	boolean fixedSearchRangeOne;
	boolean fixedSearchRangeTwo;
	boolean fixedSearchRangeThree;
	
	int searchRangeLow;
	int searchRangeHigh;
	
	int priceRangeLow;
	int priceRangeHigh;
	
	public SearchVO(){
	}
	
	public void setPriceRange() {
		List<Integer> list = new ArrayList<Integer>();
		
		if(fixedSearchRangeOne) {
			list.addAll(Arrays.asList(10000,19999));
		}
		
		if(fixedSearchRangeTwo) {
			list.addAll(Arrays.asList(20000,29999));
		}
		
		if(fixedSearchRangeThree) {
			list.addAll(Arrays.asList(30000,39999));
		}
		
		if(priceRangeLow != 0) {
			list.add(priceRangeLow);
		}
		
		if(priceRangeHigh != 0) {
			list.add(priceRangeHigh);
			
		}
		
		System.out.println("Search::" + list.toString());
		
		if(list.size() != 0) {
			this.priceRangeLow = Collections.min(list);
			this.priceRangeHigh = Collections.max(list);
		}
		
		if(this.priceRangeHigh == 0) {
			this.priceRangeHigh = 99999;
		}
		
	}
	
	
	public String getInventoryValue() {
		return inventoryValue;
	}

	public String getRankingAscValue() {
		return rankingAscValue;
	}

	public String getRankingDescValue() {
		return rankingDescValue;
	}

	public void setInventoryValue(String inventoryValue) {
		this.inventoryValue = inventoryValue;
	}

	public void setRankingAscValue(String rankingAscValue) {
		this.rankingAscValue = rankingAscValue;
	}

	public void setRankingDescValue(String rankingDescValue) {
		this.rankingDescValue = rankingDescValue;
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

	public int getPriceRangeLow() {
		return priceRangeLow;
	}


	public int getPriceRangeHigh() {
		return priceRangeHigh;
	}


	public void setPriceRangeLow(int priceRangeLow) {
		this.priceRangeLow = priceRangeLow;
	}


	public void setPriceRangeHigh(int priceRangeHigh) {
		this.priceRangeHigh = priceRangeHigh;
	}


	@Override
	public String toString() {
		return "SearchVO [page=" + page + ", showOption=" + showOption + ", orderByOption=" + orderByOption
				+ ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword + ", inventoryValue="
				+ inventoryValue + ", rankingAscValue=" + rankingAscValue + ", rankingDescValue=" + rankingDescValue
				+ ", pageUnit=" + pageUnit + ", pageSize=" + pageSize + ", fixedSearchRangeOne=" + fixedSearchRangeOne
				+ ", fixedSearchRangeTwo=" + fixedSearchRangeTwo + ", fixedSearchRangeThree=" + fixedSearchRangeThree
				+ ", searchRangeLow=" + searchRangeLow + ", searchRangeHigh=" + searchRangeHigh + "]";
	}


	
	
	
}