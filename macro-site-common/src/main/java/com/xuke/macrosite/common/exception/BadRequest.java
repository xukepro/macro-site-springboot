package com.xuke.macrosite.common.exception;

import com.xuke.macrosite.common.enums.ResCode;
import lombok.Data;

/**
 * Created by xuke on 2020/10/14
 */
@Data
public class BadRequest extends RuntimeException {
    protected Integer code;
    protected String message;

    public BadRequest(ResCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
