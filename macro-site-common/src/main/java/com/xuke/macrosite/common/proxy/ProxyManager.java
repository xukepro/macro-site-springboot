package com.xuke.macrosite.common.proxy;

import com.alibaba.fastjson.JSONObject;
import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.exception.BusinessException;
import com.xuke.macrosite.common.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xuke on 2020/10/16
 */
@Slf4j
public final class ProxyManager<T> {

    private Class<T> clazz;

    private String url;

    private OkHttpClient okHttpClient;

    public ProxyManager(Class<T> clazz, String url, OkHttpClient okHttpClient) {
        this.clazz = clazz;
        this.url = url;
        this.okHttpClient = okHttpClient;
    }

    public T getInstance() {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz}, new ProxyInvocation());
    }

    private class ProxyInvocation implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            JSONObject jsonObject = new JSONObject();
            String serverURL = url + "/" + method.getName();

            if (args != null && args.length > 1) {
                throw new BusinessException(ResCode.BAD_REQUEST);
            }

            if (method.getParameterTypes().length > 0) {
                Object arg = args[0];
                Class<?> parameterType = method.getParameterTypes()[0];
                for (Field field : parameterType.getDeclaredFields()) {
                    field.setAccessible(true);
                    jsonObject.put(field.getName(), field.get(arg));
                }
            }
            return HttpClient.call(okHttpClient, jsonObject.toJSONString(), serverURL);
        }
    }

}
