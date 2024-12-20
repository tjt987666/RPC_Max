package com.zking.xiongda.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zking.xiongda.RpcApplication;
import com.zking.xiongda.model.RpcRequest;
import com.zking.xiongda.model.RpcResponse;
import com.zking.xiongda.serializer.Serializer;
import com.zking.xiongda.serializer.SerializerFactory;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务代理（JDK 动态代理）
 */
@Slf4j
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
//        Serializer serializer = new JdkSerializer();
       final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        System.out.println(rpcRequest);

        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送请求

            // todo 注意，这里地址被硬编码了（需要使用注册中心和服务发现机制解决）
            try (
                    HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .header("Content-Type", "application/json; charset=UTF-8") // 设置请求头
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}