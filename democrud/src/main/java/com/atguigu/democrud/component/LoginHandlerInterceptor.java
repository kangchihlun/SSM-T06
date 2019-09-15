package com.atguigu.democrud.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 登陸攔截器
public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 目標方法執行之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object _usr = httpServletRequest.getSession().getAttribute("loginUser");
        if(_usr == null){

            // 丟一個錯誤 msg 回去
            httpServletRequest.setAttribute("msg","請先登錄");
            //未登錄，返回登陸頁面
            httpServletRequest.getRequestDispatcher("/index.html").forward(httpServletRequest,httpServletResponse);
            return false;
        }else{
            //已登錄，放行
            return true;
        }


    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
