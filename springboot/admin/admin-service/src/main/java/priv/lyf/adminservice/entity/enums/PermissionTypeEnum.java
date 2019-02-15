package priv.lyf.adminservice.entity.enums;


/**
 * @Description: 权限类型枚举
 * @Author: xxx
 * @CreateDate: 2018/10/31 14:17
 * @Version: 1.0
 */
public enum PermissionTypeEnum {

    BUTTON("0", "按钮"),
    MENU("1", "菜单权限"),
    ;


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

    PermissionTypeEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public static PermissionTypeEnum getByValue(String value) {
        for (PermissionTypeEnum enu : values()) {
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
