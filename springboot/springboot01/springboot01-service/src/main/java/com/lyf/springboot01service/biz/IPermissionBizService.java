package com.lyf.springboot01service.biz;

import com.baomidou.mybatisplus.plugins.Page;
import com.lyf.base.entity.SysShiroPermission;

public interface IPermissionBizService {

    Page<SysShiroPermission> getPermission(Integer pageNum);
    SysShiroPermission getPermissionById(String code);
}
