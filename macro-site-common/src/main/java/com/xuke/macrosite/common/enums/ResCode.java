package com.xuke.macrosite.common.enums;

/**
 * Created by xuke on 2020/10/14
 */
public enum ResCode {

    SUCCESS(20000, "成功"),
    BAD_REQUEST(40000, "请求错误"),
    UNAUTHORIZED(40100, "未授权"),
    FORBIDDEN(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统异常"),
    SERVER_NOT_AVAILABLE(60000, "找不到服务器"),
    OFF_LINE(60001, "用户已下线"),
    TYPE_NOT_MATCH(60002, "类型不匹配");

    private Integer code;
    private String message;

    ResCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
