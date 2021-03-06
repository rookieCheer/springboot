package com.lyf.springboot01web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.SysShiroPermission;
import com.lyf.springboot01service.biz.IPermissionBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {

    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private IPermissionBizService permissionBizServiceImpl;


    /**
     * 分页查询所有的权限
     * @return
     */
    @RequestMapping("/000000/000000")
    public Page<SysShiroPermission> getPermission(Integer pageNum){
        Page<SysShiroPermission> permission = permissionBizServiceImpl.getPermission(pageNum);

        return permission;
    }


    //查询单条
    @RequestMapping("/000000/000001")
    public SysShiroPermission getPermissionById(String code){
        SysShiroPermission permission = permissionBizServiceImpl.getPermissionById(code);

        return permission;
    }
    //修改
    //保存
    //删除


}
