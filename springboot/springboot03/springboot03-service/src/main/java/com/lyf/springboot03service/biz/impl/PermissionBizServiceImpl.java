package com.lyf.springboot03service.biz.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.SysShiroPermission;
import com.lyf.base.entity.SysShiroRolePermission;
import com.lyf.base.entity.SysShiroUserRole;
import com.lyf.base.service.ISysShiroPermissionService;
import com.lyf.base.service.ISysShiroRolePermissionService;
import com.lyf.base.service.ISysShiroUserRoleService;
import com.lyf.springboot03service.biz.IPermissionBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionBizServiceImpl implements IPermissionBizService {


    @Autowired
    private ISysShiroPermissionService sysShiroPermissionServiceImpl;

    @Autowired
    private ISysShiroUserRoleService sysShiroUserRoleServiceImpl;

    @Autowired
    private ISysShiroRolePermissionService sysShiroRolePermissionServiceImpl;

    /**
     * 通过用户code获取角色code集合
     * 获取角色对应的权限
     * 得到用户code对应的权限（适用一个用户多个角色） 可用的权限
     * @param userCode
     * @return
     */
    @Override
    public List<SysShiroPermission> userPermission(String userCode){
        List<SysShiroUserRole> userRole = sysShiroUserRoleServiceImpl.selectList(new EntityWrapper<SysShiroUserRole>().eq("user_code", userCode));
        List<String> roleCode = userRole.stream().map(SysShiroUserRole::getRoleCode).collect(Collectors.toList());

        List<SysShiroRolePermission> rolePermission = sysShiroRolePermissionServiceImpl.selectList(new EntityWrapper<SysShiroRolePermission>().in("role_code", roleCode));
        List<String> permissonCode = rolePermission.stream().map(SysShiroRolePermission::getPermissionCode).collect(Collectors.toList());
        List<SysShiroPermission> permission = sysShiroPermissionServiceImpl.selectList(new EntityWrapper<SysShiroPermission>().in("code", permissonCode).eq("status",1));
        return permission;

    }




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
