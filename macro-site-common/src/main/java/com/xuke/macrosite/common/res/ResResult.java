package com.xuke.macrosite.common.res;

import com.xuke.macrosite.common.enums.ResCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xuke on 2020/9/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResResult<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> ResResult<T> success(String message, T data) {
        return new ResResult<T>(ResCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ResResult<T> success(T data) {
        return success(ResCode.SUCCESS.getMessage(), data);
    }

    public static <T> ResResult<T> success() {
        return success(ResCode.SUCCESS.getMessage(), null);
    }

    public static <T> ResResult<T> failed(int code, String message) {
        return new ResResult<T>(code, message, null);
    }

//    public static <T> ResResult<T> failed(int code) {
//        return failed(code, );
//    }
//
//    public static <T> ResResult<T> failed(String message) {
//        return failed(HttpCode.fail);
//    }

//    public static <T> ResResult<T> badRequest(String message) {
//        return new <T>ResResult(HttpCode.HTTP_BAD_REQUEST, message, null);
//    }
//
//    public static <T> ResResult<T> validateFailed() {
//        return failed(HttpCode.HTTP_BAD_REQUEST);
//    }
//
//    public static <T> ResResult<T> validateFailed(String message) {
//        return failed(HttpCode.HTTP_BAD_REQUEST, message);
//    }
//
//    public static <T> ResResult<T> unauthorized(T data) {
//        return new ResResult<T>(HttpCode.HTTP_UNAUTHORIZED, HttpCode.msg(HttpCode.HTTP_UNAUTHORIZED), data);
//    }
//
//    public static <T> ResResult<T> forbidden(T data) {
//        return new ResResult<T>(HttpCode.HTTP_FORBIDDEN, HttpCode.msg(HttpCode.HTTP_FORBIDDEN), data);
//    }
}
