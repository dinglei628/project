package com.zb.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
public class Page<T> implements Serializable {
	private Integer totalCount;
	private Integer totalPageCount;
	private Integer currSize;
	private Integer pageSize;
	private Integer start;
	private List<?> list;
	
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		if(totalCount > 0){
			this.totalCount = totalCount;
			this.totalPageCount = this.totalCount %this.pageSize ==0?(this.totalCount /this.pageSize)
					:(this.totalCount /this.pageSize)+1;
		}
	}
	public Integer getTotalPageCount() {
		return totalPageCount;
	}
	public Integer getCurrSize() {
		return currSize;
	}
	public void setCurrSize(Integer currSize) {
		if(currSize < 1){
			this.currSize = 1;
		}else if(currSize > this.totalPageCount){
			this.currSize = this.totalPageCount;
		}else{
			this.currSize = currSize;
		}
		
		this.start = (this.currSize-1)*this.pageSize;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStart() {
		return start;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
	
}
