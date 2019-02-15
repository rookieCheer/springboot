
package priv.lyf.adminservice.biz.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.lyf.admindao.entity.SysShiroPermission;
import priv.lyf.admindao.entity.SysShiroUser;
import priv.lyf.admindao.service.ISysShiroPermissionService;
import priv.lyf.admindao.service.ISysShiroUserService;
import priv.lyf.adminservice.biz.IPermissionBizService;
import priv.lyf.adminservice.entity.enums.PermissionStatusEnum;
import priv.lyf.adminservice.entity.enums.PermissionTypeEnum;
import priv.lyf.adminservice.entity.form.PermissionForm;
import priv.lyf.adminservice.entity.query.PermissionQuery;
import priv.lyf.adminservice.entity.query.SuperQuery;
import priv.lyf.adminservice.entity.vo.PermissionVo;
import priv.lyf.adminservice.util.RelativeDateFormat;
import priv.lyf.adminservice.util.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PermissionBizServiceImpl implements IPermissionBizService {

    @Autowired
    private ISysShiroPermissionService sysShiroPermissionServiceImpl;


    @Autowired
    private ISysShiroUserService sysShiroUserServiceImpl;

    /**
     * 查询权限信息
     *
     * @return
     */
    @Override
    public Result search(PermissionQuery query) {
        SuperQuery superQuery = query.getSuperQuery();
        //查询
        Page<SysShiroPermission> page = sysShiroPermissionServiceImpl.selectPage(superQuery.getPage(), superQuery.getWrapper());
        List<PermissionVo> resultList = new ArrayList<>();
        page.getRecords().forEach(x -> {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtils.copyProperties(x, permissionVo);
            String statusName = "未知";
            String typeName = "未知";

            PermissionStatusEnum statusEnum = PermissionStatusEnum.getByValue(x.getStatus());
            if (null != statusEnum) {
                statusName = statusEnum.getMessage();
            }
            PermissionTypeEnum typeEnum = PermissionTypeEnum.getByValue(x.getType()+"");
            if (null != typeEnum) {
                typeName = typeEnum.getMessage();
            }
            permissionVo.setStatusName(statusName);
            permissionVo.setTypeName(typeName);
            resultList.add(permissionVo);
        });
        return Result.successPage(resultList, page.getTotal());
    }


    /**
     * 通过code查询权限信息
     *
     * @return
     */
    @Override
    public Result searchByCode(String code) {
        Map<String, String> userNameMap = sysShiroUserServiceImpl.selectList(null).stream().collect(Collectors.toMap(SysShiroUser::getCode, SysShiroUser::getName));
        //查询
        List<SysShiroPermission> sysShiroPermissions = sysShiroPermissionServiceImpl.selectList(new EntityWrapper<SysShiroPermission>().eq("code", code));
        if (sysShiroPermissions.size() == 0) {
            return Result.error("没有此权限信息");
        }
        SysShiroPermission sysShiroPermission = sysShiroPermissions.get(0);
        PermissionVo permissionVo = new PermissionVo();
        BeanUtils.copyProperties(sysShiroPermission, permissionVo);

        String updateName;
        String createName;
        String statusName = "未知";
        String typeName = "未知";
        //创建人
        createName = userNameMap.get(sysShiroPermission.getCreateCode());
        if (StringUtils.isBlank(createName)) {
            createName = "未知";
        }
        //修改人
        updateName = userNameMap.get(sysShiroPermission.getUpdateCode());
        if (StringUtils.isBlank(updateName)) {
            updateName = "未知";
        }
        PermissionStatusEnum statusEnum = PermissionStatusEnum.getByValue(sysShiroPermission.getStatus());
        if (null != statusEnum) {
            statusName = statusEnum.getMessage();
        }
        PermissionTypeEnum typeEnum = PermissionTypeEnum.getByValue(sysShiroPermission.getType()+"");
        if (null != typeEnum) {
            typeName = typeEnum.getMessage();
        }
        permissionVo.setStatusName(statusName);
        permissionVo.setTypeName(typeName);
        permissionVo.setUpdateTime(RelativeDateFormat.format(sysShiroPermission.getUpdateTime()));
        permissionVo.setCreateTime(RelativeDateFormat.format(sysShiroPermission.getCreateTime()));
        permissionVo.setCreateName(createName);
        permissionVo.setUpdateName(updateName);
        return Result.success(permissionVo);
    }

    /**
     * 保存权限信息
     *
     * @return
     */
    @Override
    public Result saveInfo(PermissionForm permissionForm) {
        boolean result;
        Date date = new Date();
        SysShiroPermission permission = new SysShiroPermission();
//        code  name  parentCode  parentCodes value path status type
        BeanUtils.copyProperties(permissionForm, permission);
        String code = permission.getCode();
        if (StringUtils.isNotBlank(code)) {
            permission.setUpdateTime(date);
            //@TODO 修改人
            permission.setUpdateCode("");
            result = sysShiroPermissionServiceImpl.update(permission, new EntityWrapper<SysShiroPermission>().eq("code", code));
        } else {
            //没有加密
            //系统毫秒
            long l = System.currentTimeMillis();
            String s = String.valueOf(l);
            permission.setCode(s);
            permission.setCreateCode("");
            permission.setUpdateCode("");
            permission.setCreateTime(date);
            permission.setUpdateTime(date);
            result = sysShiroPermissionServiceImpl.insert(permission);
        }
        if (result) {
            return Result.success();
        } else {
            return Result.error("未知错误");
        }
    }

}
