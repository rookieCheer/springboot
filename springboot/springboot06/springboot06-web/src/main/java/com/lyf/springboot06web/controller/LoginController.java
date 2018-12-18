package com.lyf.springboot06web.controller;

import com.lyf.springboot06service.util.VerifyCode;
import com.lyf.springboot06service.util.VerifyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

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
        return "/l/index";
    }


    /**
     * 默认页
     *
     * @return
     */
    @RequestMapping("/console")
    public String console() {
        return "/l/console";
    }


    /**
     * post登陆
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(String username, String password, String verify, Model model, HttpServletRequest request) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        HttpSession session = request.getSession();
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            String imageCode = session.getAttribute("imageCode") + "";
            if (verify != null && verify.equalsIgnoreCase(imageCode)) {
                subject.login(usernamePasswordToken);
                return "/l/index";
            } else {
                model.addAttribute("message", "验证码错误!");
            }
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("message", "密码错误");
        } catch (UnknownAccountException uae) {
            model.addAttribute("message", uae.getMessage());
        } catch (LockedAccountException lae) {
            model.addAttribute("message", lae.getMessage());
        } catch (ExcessiveAttemptsException eae) {
            model.addAttribute("message", "次数超出");
        }
        return "/l/login";
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            subject.logout();
        }
        return "/l/login";
    }

    /**
     * 拦截器 没有登录时  跳转页面  不然404
     *
     * @return
     */
    @RequestMapping("/loginForm")
    public String loginForm() {
        return "/l/login";
    }

    /**
     * 两套 生成验证码  随机
     *
     * @param response
     * @param request
     * @throws Exception
     */
    @GetMapping("/skip/getcode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Random random = new Random();
        int i = random.nextInt(2) + 1;
        switch (i) {
//            带背景
            case 1:
                //利用图片工具生成图片
                //第一个参数是生成的验证码，第二个参数是生成的图片
                Object[] objs = VerifyUtil.createImage();
                //将验证码存入Session
                session.setAttribute("imageCode", objs[0]);
                //将图片输出给浏览器
                BufferedImage image = (BufferedImage) objs[1];
                response.setContentType("image/png");
                OutputStream os = response.getOutputStream();
                ImageIO.write(image, "png", os);
                break;
            //不带背景
            case 2:
                //创建对象
                VerifyCode vc = new VerifyCode();
                //获取图片对象
                BufferedImage bi = vc.getImage();
                //获得图片的文本内容
                String text = vc.getText();
                // 将系统生成的文本内容保存到session中
                session.setAttribute("imageCode", text);
                //向浏览器输出图片
                VerifyCode.output(bi, response.getOutputStream());
                break;
            default:
                //创建对象
                VerifyCode vcd = new VerifyCode();
                //获取图片对象
                BufferedImage bid = vcd.getImage();
                //获得图片的文本内容
                String imageCode = vcd.getText();
                // 将系统生成的文本内容保存到session中
                session.setAttribute("imageCode", imageCode);
                //向浏览器输出图片
                VerifyCode.output(bid, response.getOutputStream());
                break;
        }
    }


//    /**
//     * 没有权限页面跳转
//     * @return
//     */
//    @RequestMapping("/unauthorized")
//    public String unauthorized() {
//        return "/l/unauthorized";
//    }
}
