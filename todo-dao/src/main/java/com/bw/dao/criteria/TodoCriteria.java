package com.bw.dao.criteria;

public class TodoCriteria {
	private long authorId;
	private boolean includeItems=true;
	private boolean includeAuthor=true;
	
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long userId) {
		this.authorId = userId;
	}
	public boolean isIncludeItems() {
		return includeItems;
	}
	public void setIncludeItems(boolean includeItems) {
		this.includeItems = includeItems;
	}
	public boolean isIncludeAuthor() {
		return includeAuthor;
	}
	public void setIncludeAuthor(boolean includeAuthor) {
		this.includeAuthor = includeAuthor;
	}
	
}
