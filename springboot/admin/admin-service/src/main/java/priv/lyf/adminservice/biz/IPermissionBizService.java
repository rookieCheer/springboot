package priv.lyf.adminservice.biz;

import priv.lyf.adminservice.entity.form.PermissionForm;
import priv.lyf.adminservice.entity.query.PermissionQuery;
import priv.lyf.adminservice.util.Result;

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
