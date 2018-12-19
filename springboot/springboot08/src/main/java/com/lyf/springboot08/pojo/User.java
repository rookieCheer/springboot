package com.lyf.springboot08.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "用户实体")
public class User implements Serializable {
    private Integer id;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
