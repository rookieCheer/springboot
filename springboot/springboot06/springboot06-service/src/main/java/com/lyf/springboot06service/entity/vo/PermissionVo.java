package com.lyf.springboot06service.entity.vo;

import lombok.Data;

@Data
public class PermissionVo {

    /**
     * 权限code
     */
    private String code;
    /**
     * 权限名称
     */
    private String name;
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
    private String statusName;

    /**
     * 权限类型（1 菜单权限 0  按钮 ）
     */
    private String type;
    private String typeName;
    /**
     * 父节点code
     */
    private String parentCode;
    /**
     * 节点顺序
     */
    private String parentCodes;
    /**
     * 创建人code
     */
    //  private String createCode;
    private String createName;
    /**
     * 修改人code
     */
    //private String updateCode;
    private String updateName;
    /**
     * 创建时间
     */
    //private Date createTime;
    private String createTime;
    /**
     * 修改时间
     */
    // private Date updateTime;
    private String updateTime;


}
