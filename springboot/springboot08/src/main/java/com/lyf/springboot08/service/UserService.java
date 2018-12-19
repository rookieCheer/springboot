package com.lyf.springboot08.service;

import com.lyf.springboot08.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@CacheConfig(cacheNames = {"myuser"})
public class UserService {

    public static final Map<Integer, User> users = new HashMap<>();

    static {
        users.put(1, new User(1,"我是快乐鱼"));
        users.put(2, new User(2,"我是忧郁猫"));
        users.put(3, new User(3,"我是昴先生"));
    }

    @CachePut(key = "#user.id")
    public User updateUser(User user) {
        users.put(user.getId(), user);
        return user;
    }


    @Cacheable(key = "#id")
    public User getUser(int id) {
        log.info("缓存中没有，从map中获取");
        User user = users.get(id);
        return user;
    }


    /**
     * 清除一条缓存，key为要清空的数据
     * @param id
     */
    @CacheEvict(value = "user", key = "#id")
    public void delect(int id) {
        users.remove(id);
    }

    /**
     * 方法调用后清空所有缓存
     */
    @CacheEvict(value = "accountCache", allEntries = true)
    public void delectAll() {
        users.clear();
    }

    /**
     * 方法调用前清空所有缓存
     */
    @CacheEvict(value = "accountCache", beforeInvocation = true)
    public void delectAllBefore() {
        users.clear();
    }


}

