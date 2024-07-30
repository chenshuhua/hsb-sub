package com.yinhai.hsbsub.tools.pagehelper;

import java.util.List;

/**
 * @Description 使分页数据便于前端按照统一的格式获取数据
 * @Author chenyl
 **/
public class MyPage<T> {
    private Long total;
    private Integer totalPage;
    private List<T> list;

    public MyPage() {
    }

    public MyPage(Long total, Integer totalPage, List<T> list) {
        this.total = total;
        this.totalPage = totalPage;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "MyPageHelper{" +
            "total=" + total +
            ", totalPage=" + totalPage +
            ", list=" + list +
            '}';
    }
}
