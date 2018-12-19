package com.lyf.springboot07service.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVo {

    /**
     * 用户code 唯一标识
     */
    private String code;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 账户状态  0 可用  1 待领取  2不可用
     */
    private String status;
    private String statusName;


    /**
     * 最后一次登录时间
     */
    //private Date lastLoginTime;
    private String lastLoginTime;
    //    /**
//     * 创建人code
//     */
//    private String createCode;
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


    /**
     * 用户角色code
     */
    // private String roleCode;
    private String roleName;

    /**
     * 用户角色集合
     */
    private List<String> roleNames;

}
