package priv.lyf.adminservice.biz;

import priv.lyf.adminservice.entity.form.RoleForm;
import priv.lyf.adminservice.entity.query.RoleQuery;
import priv.lyf.adminservice.util.Result;

public interface IRoleBizService {
    /**
     * 查询角色信息
     *
     * @param query
     * @return
     */
    Result search(RoleQuery query);

    /**
     * 查询角色信息 通过id
     *
     * @return
     */
    Result searchByCode(String code);

    /**
     * 保存角色信息，修改或新增
     *
     * @return
     */
    Result saveInfo(RoleForm roleForm);

    /**
     * 删除角色信息
     *
     * @param ids
     * @return
     */
    Result delRoles(String[] ids);

    /**
     * 通过角色code获取权限
     *
     * @param code
     * @return
     */
    Result getPermission(String code);

    /**
     * 给角色授权
     *
     * @param code
     * @param status
     * @param roleCode
     * @return
     */
    Result checked(String code, Boolean status, String roleCode);
}
