package com.lyf.springboot07service.entity.bo;

import com.lyf.base.entity.SysShiroPermission;
import com.lyf.base.entity.SysShiroRole;
import com.lyf.base.entity.SysShiroUser;
import lombok.Data;

import java.util.List;

/**
 * 用户业务对象。由Service层输出的封装业务逻辑的对象。
 */
@Data
public class UserBo extends SysShiroUser {

    /**
     * 用户权限集合
     */
    private List<SysShiroPermission> sysShiroPermissionList;

    /**
     * 用户角色集合
     */
    private List<SysShiroRole> sysShiroRoleList;
}
