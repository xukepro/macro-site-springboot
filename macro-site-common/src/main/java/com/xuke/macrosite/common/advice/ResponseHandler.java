package com.xuke.macrosite.common.advice;

import com.xuke.macrosite.common.res.ErrorResult;
import com.xuke.macrosite.common.res.ResPage;
import com.xuke.macrosite.common.res.ResResult;
import io.swagger.annotations.ResponseHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * Created by xuke on 2020/10/14
 */
@ControllerAdvice(basePackages = {"com.xuke.macrosite"}) //必须限定范围，否则swagger会出问题
@Slf4j
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("响应body: " + body);
        if (body instanceof ErrorResult) {
            ErrorResult errorResult = (ErrorResult) body;
            return ResResult.failed(errorResult.getStatus(), errorResult.getMessage());
        }
        if (body instanceof List) {
            List list = (List) body;
            return ResResult.success(ResPage.restPage(list));
        }
        return ResResult.success(body);
    }
}
