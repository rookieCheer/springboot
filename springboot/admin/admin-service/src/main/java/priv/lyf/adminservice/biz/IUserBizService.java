package priv.lyf.adminservice.biz;

import priv.lyf.admindao.entity.SysShiroRole;
import priv.lyf.adminservice.entity.form.UserForm;
import priv.lyf.adminservice.entity.query.UserQuery;
import priv.lyf.adminservice.util.Result;

import java.util.List;
import java.util.Map;


public interface IUserBizService {

    /**
     * 查询用户信息
     *
     * @param query
     * @return
     */
    Result getInfo(UserQuery query);

    /**
     * 查询用户信息 通过id
     *
     * @return
     */
    Result getUserInfoByCode(String code);

    /**
     * 保存用户信息，修改或新增
     *
     * @param userForm
     * @return
     */
    Result saveUserInfo(UserForm userForm);

    /**
     * 删除用户信息
     *
     * @param ids
     * @return
     */
    Result delUserInfo(String[] ids);

    /**
     * 获取角色信息  code和name
     *
     * @param
     * @return
     */
    Map<String, String> roleCodeAndName();


    /**
     * 角色信息
     *
     * @return
     */
    public List<SysShiroRole> getRoles();
}
