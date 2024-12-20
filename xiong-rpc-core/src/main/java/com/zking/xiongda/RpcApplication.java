package com.zking.xiongda;

import com.zking.xiongda.config.RpcConfig;
import com.zking.xiongda.constant.RpcConstant;
import com.zking.xiongda.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;
/**
 * RPC框架应用
 * 相当于holder，存放了项目全局用到的玻璃，双检锁单例模式实现
 */
@Slf4j
public class RpcApplication {

    // 私有的变量，用于存储 RPC 配置对象
    // 使用 volatile 关键字确保多线程环境下的可见性和有序性
    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化方法，支持传入自定义配置
     *
     * @param newRpcConfig 自定义的 RPC 配置对象
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig; // 将传入的配置对象赋值给静态变量
        log.info("rpc init config: {}", newRpcConfig.toString()); // 记录初始化的配置信息
    }

    /**
     * 默认初始化方法，从配置文件中加载配置
     * 如果加载失败，则使用默认配置
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            // 从配置文件中加载 RpcConfig 对象
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 如果加载配置失败，使用默认配置
            newRpcConfig = new RpcConfig();
            log.warn("Failed to load configuration, using default config: {}", e.getMessage());
        }
        // 调用带参数的 init 方法进行初始化
        init(newRpcConfig);
    }

    /**
     * 获取当前的 RPC 配置对象
     * 如果配置对象为空，则进行初始化
     *
     * @return 当前的 RPC 配置对象
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            // 双重检查锁定，确保线程安全
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    // 如果配置对象为空，则调用无参数的 init 方法进行初始化
                    init();
                }
            }
        }
        return rpcConfig; // 返回当前的 RPC 配置对象
    }
}
