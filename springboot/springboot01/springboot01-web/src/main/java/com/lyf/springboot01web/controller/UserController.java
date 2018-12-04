package com.lyf.springboot01web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
* @Description:    用户页面相关的控制器
* @Author:         xxx
* @CreateDate:     2018/11/9 17:08
* @Version:        1.0
*/
@Controller
public class UserController {



    //分页查询所有的用户（用户状态，用户关联的角色，时间）
    //通过id或code查询用户
    //修改用户信息
    //保存用户信息

    //(发布动态)
    @ResponseBody
    @RequestMapping(value = "/addText")
    public void addText(MultipartFile file) {
        String sql1 = "INSERT INTO `hdkj-payment`.`pdd_user_info` (`account`, `password`, `status`, `expire_date`) VALUES ('";
        String sql2 ="', '";
        String sql3 =" '0', '2018-11-14 16:44:48');";
        StringBuilder result = new StringBuilder();
        //  Map result=new HashMap();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = null;
            //使用readLine方法，一次读一行
            while ((s = reader.readLine()) != null) {
                String[] split = s.split("----");
                if (split.length==2){
                    String s1 = split[0];
                    String s2 = split[1];
                    System.out.println(sql1+s1+sql2+s2+sql3);
                }
                result.append(s + "/n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
