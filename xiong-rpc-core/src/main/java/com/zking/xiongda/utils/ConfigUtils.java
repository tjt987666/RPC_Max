package com.zking.xiongda.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 */
public class ConfigUtils {

    /**
     * 加载配置对象。
     * 该方法会根据指定的类和前缀从默认的配置文件中加载配置信息，并将其转换为指定类型的对象。
     * 默认的配置文件名为 `application.properties`。
     *
     * @param tClass 配置对象的类类型
     * @param prefix 配置项的前缀，用于从配置文件中读取特定的配置项
     * @param <T>    配置对象的泛型类型
     * @return 转换后的配置对象
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象，支持区分环境。
     * 该方法会根据指定的类、前缀和环境从相应的配置文件中加载配置信息，并将其转换为指定类型的对象。
     * 配置文件的名称根据环境动态生成，格式为 `application-{environment}.properties`。
     *
     * @param tClass      配置对象的类类型
     * @param prefix      配置项的前缀，用于从配置文件中读取特定的配置项
     * @param environment 环境标识，用于区分不同环境的配置文件
     * @param <T>         配置对象的泛型类型
     * @return 转换后的配置对象
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        // 构建配置文件的名称
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");

        // 读取配置文件
        Props props = new Props(configFileBuilder.toString());

        // 将配置文件中的属性转换为指定类型的对象
        return props.toBean(tClass, prefix);
    }
}
