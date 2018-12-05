package com.lyf.springboot06service.entity.vo;

import lombok.Data;

@Data
public class RolePermissionVo {

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限code
     */
    private String code;
    /**
     * 状态     0 不可用    1 可用
     */
    private Integer status;
    /**
     * 角色code
     */
    private String roleCode;

    /**
     * 是否授权
     */
    private String checked;

}
