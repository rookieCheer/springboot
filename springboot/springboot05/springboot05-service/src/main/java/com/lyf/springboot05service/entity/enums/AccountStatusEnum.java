package com.lyf.springboot05service.entity.enums;


/**
 * @Description: 账号状态枚举
 * @Author: xxx
 * @CreateDate: 2018/10/31 14:17
 * @Version: 1.0
 */
public enum AccountStatusEnum {
    USABLE("0", "可用"),
    UNCLAIMED("1", "待领取"),
    DISABLE("2", "不可用");


    /**
     * 成员变量
     */
    private String value;

    /**
     * 结果枚举信息
     */
    private String message;

    /**
     * 默认构造方法
     *
     * @param value 枚举值
     */

    AccountStatusEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public static AccountStatusEnum getByValue(String value) {
        for (AccountStatusEnum enu : values()) {
            if (enu.getValue().equals(value)) {
                return enu;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
