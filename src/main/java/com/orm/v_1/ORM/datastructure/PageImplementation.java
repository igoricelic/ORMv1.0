package com.orm.v_1.ORM.datastructure;

import java.util.List;

public class PageImplementation<T> implements Page<T> {
	
	private List<T> content;
	
	private Integer page;
	
	private Integer size;
	
	private Integer numberOfElements;
	
	private Integer totalPages;
	
	private Integer totalElements;
	
	private Boolean firstPage;
	
	private Boolean lastPage;
	
	public PageImplementation() {
		super();
	}

	public PageImplementation(List<T> content, Integer page, Integer size, Integer numberOfElements, Integer totalPages,
			Integer totalElements, Boolean firstPage, Boolean lastPage) {
		super();
		this.content = content;
		this.page = page;
		this.size = size;
		this.numberOfElements = numberOfElements;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.firstPage = firstPage;
		this.lastPage = lastPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public Boolean getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(Boolean firstPage) {
		this.firstPage = firstPage;
	}

	public Boolean getLastPage() {
		return lastPage;
	}

	public void setLastPage(Boolean lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		return "PageImplementation [content=" + content + ", page=" + page + ", size=" + size + ", numberOfElements="
				+ numberOfElements + ", totalPages=" + totalPages + ", totalElements=" + totalElements + ", firstPage="
				+ firstPage + ", lastPage=" + lastPage + "]";
	}
	
}
