package com.yinhai.hsbsub.repository.base;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.yinhai.hsbsub.tools.pagehelper.MyPage;
import com.yinhai.hsbsub.tools.pagehelper.PageParam;

import java.util.List;

/**
 * @Description 基础mapper类
 * @Author chenyl
 **/
public interface BaseSupportMapper {

    /**
    * 如果不是第一页就不count
    * @Author  chenyl
    * @param countColumn 指定count列
    * @param pageParam 分页参数
    */
    default void beginPager(PageParam pageParam, String countColumn) {
        if(pageParam == null) {
            pageParam = new PageParam();
        }
        if (pageParam.getPageNumber() > 1) {
            PageMethod.startPage(pageParam.getPageNumber(), pageParam.getPageSize(), false);
        } else {
            if("".equals(countColumn)) {
                countColumn = "1";
            }
            PageMethod.startPage(pageParam.getPageNumber(), pageParam.getPageSize(), true).countColumn(countColumn);
        }
    }

    /**
     * 返回分页结果并包装成统一的分页类
     * @Author  chenyl
     * @param list 结果列表
     * @return 分页类
     */
    default <T> MyPage<T> endPage(List<T> list) {
        PageInfo<T> info = new PageInfo<>(list);
        return new MyPage<>(info.getTotal(), info.getPages(), info.getList());
    }
}
