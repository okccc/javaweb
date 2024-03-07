package com.okccc.common;

import lombok.Getter;

/**
 * @Author: okccc
 * @Date: 2023/12/13 10:31:29
 * @Desc: 状态码和业务含义对应关系的枚举
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    FAIL(500, "fail"),
    USERNAEM_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    USERNAME_USED(505,"usernameUsed");

    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message){
        this.code= code;
        this.message = message;
    }

}
