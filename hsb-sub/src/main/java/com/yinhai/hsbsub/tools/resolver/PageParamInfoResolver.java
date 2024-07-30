package com.yinhai.hsbsub.tools.resolver;

import com.yinhai.hsbsub.tools.pagehelper.PageParam;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

/**
 * 注册改注解器的正确方式：https://blog.csdn.net/fxbin123/article/details/83221986
 * @Description 分页注解解析器
 * @Author chenyl
 **/
public class PageParamInfoResolver implements HandlerMethodArgumentResolver {
    /**分页参数名称与前端分页描述需要保持一致(需要路径传值) */
    private static final String PAGE_NUM = "pageNum";
    private static final String PAGE_SIZE= "pageSize";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(PageParamInfo.class);
    }

    @Override
    public Object resolveArgument(@Nullable MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
        @Nullable NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory){
        PageParam pageParam;
        try {
            int pageNum = Integer.parseInt(Objects.requireNonNull(nativeWebRequest.getParameter(PAGE_NUM)));
            int pageSize = Integer.parseInt(Objects.requireNonNull(nativeWebRequest.getParameter(PAGE_SIZE)));
            pageParam = new PageParam(pageNum, pageSize);
        }catch (Exception e) {
            //如果没传或者乱传给个默认值
            pageParam = new PageParam();
        }
        return pageParam;
    }
}
