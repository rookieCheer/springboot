package com.lyf.springboot04web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    //post登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username,String password,Model model){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
            return "main";
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("message","密码错误");
        }catch (UnknownAccountException uae) {
            model.addAttribute("message",uae.getMessage());
        }catch (LockedAccountException lae) {
            model.addAttribute("message",lae.getMessage());
        }catch (ExcessiveAttemptsException eae) {
            model.addAttribute("message","次数超出");
        }
        return "login";
    }
    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            subject.logout();
        }
        return "loginForm";
    }

    /**
     * 拦截器 没有登录时  跳转页面  不然404
     * @return
     */
    @RequestMapping("/loginForm")
    public String loginForm() {
        return "login";
    }

    /**
     * 没有权限页面跳转
     * @return
     */
    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}
