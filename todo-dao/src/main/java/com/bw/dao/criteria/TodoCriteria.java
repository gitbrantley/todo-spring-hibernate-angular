package com.bw.dao.criteria;

public class TodoCriteria {
	private long userId;
	private boolean includeItems=true;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public boolean isIncludeItems() {
		return includeItems;
	}
	public void setIncludeItems(boolean includeItems) {
		this.includeItems = includeItems;
	}
	
}
