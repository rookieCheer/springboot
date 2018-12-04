package com.lyf.base.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.lyf.base.superclass.SuperEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author XXX
 * @since 2018-11-15
 */
public class SysShiroUser extends SuperEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
     * 用户密码
     */
    private String pwd;
    /**
     * 盐
     */
    private String salt;
    /**
     * 账户状态  0 可用  1 待领取  2不可用
     */
    private String status;
    /**
     * 最后一次登录ip
     */
    private String lastLoginIp;
    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;
    /**
     * 创建人code
     */
    private String createCode;
    /**
     * 修改人code
     */
    private String updateCode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    public String getUpdateCode() {
        return updateCode;
    }

    public void setUpdateCode(String updateCode) {
        this.updateCode = updateCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysShiroUser{" +
        ", id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", account=" + account +
        ", pwd=" + pwd +
        ", salt=" + salt +
        ", status=" + status +
        ", lastLoginIp=" + lastLoginIp +
        ", lastLoginTime=" + lastLoginTime +
        ", createCode=" + createCode +
        ", updateCode=" + updateCode +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
