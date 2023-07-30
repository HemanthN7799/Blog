package com.ssd.blog.payload;

import java.util.List;

public class PaginationAndSortResponse {

	private List<PostDto> postsList;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    
    
	public List<PostDto> getPostsList() {
		return postsList;
	}
	public void setPostsList(List<PostDto> postsList) {
		this.postsList = postsList;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	public PaginationAndSortResponse(List<PostDto> postsList, int pageNo, int pageSize, long totalElements,
			int totalPages, boolean last) {
		super();
		this.postsList = postsList;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}
	public PaginationAndSortResponse() {
		super();
	}
    
    
    
}
