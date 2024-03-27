package com.ahao.ticket.utils;

import com.ahao.ticket.domain.CommonUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//请求相关的工具类
public class RequestUtil {
    public static HttpServletRequest getRequest(){
        return  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
    public static HttpSession getSession(){
        return getRequest().getSession();
    }


    public static CommonUser getCurrent(){
        Object obj=getSession().getAttribute("user");
        if(obj!=null){
            return (CommonUser)obj;
        }
        return null;
    }

    public static void  removeCurrent(){
        getSession().removeAttribute("user");
    }
}
