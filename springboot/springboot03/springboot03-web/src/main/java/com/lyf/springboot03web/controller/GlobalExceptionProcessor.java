package com.lyf.springboot03web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 全局异常处理
 * @Author: xxx
 * @CreateDate: 2018/11/9 17:28
 * @Version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionProcessor {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionProcessor.class);


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> handleMissingServletRequestParameterException(RuntimeException e) {
        logger.error("运行异常", e);
        Map<String,Object> map = new HashMap<>();
        map.put("code","500");
        map.put("msg","系统出错了！");
        return map;
    }


//    /**
//     * 400 - Bad Request
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
//        logger.error("缺少请求参数", e);
//        return "缺少请求参数";
//    }
//    /**
//     * 400 - Bad Request
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        logger.error("参数解析失败", e);
//        return "参数解析失败";
//    }
//
//    /**
//     * 400 - Bad Request
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        logger.error("参数验证失败", e);
//        BindingResult result = e.getBindingResult();
//        FieldError error = result.getFieldError();
//        String field = error.getField();
//        String code = error.getDefaultMessage();
//        String message = String.format("%s:%s", field, code);
//        return "参数验证失败="+message;
//    }
//
//    /**
//     * 400 - Bad Request
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(BindException.class)
//    public String handleBindException(BindException e) {
//        logger.error("参数绑定失败", e);
//        BindingResult result = e.getBindingResult();
//        FieldError error = result.getFieldError();
//        String field = error.getField();
//        String code = error.getDefaultMessage();
//        String message = String.format("%s:%s", field, code);
//        return "参数绑定失败="+message;
//    }
//
//
//    /**
//     * 400 - Bad Request
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ConstraintViolationException.class)
//    public String handleServiceException(ConstraintViolationException e) {
//        logger.error("参数验证失败", e);
//        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//        ConstraintViolation<?> violation = violations.iterator().next();
//        String message = violation.getMessage();
//        return "参数验证失败" + message;
//    }
//
//    /**
//     * 400 - Bad Request
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ValidationException.class)
//    public String handleValidationException(ValidationException e) {
//        logger.error("参数验证失败", e);
//        return "参数验证失败";
//    }
//
//    /**
//     * 404 - Not Found
//     */
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public String noHandlerFoundException(NoHandlerFoundException e) {
//        logger.error("Not Found", e);
//        return "Not Found="+e;
//    }
//
//
//    /**
//     * 405 - Method Not Allowed
//     */
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//        logger.error("不支持当前请求方法", e);
//        return "request_method_not_supported";
//    }
//
//    /**
//     * 415 - Unsupported Media Type
//     */
//    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public String handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
//        logger.error("不支持当前媒体类型", e);
//        return "content_type_not_supported";
//    }


}
