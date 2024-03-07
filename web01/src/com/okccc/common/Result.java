package com.okccc.common;

import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2023/12/13 10:28:17
 * @Desc: 统一响应的消息格式
 */
@Data
public class Result {

    // 返回码
    private Integer code;

    // 返回消息
    private String message;

    // 返回数据
    private Object data;

    public Result() {

    }

    public static Result build(ResultCodeEnum resultCodeNum) {
        Result result = new Result();
        result.setCode(resultCodeNum.getCode());
        result.setMessage(resultCodeNum.getMessage());
        return result;
    }

}
