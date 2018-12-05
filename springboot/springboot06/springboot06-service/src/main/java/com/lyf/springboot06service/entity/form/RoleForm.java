package com.lyf.springboot06service.entity.form;

import lombok.Data;

@Data
public class RoleForm {
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色值
     */
    private String value;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态  0冻结   1可用
     */
    private Integer status;


    /**
     * 角色code
     */
    private String code;

}
