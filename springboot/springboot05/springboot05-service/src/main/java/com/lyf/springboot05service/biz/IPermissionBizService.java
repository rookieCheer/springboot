package com.lyf.springboot05service.biz;

import com.lyf.springboot05service.entity.form.PermissionForm;
import com.lyf.springboot05service.entity.query.PermissionQuery;
import com.lyf.springboot05service.util.Result;

public interface IPermissionBizService {

    /**
     * 查询权限信息
     *
     * @param query
     * @return
     */
    Result search(PermissionQuery query);

    /**
     * 查询code权限信息 通过id
     *
     * @return
     */
    Result searchByCode(String code);

    /**
     * 保存权限信息，修改或新增
     *
     * @param permissionForm
     * @return
     */
    Result saveInfo(PermissionForm permissionForm);
}
