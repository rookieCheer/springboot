package com.lyf.springboot06service.biz;

import com.lyf.springboot06service.entity.form.PermissionForm;
import com.lyf.springboot06service.entity.query.PermissionQuery;
import com.lyf.springboot06service.util.Result;

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
