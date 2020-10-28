package com.xuke.macrosite.common.exception;

import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.res.ErrorResult;
import com.xuke.macrosite.common.res.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuke on 2020/9/16
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理抛出的异常
     * @param e
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResult handleThrowable(Throwable e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.fail(ResCode.SYSTEM_ERROR, e);
        log.warn("URL: " + request.getRequestURI() + ", 服务器异常: " + error);
        e.printStackTrace();
        return error;
    }


    /**
     * 处理自定义业务异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ErrorResult handleBusinessException(BusinessException e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.builder().status(e.code)
                .message(e.message)
                .exception(e.getClass().getName())
                .build();
        log.warn("URL: " + request.getRequestURI() + ", 业务异常: " + error);
        return error;
    }


    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResult handleValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }

        ErrorResult error = ErrorResult.builder()
                .status(ResCode.BAD_REQUEST.getCode())
                .message(message)
                .exception(e.getClass().getName())
                .build();

        log.warn("URL: " + request.getRequestURI() + ", 参数校验失败: " + error);
        return error;
//        return ResResult.validateFailed(message);
    }

//    @ExceptionHandler(value = BindException.class)
//    public ResResult handleValidException(BindException e) {
//        BindingResult bindingResult = e.getBindingResult();
//        String message = null;
//        if (bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if (fieldError != null) {
//                message = fieldError.getField() + fieldError.getDefaultMessage();
//            }
//        }
//        return ResResult.validateFailed(message);
//    }


}
