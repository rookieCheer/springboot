package com.lyf.springboot04web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限测试
 */
@RestController
public class PermissionTestController {


    /**
     * 权限校验
     * @return
     */
    @RequestMapping("/role")
    @RequiresPermissions("role")
    public String index(){
        return "有权限！";
    }

    /**
     * 角色校验
     * @return
     */
    @RequestMapping("/testRole")
    @RequiresRoles("test")
    public String role(){
        return "有test角色！";
    }
}
