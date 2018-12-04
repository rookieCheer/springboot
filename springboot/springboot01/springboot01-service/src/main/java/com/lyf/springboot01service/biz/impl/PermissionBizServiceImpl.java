package com.lyf.springboot01service.biz.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.SysShiroPermission;
import com.lyf.base.service.ISysShiroPermissionService;
import com.lyf.springboot01service.biz.IPermissionBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionBizServiceImpl implements IPermissionBizService {


    @Autowired
    private ISysShiroPermissionService sysShiroPermissionServiceImpl;

    @Override
    public Page<SysShiroPermission> getPermission(Integer pageNum){
        //当前页码  每页显示数
        Page<SysShiroPermission> page = new Page(pageNum,10);
        Page<SysShiroPermission> sysShiroPermissionPage = sysShiroPermissionServiceImpl.selectPage(page, null);
        return sysShiroPermissionPage;
    }

    @Override
    public SysShiroPermission getPermissionById(String code){
        //当前页码  每页显示数
        SysShiroPermission sysShiroPermissionPage = sysShiroPermissionServiceImpl.selectOne(new EntityWrapper<SysShiroPermission>().eq("code",code));
        return sysShiroPermissionPage;
    }

}
