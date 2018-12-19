package com.lyf.springboot07service.entity.query;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

@Data
public class SuperQuery {

    private Page page;
    private EntityWrapper wrapper;

}
