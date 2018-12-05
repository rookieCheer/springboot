package com.lyf.springboot06web.controller.sys;

import com.lyf.base.entity.SysShiroRole;
import com.lyf.base.service.ISysShiroUserService;
import com.lyf.springboot06service.biz.IUserBizService;
import com.lyf.springboot06service.entity.form.UserForm;
import com.lyf.springboot06service.entity.query.UserQuery;
import com.lyf.springboot06service.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户页面控制器
 * @Author: xxx
 * @CreateDate: 10:22 2018/11/26
 * @Version: 1.0
 */
@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserBizService userBizServiceImpl;

    @Autowired
    private ISysShiroUserService sysShiroUserServiceImpl;


    /**
     * 跳转到查询页面
     *
     * @return
     */
    @RequestMapping("/000000/000001")
    public String searchSkip(Model model) {
        List<SysShiroRole> roles = userBizServiceImpl.getRoles();
        model.addAttribute("roles", roles);
        return "/layuiadmin/sys/user/user-list";
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/000000/000002")
    public Result search(UserQuery query) {
        logger.info("查询用户信息  query={}", query);
        Result userInfo = userBizServiceImpl.getInfo(query);
        return userInfo;
    }

    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/000000/000004")
    public Result saveUserInfo(UserForm userForm) {
        logger.info("保存用户信息 userForm={}", userForm);
        Result result = userBizServiceImpl.saveUserInfo(userForm);
        return result;
    }


    /**
     * 查询用户信息 通过id
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/000000/000003")
    public Result search(String code, Model model) {
        logger.info("通过code查询用户信息 用户 code={}", code);
        Result userVo = userBizServiceImpl.getUserInfoByCode(code);
        Map<String, String> roleMap = userBizServiceImpl.roleCodeAndName();
        model.addAttribute("role", roleMap);
        return userVo;
    }


    /**
     * 删除用户信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/000000/000005")
    public Result delUsers(@RequestParam(value = "ids[]") String[] ids) {
        logger.info("删除用户信息  ids个数={}", ids.length);
        Result result = userBizServiceImpl.delUserInfo(ids);
        return result;
    }

}
