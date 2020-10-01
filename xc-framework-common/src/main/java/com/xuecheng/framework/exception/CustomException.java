package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @Author Mr zhu
 * @Date 2020/9/8 18:58
 */
public class CustomException extends RuntimeException {

    ResultCode resultCode;

    public CustomException(ResultCode resultCode) {

        this.resultCode = resultCode;

    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
