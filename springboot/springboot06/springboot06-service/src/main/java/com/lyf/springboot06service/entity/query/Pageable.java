package com.lyf.springboot06service.entity.query;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Pageable implements Serializable {

    /**
     * 数目.
     */
    private Integer limit = new Integer(10);

    /**
     * 页序号
     */
    private Integer pageNum = new Integer(0);

}
