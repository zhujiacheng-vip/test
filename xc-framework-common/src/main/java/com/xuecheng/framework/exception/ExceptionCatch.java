package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author Mr zhu
 * @Date 2020/9/8 19:06
 */
//控制器
@ControllerAdvice
public class ExceptionCatch {

    //定义日志
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCast.class);

    //定义异常map,数据存储进去不可更改,只可以读
    //private static final ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //捕获CustomException次类的异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException customException){

        //记录日志
        LOGGER.error("时间:" + new Date() + "\ncatch Exception:{};exception:", customException.getMessage(), customException);

        ResultCode resultCode = customException.getResultCode();

        return new ResponseResult(resultCode);

    }

    //捕获CustomException次类的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception Exception){

        //记录日志
        LOGGER.error("时间:" + new Date() + "\ncatch Exception:{};exception:", Exception.getMessage(), Exception);

        return new ResponseResult(CommonCode.SERVER_ERROR);

    }

}
