package priv.lyf.admindao.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import priv.lyf.admindao.superclass.SuperEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author XXX
 * @since 2019-02-15
 */
public class SysShiroUserRole extends SuperEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色code
     */
    private String roleCode;
    /**
     * 用户code
     */
    private String userCode;
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

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
        return "SysShiroUserRole{" +
        ", id=" + id +
        ", roleCode=" + roleCode +
        ", userCode=" + userCode +
        ", createCode=" + createCode +
        ", updateCode=" + updateCode +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
