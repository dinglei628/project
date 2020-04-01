package com.smbms.my.pojo;

import java.util.List;

public class Page<T> {
	private Integer pageSize;
	private Integer pageIndex;
	private Integer totalCount;
	private Integer totalPageCount;
	private List<T> list;
	private Boolean hasPre;
	private Boolean hasNext;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Boolean getHasPre() {
		return this.pageIndex>1;
	}
	public Boolean getHasNext() {
		return this.pageIndex<this.totalPageCount;
	}
	public Page(Integer pageSize, Integer pageIndex, Integer totalCount,
                List<T> list) {
		super();
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.totalCount = totalCount;
		this.list = list;
		this.totalPageCount=this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:(this.totalCount/this.pageSize+1);
	}
	public Page() {
		super();
	}
}
