package com.lyf.springboot08.controller;

import com.lyf.springboot08.pojo.User;
import com.lyf.springboot08.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "用户增删改查api")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户", httpMethod = "POST")
    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定修改用户", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "这个id是用户的id", paramType = "path")
    @RequestMapping("/user/{id}/{name}")
    public User updateUser(@PathVariable int id, @PathVariable String name) {
        User user = new User(id, name);
        return userService.updateUser(user);
    }

}
