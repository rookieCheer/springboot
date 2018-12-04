package com.lyf.springboot04service.biz.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lyf.base.entity.*;
import com.lyf.base.service.*;
import com.lyf.springboot04service.Bo.UserBo;
import com.lyf.springboot04service.biz.IUserBizService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBizServiceImpl implements IUserBizService {


    @Autowired
    private ISysShiroUserService sysShiroUserServiceImpl;
    @Autowired
    private ISysShiroUserRoleService sysShiroUserRoleServiceImpl;
    @Autowired
    private ISysShiroRolePermissionService sysShiroRolePermissionServiceImpl;
    @Autowired
    private ISysShiroPermissionService sysShiroPermissionServiceImpl;
    @Autowired
    private ISysShiroRoleService sysShiroRoleServiceImpl;

    /**
     * 登陆 调用
     * 同时把用户对应的一些信息放入
     *
     * @param account 账户
     * @return
     */
    @Override
    public UserBo loginUser(String account) {
        List<SysShiroUser> sysShiroUserList = sysShiroUserServiceImpl.selectList(new EntityWrapper<SysShiroUser>().eq("account", account));
        if (sysShiroUserList != null && sysShiroUserList.size() == 1) {
            UserBo userBo = new UserBo();
            SysShiroUser sysShiroUser = sysShiroUserList.get(0);
            BeanUtils.copyProperties(sysShiroUser,userBo);
            //获取用户角色
            List<SysShiroRole> roleLsit = this.getRoleByCode(sysShiroUser.getCode());
            //获取对应的权限
            List<SysShiroPermission> permissionByCode = this.getPermissionByCode(roleLsit);
            userBo.setSysShiroRoleList(roleLsit);
            userBo.setSysShiroPermissionList(permissionByCode);
            return userBo;
        }
        return null;
    }

    /**
     * 获取当前用户的角色信息
     * @param userCode
     * @return
     */
    public List<SysShiroRole> getRoleByCode(String userCode) {
        List<SysShiroUserRole> sysShiroUserRoleList = sysShiroUserRoleServiceImpl.selectList(new EntityWrapper<SysShiroUserRole>().eq("user_code", userCode));
        List<String> roleCode = sysShiroUserRoleList.stream().map(SysShiroUserRole::getRoleCode).collect(Collectors.toList());
        if (null != roleCode && roleCode.size() > 0) {
            return sysShiroRoleServiceImpl.selectList(new EntityWrapper<SysShiroRole>().in("code", roleCode));
        }
        return null;
    }

    /**
     * 获取角色对应的权限
     * @param roleLsit
     * @return
     */
    public List<SysShiroPermission> getPermissionByCode(List<SysShiroRole> roleLsit) {
        List<String> roleCode = roleLsit.stream().map(SysShiroRole::getCode).collect(Collectors.toList());
        if (null != roleCode && roleCode.size() > 0) {
            List<SysShiroRolePermission> sysShiroRolePermissions = sysShiroRolePermissionServiceImpl.selectList(new EntityWrapper<SysShiroRolePermission>().in("role_code", roleCode));
            List<String> permissionCode = sysShiroRolePermissions.stream().map(SysShiroRolePermission::getPermissionCode).collect(Collectors.toList());
            return sysShiroPermissionServiceImpl.selectList(new EntityWrapper<SysShiroPermission>().in("code", permissionCode));
        }
        return null;
    }

}
