package com.lyf.springboot02service.biz;

import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.SysShiroPermission;

import java.util.List;

public interface IPermissionBizService {


    List<SysShiroPermission> userPermission(String userCode);

    Page<SysShiroPermission> getPermission(Integer pageNum);
    SysShiroPermission getPermissionById(String code);
}
