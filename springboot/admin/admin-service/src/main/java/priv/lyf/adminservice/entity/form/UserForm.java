package priv.lyf.adminservice.entity.form;

import lombok.Data;

import java.util.List;

@Data
public class UserForm {
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 账户状态  0 可用  1 待领取  2不可用
     */
    private String status;
    /**
     * 用户code
     */
    private String code;

    /**
     * 角色code
     */
    private List roleCode;

}
