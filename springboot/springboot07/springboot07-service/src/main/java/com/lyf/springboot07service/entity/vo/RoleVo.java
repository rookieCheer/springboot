package com.lyf.springboot07service.entity.vo;

import lombok.Data;

@Data
public class RoleVo {

    /**
     * 角色code 唯一标识
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色值
     */
    private String value;
    /**
     * 状态     0 不可用    1 可用
     */
    private Integer status;
    private String statusName;
    /**
     * 描述
     */
    private String description;


    /**
     * 创建人code
     */
    //private String createCode;
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
    //private Date updateTime;
    private String updateTime;

//    /**
//     * 角色对应的权限信息
//     */
//    private List<Permission> permissions;
//
//    @Data
//    public class Permission{
//        /**
//         * 权限code 唯一标识
//         */
//        private String code;
//        /**
//         * 权限名称
//         */
//        private String name;
//
//
//    }
}
