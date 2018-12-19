package com.lyf.springboot07service.entity.query;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.SysShiroPermission;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class PermissionQuery extends Pageable {

    /**
     * 权限code
     */
    private String code;
    /**
     * 权限标识
     */
    private String value;
    /**
     * 权限name
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 权限类型（1 菜单权限 0  按钮 ）
     */
    private String type;


    /**
     * 处理查询条件
     */
    public SuperQuery getSuperQuery() {
        SuperQuery superQuery = new SuperQuery();
        String code = this.getCode();
        String type = this.getType();
        String value = this.getValue();
        String name = this.getName();
        Integer status = this.getStatus();
        Integer pageNum = this.getPageNum();
        Integer limit = this.getLimit();

        Page<SysShiroPermission> page = new Page(pageNum, limit);
        EntityWrapper<SysShiroPermission> wrapper = new EntityWrapper<SysShiroPermission>();
        if (StringUtils.isNotBlank(code)) {
            wrapper.eq("code", code);
        }
        if (StringUtils.isNotBlank(type)) {
            wrapper.eq("type", type);
        }
        if (StringUtils.isNotBlank(value)) {
            wrapper.eq("value", value);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq("name", name);
        }
        if (null != status && !status.equals(-1)) {
            wrapper.eq("status", status);
        }
        superQuery.setPage(page);
        superQuery.setWrapper(wrapper);
        return superQuery;
    }
}
