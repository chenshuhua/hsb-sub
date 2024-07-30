package com.yinhai.hsbsub.tools.pagehelper;

/**
 * @Description
 * @Author chenyl
 **/
public class PageParam {
    private int pageNumber;
    private int pageSize;

    public PageParam() {
        //给个默认值
        this.pageNumber = 1;
        this.pageSize = 10;
    }
    public PageParam(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
