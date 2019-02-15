package priv.lyf.adminweb.controller.sys;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.lyf.adminservice.biz.IPermissionBizService;
import priv.lyf.adminservice.entity.form.PermissionForm;
import priv.lyf.adminservice.entity.query.PermissionQuery;
import priv.lyf.adminservice.util.Result;

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
    @RequestMapping("/000003/000001")
    public String searchSkip() {
        return "/layuiadmin/sys/permission/permission-list";
    }


    /**
     * 查询权限信息
     *
     * @return
     */
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
