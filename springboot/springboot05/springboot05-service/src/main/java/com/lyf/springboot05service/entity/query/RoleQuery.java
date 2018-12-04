package com.lyf.springboot05service.entity.query;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.SysShiroPermission;
import com.lyf.base.entity.SysShiroRole;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class RoleQuery extends Pageable {

    /**
     * 角色code
     */
    private String code;
    /**
     * 角色值
     */
    private String value;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 处理查询条件
     */
    public SuperQuery getSuperQuery() {
        SuperQuery superQuery = new SuperQuery();
        String code = this.getCode();
        String value = this.getValue();
        Integer status = this.getStatus();
        Integer pageNum = this.getPageNum();
        Integer limit = this.getLimit();

        Page<SysShiroRole> page = new Page<SysShiroRole>(pageNum, limit);
        EntityWrapper<SysShiroPermission> wrapper = new EntityWrapper<SysShiroPermission>();
        if (StringUtils.isNotBlank(code)) {
            wrapper.eq("code", code);
        }
        if (StringUtils.isNotBlank(value)) {
            wrapper.eq("value", value);
        }
        if (null != status && !status.equals(-1)) {
            wrapper.eq("status", status);
        }
        superQuery.setPage(page);
        superQuery.setWrapper(wrapper);
        return superQuery;
    }
}
