package com.lyf.springboot06service.entity.query;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.SysShiroUser;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class UserQuery extends Pageable {

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 角色code
     */
    private String roleCode;

    /**
     * 处理查询条件
     */
    public SuperQuery getSuperQuery() {
        SuperQuery superQuery = new SuperQuery();
        String account = this.getAccount();
        String roleCode = this.getRoleCode();
        String name = this.getName();
        Integer status = this.getStatus();
        Integer pageNum = this.getPageNum();
        Integer limit = this.getLimit();
        Page<SysShiroUser> page = new Page<SysShiroUser>(pageNum, limit);
        EntityWrapper<SysShiroUser> wrapper = new EntityWrapper<SysShiroUser>();
        if (StringUtils.isNotBlank(account)) {
            wrapper.eq("account", account);
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
