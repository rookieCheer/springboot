package com.lyf.springboot07service.biz.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.*;
import com.lyf.base.service.*;
import com.lyf.springboot07service.biz.IUserBizService;
import com.lyf.springboot07service.entity.bo.UserBo;
import com.lyf.springboot07service.entity.enums.AccountStatusEnum;
import com.lyf.springboot07service.entity.form.UserForm;
import com.lyf.springboot07service.entity.query.SuperQuery;
import com.lyf.springboot07service.entity.query.UserQuery;
import com.lyf.springboot07service.entity.vo.UserVo;
import com.lyf.springboot07service.util.RelativeDateFormat;
import com.lyf.springboot07service.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
     * 用户查询（一个用户可以有多个权限）
     *
     * @param query
     * @return
     */
    @Override
    public Result getInfo(UserQuery query) {
        //处理查询条件
        SuperQuery superQuery = query.getSuperQuery();
        //查询
        Page<SysShiroUser> page = sysShiroUserServiceImpl.selectPage(superQuery.getPage(), superQuery.getWrapper());
        List<SysShiroUserRole> sysShiroUserRoles = sysShiroUserRoleServiceImpl.selectList(null);
        Map<String, List<SysShiroUserRole>> userCodeMap = sysShiroUserRoles.stream().collect(Collectors.groupingBy(SysShiroUserRole::getUserCode));
        List<SysShiroRole> sysShiroRoles = sysShiroRoleServiceImpl.selectList(null);
        Map<String, String> codeNameMap = sysShiroRoles.stream().collect(Collectors.toMap(SysShiroRole::getCode, SysShiroRole::getName, (k1, k2) -> k1));
        List<UserVo> resultList = new ArrayList();
        page.getRecords().forEach(x -> {
            String statusName;
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(x, userVo);
            //状态处理（转成汉字）
            AccountStatusEnum byValue = AccountStatusEnum.getByValue(x.getStatus());
            if (Objects.isNull(byValue)) {
                statusName = "未知";
            } else {
                statusName = byValue.getMessage();
            }
            //角色名称（转成汉字）
            //通过用户code获取角色code
            List<String> roleNames = new ArrayList<>();
            List<SysShiroUserRole> sysShiroUserRoles1 = userCodeMap.get(x.getCode());
            if (null == sysShiroUserRoles1) {
                sysShiroUserRoles1 = new ArrayList<>();
            }
            List<String> roleCodes = sysShiroUserRoles1.stream().map(SysShiroUserRole::getRoleCode).collect(Collectors.toList());
            roleCodes.forEach(y -> {
                String s = codeNameMap.get(y);
                if (StringUtils.isBlank(s)) {
                    s = "未知";
                }
                roleNames.add(s);
            });
            userVo.setRoleNames(roleNames);
            userVo.setStatusName(statusName);
            resultList.add(userVo);
        });
        return Result.successPage(resultList, page.getTotal());
    }

    /**
     * 通过code查询
     *
     * @return
     */
    @Override
    public Result getUserInfoByCode(String code) {
        List<String> roleNames = new ArrayList<>();
        List<SysShiroUser> sysShiroUsers = sysShiroUserServiceImpl.selectList(null);
        Map<String, String> userNameMap = sysShiroUsers.stream().collect(Collectors.toMap(SysShiroUser::getCode, SysShiroUser::getName));
        sysShiroUsers = sysShiroUsers.stream().filter(x -> x.getCode().equals(code)).collect(Collectors.toList());
        if (1 != sysShiroUsers.size()) {
            return Result.error("没有此用户信息！");
        }
        SysShiroUser shiroUser = sysShiroUsers.get(0);
        List<SysShiroUserRole> sysShiroUserRoles = sysShiroUserRoleServiceImpl.selectList(new EntityWrapper<SysShiroUserRole>().eq("user_code", shiroUser.getCode()));
        if (sysShiroUserRoles.size() != 0) {
            List<String> roleCodes = sysShiroUserRoles.stream().map(SysShiroUserRole::getRoleCode).collect(Collectors.toList());
            List<SysShiroRole> sysShiroRoles = sysShiroRoleServiceImpl.selectList(new EntityWrapper<SysShiroRole>().in("code", roleCodes));
            roleNames = sysShiroRoles.stream().map(SysShiroRole::getName).collect(Collectors.toList());
        }
        UserVo userVo = this.getUserVo(shiroUser, userNameMap, roleNames);
        return Result.success(userVo);
    }


    /**
     * 获取所有的角色信息
     *
     * @return
     */
    @Override
    public Map<String, String> roleCodeAndName() {
        //用户集合
        List<SysShiroRole> roles = this.getRoles();

        Map<String, String> roleNameMap = roles.stream().collect(Collectors.toMap(SysShiroRole::getCode, SysShiroRole::getName));
        return roleNameMap;
    }

    /**
     * 保存用户信息  如果code为""则  是新增，不为“”为修改
     *
     * @return
     */
    @Override
    public Result saveUserInfo(UserForm userForm) {
        boolean result;
        boolean result1;
        Date date = new Date();
        String code = userForm.getCode();
        SysShiroUser sysShiroUser = new SysShiroUser();
//        name  account  status  code
        BeanUtils.copyProperties(userForm, sysShiroUser);
        if (StringUtils.isNotBlank(code)) {
            sysShiroUser.setUpdateTime(date);
            // sysShiroUser.setUpdateCode();
            result = sysShiroUserServiceImpl.update(sysShiroUser, new EntityWrapper<SysShiroUser>().eq("code", code));
            if (!result) {
                return Result.error("用户信息修改失败！");
            }
        } else {
            //没有加密
            //系统毫秒
            long l = System.currentTimeMillis();
            code = String.valueOf(l);
            sysShiroUser.setCode(code);
            sysShiroUser.setPwd("123456");
            //@TODO 这里要改  盐不能是code
            sysShiroUser.setSalt(code);
            sysShiroUser.setCreateTime(date);
            sysShiroUser.setUpdateTime(date);
            result = sysShiroUserServiceImpl.insert(sysShiroUser);
            if (!result) {
                return Result.error("用户信息保存失败！");
            }
        }

        //保存角色和用户code   @TODO  没有的话  sysShiroUserRoles是null还是 0
        //先删除原来的角色
        sysShiroUserRoleServiceImpl.delete(new EntityWrapper<SysShiroUserRole>().eq("user_code", code));

        List<String> roleCode = userForm.getRoleCode();
        if (null != roleCode) {
            for (String i : roleCode) {
                SysShiroUserRole sysShiroUserRole = new SysShiroUserRole();
                sysShiroUserRole.setRoleCode(i);
                sysShiroUserRole.setUserCode(code);
                //@TODO 这里要改  创建者修改者
                sysShiroUserRole.setCreateCode("");
                sysShiroUserRole.setUpdateCode("");
                sysShiroUserRole.setCreateTime(date);
                sysShiroUserRole.setUpdateTime(date);
                result1 = sysShiroUserRoleServiceImpl.insert(sysShiroUserRole);
                if (!result1) {
                    return Result.error("用户角色保存失败！");
                }
            }
        }
        return Result.success();
    }

    /**
     * 删除用户信息
     *
     * @param ids
     * @return
     */
    @Override
    public Result delUserInfo(String[] ids) {
        List<String> userCodeList = Arrays.asList(ids);
        boolean b = sysShiroUserServiceImpl.delete(new EntityWrapper<SysShiroUser>().in("code", userCodeList));
        if (b) {
            return Result.success();
        } else {
            return Result.error("未知错误");
        }
    }


    /**
     * 转换器
     *
     * @param user
     * @return
     */
    public UserVo getUserVo(SysShiroUser user, Map<String, String> userNameMap, List<String> roleNames) {
        String statusName;
        String updateName;
        String createName;
        UserVo userVo = new UserVo();
        //状态处理（转成汉字）
        AccountStatusEnum byValue = AccountStatusEnum.getByValue(user.getStatus());
        if (Objects.isNull(byValue)) {
            statusName = "未知";
        } else {
            statusName = byValue.getMessage();
        }
        //创建人
        createName = userNameMap.get(user.getCode());
        if (StringUtils.isBlank(createName)) {
            createName = "未知";
        }
        //修改人
        updateName = userNameMap.get(user.getUpdateCode());
        if (StringUtils.isBlank(updateName)) {
            updateName = "未知";
        }

        //        最后一次登录
        if (null != user.getLastLoginTime()) {
            userVo.setLastLoginTime(RelativeDateFormat.format(user.getLastLoginTime()));
        }
        userVo.setUpdateTime(RelativeDateFormat.format(user.getUpdateTime()));
        userVo.setCreateTime(RelativeDateFormat.format(user.getCreateTime()));
        userVo.setUpdateName(updateName);
        userVo.setCreateName(createName);
        userVo.setStatusName(statusName);
        userVo.setRoleNames(roleNames);
        userVo.setAccount(user.getAccount());
        userVo.setName(user.getName());
        userVo.setStatus(user.getStatus());
        userVo.setCode(user.getCode());
        return userVo;
    }


    @Override
    public List<SysShiroRole> getRoles() {
        List<SysShiroRole> sysShiroRoles = sysShiroRoleServiceImpl.selectList(null);
        return sysShiroRoles;
    }




    /**
     * 登陆 调用
     * 同时把用户对应的一些信息放入
     *
     * @param account 账户
     * @return
     */
    @Override
    public UserBo loginUser(String account) {
        SysShiroUser sysShiroUser = sysShiroUserServiceImpl.selectOne(new EntityWrapper<SysShiroUser>().eq("account", account));
        if (sysShiroUser != null) {
            UserBo userBo = new UserBo();
            BeanUtils.copyProperties(sysShiroUser,userBo);
            //获取用户角色
            List<SysShiroRole> roleLsit = this.getRoleByCode(sysShiroUser.getCode());
            if (null!=roleLsit){
                //获取对应的权限
                List<SysShiroPermission> permissionByCode = this.getPermissionByCode(roleLsit);
                userBo.setSysShiroRoleList(roleLsit);
                userBo.setSysShiroPermissionList(permissionByCode);
            }else{
                userBo.setSysShiroRoleList(null);
                userBo.setSysShiroPermissionList(null);
            }
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
