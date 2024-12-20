package com.zking.xiongda;

import com.zking.xiongda.model.User;
import com.zking.xiongda.proxy.ServiceProxyFactory;
import com.zking.xiongda.serializer.UserService;


/**
 * 服务消费者示例
 * 该类展示了如何加载和使用 RPC 配置
 */
public class ConsumerExample {
    public static void main(String[] args) {
//        // 从配置文件中加载 RpcConfig 对象，配置前缀为 "rpc"
//        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
//
//        // 打印加载的配置信息
//        System.out.println(rpc);
//        获取代理
        UserService userService = ServiceProxyFactory.getMockProxy(UserService.class);
        User user = new User();
        user.setName("yupi");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        long number = userService.getNumber();
        System.out.println(number);

    }
}

