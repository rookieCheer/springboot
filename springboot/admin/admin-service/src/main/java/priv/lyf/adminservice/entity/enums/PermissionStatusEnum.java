package priv.lyf.adminservice.entity.enums;


/**
 * @Description: 权限状态枚举
 * @Author: xxx
 * @CreateDate: 2018/10/31 14:17
 * @Version: 1.0
 */
public enum PermissionStatusEnum {

    USABLE(1, "可用"),
    DISABLE(0, "不可用"),
    ;


    /**
     * 成员变量
     */
    private Integer value;

    /**
     * 结果枚举信息
     */
    private String message;

    /**
     * 默认构造方法
     *
     * @param value 枚举值
     */

    PermissionStatusEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public static PermissionStatusEnum getByValue(Integer value) {
        for (PermissionStatusEnum enu : values()) {
            if (enu.getValue().equals(value)) {
                return enu;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
