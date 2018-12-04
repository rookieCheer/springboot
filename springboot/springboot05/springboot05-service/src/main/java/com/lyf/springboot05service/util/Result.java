package com.lyf.springboot05service.util;


import java.io.Serializable;

/**
 * @Description: 返回封装
 * @Author: xxx
 * @CreateDate: 2018/10/16 13:37
 * @Version: 1.0
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3951187350403816092L;

    /**
     * 返回信息
     */
    private String msg;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状数量
     */
    private Integer count;
    /**
     * 数据
     */
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "成功";
        this.data = data;
    }

    private Result(String msg) {
        this.code = 1;
        this.msg = msg;
        this.data = null;
    }

    //分页
    private Result(T data, Integer count) {
        this.code = 0;
        this.msg = "成功";
        this.data = data;
        this.count = count;
    }

    private Result(String msg, Integer count) {
        this.code = 1;
        this.msg = msg;
        this.data = null;
        this.count = count;
    }


    /**
     * 成功时候的调用
     *
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功，不需要传入参数
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success() {
        return (Result<T>) success("");
    }

    /**
     * 失败时候的调用
     *
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return new Result<T>(msg);
    }


    /**
     * 分页成功时候的调用
     *
     * @return
     */
    public static <T> Result<T> successPage(T data, Integer count) {
        return new Result<T>(data, count);
    }

    /**
     * 分页 失败时候的调用
     *
     * @return
     */
    public static <T> Result<T> errorPage(String msg) {
        return new Result<T>(msg, 0);
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public Integer getCount() {
        return count;
    }

    public T getData() {
        return data;
    }
}