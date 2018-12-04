package com.lyf.springboot03service.biz;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IUserBIzService {

    String checkLogin(Model model, HttpSession session, String loginname, String password, HttpServletRequest request);
}
