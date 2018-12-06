package com.lyf.springboot06service.biz;


import com.lyf.base.entity.SysShiroRole;
import com.lyf.springboot06service.entity.bo.UserBo;
import com.lyf.springboot06service.entity.form.UserForm;
import com.lyf.springboot06service.entity.query.UserQuery;
import com.lyf.springboot06service.util.Result;

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


    /**
     * 通过账号查用户信息
     * @param account
     * @return
     */
    UserBo loginUser(String account);
}
