package com.lyf.springboot02service.biz;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface IUserBIzService {

    String checkLogin(Model model, HttpSession session, String loginname, String password);
}
