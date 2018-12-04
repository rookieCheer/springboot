package com.lyf.springboot03service.biz.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lyf.base.constant.Constants;
import com.lyf.base.entity.SysShiroUser;
import com.lyf.base.service.ISysShiroUserService;
import com.lyf.springboot03service.biz.IUserBIzService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserBizServiceImpl implements IUserBIzService {


    @Autowired
    private ISysShiroUserService sysShiroUserServiceImpl;

    /**
     * * 判断登录
     *
     * @param model
     * @param session
     * @param loginname
     * @param password
     * @return
     */
    @Override
    public String checkLogin(Model model, HttpSession session, String loginname, String password, HttpServletRequest request) {
        if (StringUtils.isBlank(loginname) && StringUtils.isBlank(password)) {
            model.addAttribute("message", "账号或密码不能为空！");
            return "loginForm";
        }

        SysShiroUser sysShiroUser = sysShiroUserServiceImpl.selectOne(new EntityWrapper<SysShiroUser>().eq("account", loginname));
        if (sysShiroUser == null) {
            model.addAttribute("message", "没有" + loginname + "账户信息！");
            return "loginForm";
        }
        if (password.equals(sysShiroUser.getPwd())) {
            String ip = request.getRemoteAddr();
            sysShiroUser.setLastLoginIp(ip);
            sysShiroUser.setLastLoginTime(new Date());
            boolean b = sysShiroUserServiceImpl.updateById(sysShiroUser);
            // 登录成功，将user对象设置到HttpSession作用范围域
            session.setAttribute(Constants.SESSION_USER_ACCOUNT, sysShiroUser.getAccount());
            session.setAttribute(sysShiroUser.getAccount()+Constants.SESSION_USER_KEY, sysShiroUser);
            model.addAttribute("sysShiroUser",sysShiroUser);
            return "main";
        } else {
            model.addAttribute("message", "密码错误！");
            return "loginForm";
        }


    }

//    @Override
//    public SysShiroPermission getPermissionById(String code) {
//        //当前页码  每页显示数
//        SysShiroPermission sysShiroPermissionPage = sysShiroPermissionServiceImpl.selectOne(new EntityWrapper<SysShiroPermission>().eq("code", code));
//        return sysShiroPermissionPage;
//    }

}
