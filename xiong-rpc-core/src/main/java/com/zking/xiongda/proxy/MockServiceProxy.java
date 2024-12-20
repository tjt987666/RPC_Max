package com.zking.xiongda.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MOCK服务代理类（JDK动态代理）
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //根据方法的返回值，生成特定的默认值对象
        Class<?> returnType = method.getReturnType();
        log.info("log invoke method:{}", method.getName());
        return getDefaultObject(returnType);
    }


    /**
     * 生成指定类型的默认对象
     *
     */
    private Object getDefaultObject(Class<?> type) {
        if(type.isPrimitive()){
            if (type == boolean.class){
                return false;
            }else if (type == short.class){
                return (short)0;
            } else if (type == int.class) {
                return 0;
            }else if(type == long.class){
                return 0L;
            }
        }
        return null;
    }

}
