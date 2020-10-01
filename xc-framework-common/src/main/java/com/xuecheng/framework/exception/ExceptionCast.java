package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @Author Mr zhu
 * @Date 2020/9/8 19:03
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode){

        throw new CustomException(resultCode);

    }

}
