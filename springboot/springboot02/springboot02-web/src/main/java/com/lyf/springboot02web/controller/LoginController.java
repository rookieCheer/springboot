package com.lyf.springboot02web.controller;


import com.lyf.springboot02service.biz.IUserBIzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Description: 登录页面
 * @Author: xxx
 * @CreateDate: 2018/11/12 11:02
 * @Version: 1.0
 */
@Controller
public class LoginController {

    @Autowired
    private IUserBIzService userBizServiceImpl;

    @RequestMapping("/login")
    public String login(Model model, String loginname, String password, HttpSession session) {
        return userBizServiceImpl.checkLogin(model, session, loginname, password);
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public String logout( HttpSession session) {
        //清除session
        session.invalidate();
        return "loginForm";
    }

    /**
     * 拦截器 没有登录时  跳转页面  不然404
     * @return
     */
    @RequestMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
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

