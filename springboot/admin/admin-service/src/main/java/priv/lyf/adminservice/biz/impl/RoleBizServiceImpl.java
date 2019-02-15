
package priv.lyf.adminservice.biz.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.lyf.admindao.entity.SysShiroPermission;
import priv.lyf.admindao.entity.SysShiroRole;
import priv.lyf.admindao.entity.SysShiroRolePermission;
import priv.lyf.admindao.entity.SysShiroUser;
import priv.lyf.admindao.service.ISysShiroPermissionService;
import priv.lyf.admindao.service.ISysShiroRolePermissionService;
import priv.lyf.admindao.service.ISysShiroRoleService;
import priv.lyf.admindao.service.ISysShiroUserService;
import priv.lyf.adminservice.biz.IRoleBizService;
import priv.lyf.adminservice.entity.enums.RoleStatusEnum;
import priv.lyf.adminservice.entity.form.RoleForm;
import priv.lyf.adminservice.entity.query.RoleQuery;
import priv.lyf.adminservice.entity.query.SuperQuery;
import priv.lyf.adminservice.entity.vo.RolePermissionVo;
import priv.lyf.adminservice.entity.vo.RoleVo;
import priv.lyf.adminservice.util.RelativeDateFormat;
import priv.lyf.adminservice.util.Result;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleBizServiceImpl implements IRoleBizService {

    /**
     * 角色
     */
    @Autowired
    private ISysShiroRoleService sysShiroRoleServiceImpl;
    /**
     * 角色权限表
     */
    @Autowired
    private ISysShiroRolePermissionService sysShiroRolePermissionServiceImpl;
    /**
     * 角色权限表
     */
    @Autowired
    private ISysShiroPermissionService sysShiroPermissionServiceImpl;
    @Autowired
    private ISysShiroUserService sysShiroUserServiceImpl;


    /**
     * 查询角色信息
     * 思路 开始想把查询出来的数据放到缓存中去  减少查询数据库的次数
     * 缺点 放到缓存  每次修改之后 没有实时更新
     *
     * @param query
     * @return
     */
    @Override
    public Result search(RoleQuery query) {
        Map<String, List<SysShiroRolePermission>> roleMap = sysShiroRolePermissionServiceImpl.selectList(null).stream().collect(Collectors.groupingBy(SysShiroRolePermission::getRoleCode));
        Map<String, String> permissionMap = sysShiroPermissionServiceImpl.selectList(null).stream().collect(Collectors.toMap(SysShiroPermission::getCode, SysShiroPermission::getName));
        SuperQuery superQuery = query.getSuperQuery();
        //查询
        Page<SysShiroRole> page = sysShiroRoleServiceImpl.selectPage(superQuery.getPage(), superQuery.getWrapper());
        List<RoleVo> resultList = new ArrayList();
        page.getRecords().forEach(x -> {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(x, roleVo);
//            List<RoleVo.Permission> permissionList = new ArrayList();

//            roleMap.get(x.getCode()).forEach(y -> {
//                RoleVo.Permission permission = new RoleVo().new Permission();
//                String permissionCode = y.getPermissionCode();
//                String permissionName = permissionMap.get(permissionCode);
//                permission.setCode(permissionCode);
//                permission.setName(permissionName);
//                permissionList.add(permission);
//            });
            roleVo.setUpdateTime(RelativeDateFormat.format(x.getUpdateTime()));
            resultList.add(roleVo);
        });
        return Result.successPage(resultList, page.getTotal());
    }

    @Override
    public Result searchByCode(String code) {
        Map<String, String> userNameMap = sysShiroUserServiceImpl.selectList(null).stream().collect(Collectors.toMap(SysShiroUser::getCode, SysShiroUser::getName));
        //查询
        List<SysShiroRole> sysShiroRoles = sysShiroRoleServiceImpl.selectList(new EntityWrapper<SysShiroRole>().eq("code", code));
        if (sysShiroRoles.size() == 0) {
            return Result.error("没有此用户信息");
        }
        if (sysShiroRoles.size() > 1) {
            return Result.error("此用户信息多条，数据错误");
        }
        SysShiroRole sysShiroRole = sysShiroRoles.get(0);
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(sysShiroRole, roleVo);

        String updateName;
        String createName;
        String statusName = "未知";
        String typeName = "未知";
        //创建人
        createName = userNameMap.get(sysShiroRole.getCreateCode());
        if (StringUtils.isBlank(createName)) {
            createName = "未知";
        }
        //修改人
        updateName = userNameMap.get(sysShiroRole.getUpdateCode());
        if (StringUtils.isBlank(updateName)) {
            updateName = "未知";
        }
        RoleStatusEnum statusEnum = RoleStatusEnum.getByValue(sysShiroRole.getStatus());
        if (null != statusEnum) {
            statusName = statusEnum.getMessage();
        }
        roleVo.setStatusName(statusName);
        roleVo.setUpdateTime(RelativeDateFormat.format(sysShiroRole.getUpdateTime()));
        roleVo.setCreateTime(RelativeDateFormat.format(sysShiroRole.getCreateTime()));
        roleVo.setCreateName(createName);
        roleVo.setUpdateName(updateName);
        return Result.success(roleVo);
    }

    @Override
    public Result saveInfo(RoleForm roleForm) {
        boolean result;
        SysShiroRole sysShirorole = new SysShiroRole();
//        name  value  status  description
        BeanUtils.copyProperties(roleForm, sysShirorole);
        if (StringUtils.isNotBlank(roleForm.getCode())) {
            result = sysShiroRoleServiceImpl.update(sysShirorole, new EntityWrapper<SysShiroRole>().eq("code", roleForm.getCode()));
        } else {
            //系统毫秒
            long l = System.currentTimeMillis();
            String s = String.valueOf(l);
            Date date = new Date();
            sysShirorole.setCode(s);
            sysShirorole.setName(roleForm.getName());
            sysShirorole.setValue(roleForm.getValue());
            sysShirorole.setDescription(roleForm.getDescription());
            sysShirorole.setCreateTime(date);
            sysShirorole.setUpdateTime(date);
            result = sysShiroRoleServiceImpl.insert(sysShirorole);
        }
        if (result) {
            return Result.success();
        } else {
            return Result.error("未知错误");
        }
    }

    /**
     * 删除用户信息
     *
     * @param ids
     * @return
     */
    @Override
    public Result delRoles(String[] ids) {
        List<String> roleCodeList = Arrays.asList(ids);
        if (roleCodeList == null || roleCodeList.size() == 0) {
            return Result.error("无code！");
        }
        //@TODO 这里有问题  如果 角色没有对应的权限会不会报错！
        boolean b = sysShiroRoleServiceImpl.delete(new EntityWrapper<SysShiroRole>().in("code", roleCodeList));
        boolean c = sysShiroRolePermissionServiceImpl.delete(new EntityWrapper<SysShiroRolePermission>().in("role_code", roleCodeList));
        if (b && c) {
            return Result.success();
        } else {
            return Result.error("未知错误");
        }
    }

    /**
     * code 角色code
     *
     * @param code
     * @return
     */
    @Override
    public Result getPermission(String code) {
        List<SysShiroRolePermission> sysShiroRolePermission = sysShiroRolePermissionServiceImpl.selectList(new EntityWrapper<SysShiroRolePermission>().eq("role_code", code));
        List<String> permissionCodes = sysShiroRolePermission.stream().map(SysShiroRolePermission::getPermissionCode).collect(Collectors.toList());
        List<SysShiroPermission> sysShiroPermissions = sysShiroPermissionServiceImpl.selectList(null);
        List<RolePermissionVo> result = new ArrayList();
        sysShiroPermissions.forEach(x -> {
            RolePermissionVo rolePermissionVo = new RolePermissionVo();
            if (permissionCodes.contains(x.getCode())) {
                rolePermissionVo.setChecked("1");
            } else {
                rolePermissionVo.setChecked("0");
            }
            rolePermissionVo.setRoleCode(code);
            rolePermissionVo.setName(x.getName());
            rolePermissionVo.setStatus(x.getStatus());
            rolePermissionVo.setCode(x.getCode());
            result.add(rolePermissionVo);
        });
        return Result.success(result);
    }

    /**
     * code 角色code
     *
     * @param code
     * @return
     */
    @Override
    public Result checked(String code, Boolean status, String roleCode) {
        SysShiroRolePermission sysShiroRolePermission = sysShiroRolePermissionServiceImpl.selectOne(new EntityWrapper<SysShiroRolePermission>().eq("permission_code", code)
                .eq("role_code", roleCode));
        Boolean result;
        if (null != sysShiroRolePermission) {
            result = sysShiroRolePermissionServiceImpl.deleteById(sysShiroRolePermission);
        } else {
            sysShiroRolePermission = new SysShiroRolePermission();
            Date date = new Date();
            sysShiroRolePermission.setCreateCode("");
            sysShiroRolePermission.setCreateTime(date);
            sysShiroRolePermission.setUpdateCode("");
            sysShiroRolePermission.setUpdateTime(date);
            sysShiroRolePermission.setPermissionCode(code);
            sysShiroRolePermission.setRoleCode(roleCode);
            result = sysShiroRolePermissionServiceImpl.insert(sysShiroRolePermission);
        }
        if (result) {
            return Result.success();
        } else {
            return Result.error("位置异常");
        }

    }

}
