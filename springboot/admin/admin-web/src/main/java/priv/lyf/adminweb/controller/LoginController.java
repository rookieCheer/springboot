package priv.lyf.adminweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "/layuiadmin/index";
    }


    /**
     * 默认页
     *
     * @return
     */
    @RequestMapping("/console")
    public String console() {
        return "/layuiadmin/console";
    }
}
