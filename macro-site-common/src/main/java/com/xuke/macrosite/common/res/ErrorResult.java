package com.xuke.macrosite.common.res;

import com.xuke.macrosite.common.enums.ResCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xuke on 2020/10/14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {
    private Integer status;
    private String message;
    private String exception;

    public static ErrorResult fail(ResCode resCode, Throwable e, String message) {
        ErrorResult result = ErrorResult.fail(resCode, e);
        result.setMessage(message);
        return result;
    }

    public static ErrorResult fail(ResCode resCode, String message) {
        ErrorResult result = new ErrorResult();
        result.setMessage(message);
        result.setStatus(resCode.getCode());
        return result;
    }

    public static ErrorResult fail(ResCode resCode, Throwable e) {
        ErrorResult result = new ErrorResult();
        result.setMessage(resCode.getMessage());
        result.setStatus(resCode.getCode());
        result.setException(e.getClass().getName());
        return result;
    }
}
