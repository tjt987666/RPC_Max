package com.zking.xiongda;


import com.zking.xiongda.config.RpcConfig;
import com.zking.xiongda.regisrty.LocalRegistry;
import com.zking.xiongda.serializer.UserService;
import com.zking.xiongda.serializer.UserServiceImpl;
import com.zking.xiongda.service.HttpServer;
import com.zking.xiongda.service.VertxHttpServer;
import com.zking.xiongda.utils.ConfigUtils;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        // rpc初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);


        // 启动服务，从配置文件中获取到端口
        RpcConfig rpc  = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpc);


        HttpServer server = new VertxHttpServer();
        server.doStart(RpcApplication.getRpcConfig().getServerPort());



    }
}
