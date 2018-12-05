package com.lyf.springboot06service.entity.form;

import lombok.Data;

@Data
public class PermissionForm {

    /**
     * 权限名称
     */
    private String code;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 父节点code
     */
    private String parentCode;
    /**
     * 节点顺序
     */
    private String parentCodes;
    /**
     * 权限标识
     */
    private String value;
    /**
     * 权限地址 url
     */
    private String path;
    /**
     * 状态     0 不可用    1 可用
     */
    private Integer status;
    /**
     * 权限类型（1 菜单权限 0  按钮 ）
     */
    private String type;

}
