package com.lyf.springboot03web.interceptor;

import com.lyf.base.constant.Constants;
import com.lyf.base.entity.SysShiroUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 登录拦截器
 * @Author: xxx
 * @CreateDate: 2018/11/12 13:04
 * @Version: 1.0
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    /**
     * 该方法将在整个请求完成之后执行， 主要作用是用于清理资源的，
     * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
        logger.info("登录拦截器 afterCompletion --> ");

    }

    /**
     * 该方法将在Controller的方法调用之后执行， 方法中可以对ModelAndView进行操作 ，
     * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {
        logger.info("登录拦截器 postHandle --> ");

    }

    /**
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 该方法的返回值为true拦截器才会继续往下执行，该方法的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("登录拦截器 preHandle  controller之前执行 --> ");
        // flag变量用于判断用户是否登录，默认为false
        boolean flag = false;
        // 拦截请求
        // 1.获取session中的用户
        HttpSession session = request.getSession();

        SysShiroUser user = (SysShiroUser) session.getAttribute( session.getAttribute(Constants.SESSION_USER_ACCOUNT)+ Constants.SESSION_USER_KEY);
        // 2.判断用户是否已经登录
        if (user == null) {
            Object attribute = session.getAttribute(session.getAttribute(Constants.SESSION_USER_ACCOUNT)+ Constants.SESSION_OLD_USER_KEY);
            if (null != attribute) {
                user = (SysShiroUser)attribute;
                request.setAttribute("message", "您的账号在"+user.getLastLoginTime()+"时已经在"+user.getLastLoginIp()+"登录 您的登录已下线");
            } else {
                request.setAttribute("message", "请先登录再访问网站");
            }
            // 如果用户没有登录，则设置提示信息，跳转到登录页面
            logger.info("登录拦截器   登录前访问的页面" + request.getServletPath());
            request.getRequestDispatcher("/loginForm").forward(request, response);
        } else {
            // 如果用户已经登录 则验证通过，放行
            flag = true;
        }
        return flag;
    }

}
