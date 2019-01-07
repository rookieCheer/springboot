package com.lyf.springboot11.web;


import com.lyf.springboot11.conguration.TargetDataSource;
import com.lyf.springboot11.entity.SysShiroRole;
import com.lyf.springboot11.service.ISysShiroRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XXX
 * @since 2018-12-28
 */
@Controller

public class SysShiroRoleController {

    @Autowired
    private ISysShiroRoleService sysShiroRoleServiceImpl;

    @ResponseBody
    @RequestMapping("/read-writ-r")
    @TargetDataSource(name = "ds1")
    public List<SysShiroRole> index(){
        List<SysShiroRole> sysShiroRoles = sysShiroRoleServiceImpl.selectList(null);
        return sysShiroRoles;
    }
    @ResponseBody
    @RequestMapping("/read-writ-w")
    public List<SysShiroRole> index1(){
        List<SysShiroRole> sysShiroRoles = sysShiroRoleServiceImpl.selectList(null);
        return sysShiroRoles;
    }
}

