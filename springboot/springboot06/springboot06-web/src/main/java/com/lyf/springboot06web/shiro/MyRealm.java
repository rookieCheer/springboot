package com.lyf.springboot06web.shiro;

import com.lyf.base.entity.SysShiroPermission;
import com.lyf.base.entity.SysShiroRole;
import com.lyf.springboot06service.biz.IUserBizService;
import com.lyf.springboot06service.entity.bo.UserBo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 这个类是参照JDBCRealm写的，主要是自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserBizService userBizServiceImpl;


    /**
     * 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserBo userBo  = (UserBo)principalCollection.getPrimaryPrincipal();
        for(SysShiroRole role:userBo.getSysShiroRoleList()){
            authorizationInfo.addRole(role.getValue());
        }
        //用户具有角色对应的权限值集合
        for(SysShiroPermission permission:userBo.getSysShiroPermissionList()){
            authorizationInfo.addStringPermission(permission.getValue());
        }
        return authorizationInfo;
    }

    /**
     * 定义如何获取用户信息的业务逻辑，给shiro做登录
     *
     * @param authenticationToken authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        //获取用户的输入的账号.
        String account = (String)authenticationToken.getPrincipal();
        UserBo userBo = userBizServiceImpl.loginUser(account);
        if (null == userBo) {
            throw new UnknownAccountException("用户不存在!");
        }
        if (!userBo.getStatus().equals("0")) {
            throw new LockedAccountException("用户被锁定");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名  用户对象
                userBo,
                //密码
                userBo.getPwd(),
                //salt=username+salt  获取字节数组
                ByteSource.Util.bytes(userBo.getSalt()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }
}
