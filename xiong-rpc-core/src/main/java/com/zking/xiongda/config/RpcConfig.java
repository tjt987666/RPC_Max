package com.zking.xiongda.config;

import com.zking.xiongda.serializer.SerializerKeys;
import lombok.Data;

@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "xiongda-rpc";
    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "127.0.0.1";

    /**
     * 服务器端口
     */
    private Integer serverPort = 8080;

    /**
     * mock模拟调用
     */
    private boolean mock = false;

    /**
     * 序列号器
     */
    private String serializer  = SerializerKeys.JDK;



}
