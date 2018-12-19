package com.lyf.springboot08.controller;

import com.lyf.springboot08.pojo.User;
import com.lyf.springboot08.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
    @RequestMapping("/user/{id}/{name}")
    public User updateUser(@PathVariable int id, @PathVariable String name) {
        User user = new User(id, name);
        return userService.updateUser(user);
    }

}
