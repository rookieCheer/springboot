package com.lyf.springboot02service.biz.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lyf.base.entity.SysShiroUser;
import com.lyf.base.service.ISysShiroUserService;
import com.lyf.springboot02service.biz.IUserBIzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class UserBizServiceImpl implements IUserBIzService {


    @Autowired
    private ISysShiroUserService sysShiroUserServiceImpl;

    /**
     *      * 判断登录
     * @param model
     * @param session
     * @param loginname
     * @param password
     * @return
     */
    @Override
    public String checkLogin(Model model,HttpSession session, String loginname, String password) {
        SysShiroUser sysShiroUser = sysShiroUserServiceImpl.selectOne(new EntityWrapper<SysShiroUser>().eq("account", loginname));
        if (sysShiroUser == null){
            model.addAttribute("message", "没有"+loginname+"账户信息！");
            return "loginForm";
        }
        if (password.equals(sysShiroUser.getPwd())){

            // 登录成功，将user对象设置到HttpSession作用范围域
            session.setAttribute("user", sysShiroUser);
            return "main";
        }else{
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
