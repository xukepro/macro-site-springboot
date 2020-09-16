package com.xuke.macrosite.common.exception;

import com.xuke.macrosite.common.api.ResResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuke on 2020/9/16
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return ResResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResResult handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return ResResult.validateFailed(message);
    }
}
