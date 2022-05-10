package com.zzg.common.vo;

import java.util.List;

/*
    自定义分页包装对象
 */
public class PageList<T>{
    private Integer currentpage = 1; // 当前页，初始值也为第一页
    private Integer firstPage = 1;// 首页为第一页
    private Integer prePage; // 上一页
    private Integer nextPage; // 下一页
    private Integer lastPage; // 最后一页
    private Integer totalPage; // 总页数
    private Integer totalData; // 总数据
    private Integer pageSize; //
    private List<T> list; // list集合用来装查出的数据

    public PageList() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "PageList [currentpage=" + currentpage + ", firstPage=" + firstPage + ", prePage=" + prePage
                + ", nextPage=" + nextPage + ", lastPage=" + lastPage + ", totalPage=" + totalPage + ", totalData="
                + totalData + ", pageSize=" + pageSize + ", list=" + list + "]";
    }

    public Integer getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(Integer currentpage) {
        this.currentpage = currentpage;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalData() {
        return totalData;
    }

    public void setTotalData(Integer totalData) {
        this.totalData = totalData;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageList(Integer currentpage, Integer totalData, Integer pageSize) {
        this.currentpage = currentpage;
        this.totalData = totalData;
        this.pageSize = pageSize;

        /**
         * 为页面中的尾页、上一页、下一页、总页数赋值
         */
        // 总页数：总数据/每页数据 。 如果除不尽则向上取整
        this.totalPage = this.totalData % this.pageSize == 0 ? this.totalData / this.pageSize
                : this.totalData / this.pageSize + 1;
        //尾页：等于总页数
        this.lastPage = this.totalPage;
        // 上一页：如果传入当前页页码小于等于1则直接赋值为1
        this.prePage = this.currentpage <= 1 ? 1 : this.currentpage - 1;

        // 下一页:如果传入当前页页码大于等于总页数则赋值为总页数
        this.nextPage = this.currentpage >= this.totalPage ? this.totalPage : this.currentpage + 1;
    }
}
