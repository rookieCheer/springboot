package com.lyf.springboot06service.util;

import com.lyf.springboot06service.entity.enums.RoleStatusEnum;

public class MyEnumUtil {

    /**
     * 角色状态
     * @param status
     * @return
     */
    public static String getRoleStatusText(Integer status) {
        RoleStatusEnum byValue = RoleStatusEnum.getByValue(status);
        if (byValue == null) {
            return"未知";
        }
        return byValue.getMessage();
    }
}