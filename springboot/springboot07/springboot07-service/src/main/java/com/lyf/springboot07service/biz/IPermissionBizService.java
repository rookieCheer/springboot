package com.lyf.springboot07service.biz;

import com.lyf.springboot07service.entity.form.PermissionForm;
import com.lyf.springboot07service.entity.query.PermissionQuery;
import com.lyf.springboot07service.util.Result;

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
