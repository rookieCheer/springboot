package com.lyf.springboot05web.controller.sys;

import com.lyf.springboot05service.biz.IRoleBizService;
import com.lyf.springboot05service.entity.form.RoleForm;
import com.lyf.springboot05service.entity.query.RoleQuery;
import com.lyf.springboot05service.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {


    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleBizService roleBizServiceImpl;

    /**
     * 跳转到查询页面
     *
     * @return
     */
    @RequestMapping("/000002/000001")
    public String searchSkip() {
        return "/layuiadmin/sys/role/role-list";
    }


    /**
     * 查询角色信息
     *
     * @return
     */
    @RequestMapping("/000002/000002")
    @ResponseBody
    public Result search(RoleQuery query) {
        logger.info("查询角色信息  query={}", query);
        Result search;
        try {
            search = roleBizServiceImpl.search(query);
        } catch (Exception e) {
            logger.error("查询角色信息  【异常】 e={}", e);
            search = Result.error("捕获错误");
        }
        return search;
    }

    /**
     * 通过id查询
     *
     * @return
     */
    @RequestMapping("/000002/000003")
    @ResponseBody
    public Result searchByCode(String code) {
        logger.info("通过code查询角色信息  code={}", code);
        Result search;
        try {
            search = roleBizServiceImpl.searchByCode(code);
        } catch (Exception e) {
            logger.error("通过code查询角色信息  【异常】 e={}", e);
            search = Result.error("捕获错误");
        }
        return search;
    }

    /**
     * 保存信息   修改/新增
     *
     * @return
     */
    @RequestMapping("/000002/000004")
    @ResponseBody
    public Result saveInfo(RoleForm roleForm) {
        logger.info("保存角色信息  roleForm={}", roleForm);
        Result search;
        try {
            search = roleBizServiceImpl.saveInfo(roleForm);
        } catch (Exception e) {
            logger.error("保存角色信息  【异常】 e={}", e);
            search = Result.error("捕获错误");
        }
        return search;
    }

    /**
     * 删除用户信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/000002/000005")
    public Result delRoles(@RequestParam(value = "ids[]") String[] ids) {
        logger.info("删除角色信息  ids个数={}", ids.length);
        Result result;
        try {
            result = roleBizServiceImpl.delRoles(ids);
        } catch (Exception e) {
            logger.error("删除角色信息  【异常】 e={}", e);
            result = Result.error("捕获错误");
        }
        return result;
    }


    /**
     * 角色权限详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/000002/000006/{code}")
    public Result getPermission(@PathVariable(name = "code") String code) {
        logger.info("角色权限详情  code={}", code);
        Result result;
        try {
            result = roleBizServiceImpl.getPermission(code);
        } catch (Exception e) {
            logger.error("角色权限详情  【异常】 e={}", e);
            result = Result.error("捕获错误");
        }
        return result;
    }

    /**
     * 角色权限开通或关闭
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/000002/000007")
    public Result checked(String code, Boolean status, String roleCode) {
        logger.info("角色选择权限  code={}，status={}", code, status);
        Result result;
        try {
            result = roleBizServiceImpl.checked(code, status, roleCode);
        } catch (Exception e) {
            logger.error("角色权限详情  【异常】 e={}", e);
            result = Result.error("捕获错误");
        }
        return result;
    }

}
