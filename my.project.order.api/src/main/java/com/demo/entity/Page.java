package com.demo.entity;

import java.util.List;

/**
 * 分页
 */
public class Page<T> {
    private Integer pageIndex;   //当前页
    private Integer pageSize; //页容量
    private Integer totalCount;  //总数据量
    private Integer totalPageCount; //最后一页
    private List<T> list;  //页数据

    private Boolean hasNext; //是否有下页
    private Boolean hasUp;  //是否有上页

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public Boolean getHasNext() {
        return this.pageIndex < this.totalPageCount;
    }

    public Boolean getHasUp() {
        return this.pageIndex > 1;
    }

    public Page() {
    }

    public Page(Integer pageIndex, Integer pageSize, Integer totalCount,
                List<T> list) {
        super();
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPageCount = this.totalCount % this.pageSize == 0 ?
                this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
        this.list = list;
    }

}
