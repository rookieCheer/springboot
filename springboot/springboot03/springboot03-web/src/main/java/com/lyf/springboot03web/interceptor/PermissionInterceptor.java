package com.lyf.springboot03web.interceptor;

import com.lyf.base.constant.Constants;
import com.lyf.base.entity.SysShiroPermission;
import com.lyf.base.entity.SysShiroUser;
import com.lyf.springboot03service.biz.IPermissionBizService;
import com.lyf.springboot03web.aspect.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 权限校验 拦截器
 * @Author: xxx
 * @CreateDate: 2018/11/12 13:39
 * @Version: 1.0
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
    @Autowired
    private IPermissionBizService permissionBizServiceImpl;

    /**
     * 该方法将在整个请求完成之后执行， 主要作用是用于清理资源的，
     * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
        logger.info("PermissionInterceptor afterCompletion --> ");

    }

    /**
     * 该方法将在Controller的方法调用之后执行， 方法中可以对ModelAndView进行操作 ，
     * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {
        logger.info("PermissionInterceptor postHandle --> ");

    }

    /**
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 该方法的返回值为true拦截器才会继续往下执行，该方法的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("权限拦截器 preHandle --> ");
        Method method = ((HandlerMethod) handler).getMethod();
        Auth auth = method.getAnnotation(Auth.class);
        if (auth == null) {
            //没有权限校验注解  直接放行
            return true;
        }
        // 从参数中取出用户身份并验证
        String[] authorities = auth.authorities();
        if (authorities.length > 0) {
            //判断用户是否有权限
            HttpSession session = request.getSession();
            String account = (String) session.getAttribute(Constants.SESSION_USER_ACCOUNT);
            SysShiroUser user = (SysShiroUser) session.getAttribute(account + Constants.SESSION_USER_KEY);
            List<SysShiroPermission> sysShiroPermissions = permissionBizServiceImpl.userPermission(user.getCode());
            List<String> permissionValue = sysShiroPermissions.stream().map(SysShiroPermission::getValue).collect(Collectors.toList());
            //只要包含一个就包含  放行
            for (String authoritie : authorities) {
                if (permissionValue.contains(authoritie)) {
                    return true;
                }
            }
        }
        request.setAttribute("message", "没有权限，请联系管理员！");
        request.getRequestDispatcher("/unauthorized").forward(request, response);
        return false;
    }

}
