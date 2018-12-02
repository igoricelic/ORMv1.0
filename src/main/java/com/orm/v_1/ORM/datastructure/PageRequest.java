package com.orm.v_1.ORM.datastructure;

public class PageRequest {
	
	private static final Integer DEFAULT_PAGE_VALUE = 0;
	
	private static final Integer DEFAULT_SIZE_VALUE = 10;
	
	private Integer page;
	
	private Integer size;
	
	private Integer offset;
	
	public PageRequest (Integer page, Integer size) {
		this.page = page;
		this.size = size;
		this.offset = page * size;
	}
	
	public Integer getPage() {
		return page;
	}
	
	public Integer getSize() {
		return size;
	}
	
	public Integer getOffset() {
		return offset;
	}
	
	public static PageRequest build (Integer page, Integer size) {
		page = (page == null) ? DEFAULT_PAGE_VALUE : page;
		size = (size == null) ? DEFAULT_SIZE_VALUE : size;
		return new PageRequest(page, size);
	}

}
