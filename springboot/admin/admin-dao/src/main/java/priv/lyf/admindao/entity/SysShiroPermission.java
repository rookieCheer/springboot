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
public class SysShiroPermission extends SuperEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限code
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
    private Integer type;
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "SysShiroPermission{" +
        ", id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", parentCode=" + parentCode +
        ", parentCodes=" + parentCodes +
        ", value=" + value +
        ", path=" + path +
        ", status=" + status +
        ", type=" + type +
        ", createCode=" + createCode +
        ", updateCode=" + updateCode +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
