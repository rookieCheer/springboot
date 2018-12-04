package com.lyf.base.superclass;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
* @Description:    父类
* @Author:         yc
* @CreateDate:     2018/10/11 17:26
* @Version:        1.0
*/
public class SuperEntity<T extends Model> extends Model<T> {
    private Long id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
