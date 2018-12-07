package com.lyf.springboot06web.controller.sys;


import com.lyf.springboot06service.biz.IPermissionBizService;
import com.lyf.springboot06service.entity.form.PermissionForm;
import com.lyf.springboot06service.entity.query.PermissionQuery;
import com.lyf.springboot06service.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PermissionController {


    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private IPermissionBizService permissionBizServiceImpl;

    /**
     * 跳转到查询页面
     *
     * @return
     */
    @RequiresPermissions({"/000003/000001"})
    @RequestMapping("/000003/000001")
    public String searchSkip() {
        return "/layuiadmin/sys/permission/permission-list";
    }


    /**
     * 查询权限信息
     *
     * @return
     */
    @RequiresPermissions({"/000003/000002"})
    @RequestMapping("/000003/000002")
    @ResponseBody
    public Result search(PermissionQuery query) {
        logger.info("查询权限信息  query={}", query);
        Result search;
        try {
            search = permissionBizServiceImpl.search(query);
        } catch (Exception e) {
            logger.error("查询权限信息  【异常】 e={}", e);
            search = Result.error("捕获错误");
        }
        return search;
    }

    /**
     * 通过id查询
     *
     * @return
     */
    @RequiresPermissions({"/000003/000003"})
    @RequestMapping("/000003/000003")
    @ResponseBody
    public Result searchByCode(String code) {
        logger.info("通过code查询权限信息  code={}", code);
        Result search;
        try {
            search = permissionBizServiceImpl.searchByCode(code);
        } catch (Exception e) {
            logger.error("查询权限信息  【异常】 e={}", e);
            search = Result.error("捕获错误");
        }
        return search;
    }

    /**
     * 保存信息   修改/新增
     *
     * @return
     */
    @RequiresPermissions({"/000003/000004"})
    @RequestMapping("/000003/000004")
    @ResponseBody
    public Result saveInfo(PermissionForm permissionForm) {
        logger.info("保存权限信息  permissionForm={}", permissionForm);
        Result search;
        try {
            search = permissionBizServiceImpl.saveInfo(permissionForm);
        } catch (Exception e) {
            logger.error("查询权限信息  【异常】 e={}", e);
            search = Result.error("捕获错误");
        }
        return search;
    }

}
