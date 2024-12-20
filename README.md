# RPC进阶版



#### 基础版：
- 基本的RPC调用功能

#### 进阶版：
- 全局配置加载（properties）
- 接口Mock
- 序列化器和SPI机制
- 注册中心
- 自定义协议
- 负载均衡
- 重试机制
- 容错机制
- 启动机制
- 主键驱动

### 进阶阶段一（全局配置加载）
1. **创建RPC框架的配置类**
   - 定义配置属性
   - 提供getter和setter方法

2. **创建工具类读取配置工具**
   - 使用YAML解析库（如SnakeYAML）读取配置文件
   - 将配置文件中的内容映射到配置类

3. **创建RPC相关的常量**
   - 定义常用的常量，如默认端口号、超时时间等

4. **维持全局配置（双检锁单例模式）**
   - 确保全局配置类是单例的
   - 使用双重检查锁定（double-checked locking）确保线程安全

### 进阶阶段二（接口Mock）
1. **添加新的Mock字段**
   - 在配置类中添加Mock相关的字段
   - 例如：是否启用Mock、Mock数据源等

2. **Mock服务代理（JDK动态代理）**
   - 使用JDK动态代理创建Mock服务代理
   - 实现InvocationHandler接口，重写invoke方法

3. **服务代理工厂（用来创建代理对象）**
   - 创建一个工厂类，负责创建不同类型的代理对象
   - 根据配置决定是否创建Mock代理

### 进阶阶段三（序列化器和SPI机制）
1. **自定义序列化器（JSON、Kryo、Hessian）**
   - 实现不同的序列化器接口
   - 例如：JSONSerializer、KryoSerializer、HessianSerializer

2. **定义序列化工厂、工厂模式+单例模式（用于获取序列化器对象）**
   - 创建一个序列化工厂类
   - 使用工厂模式和单例模式确保工厂类是单例的

3. **动态获取序列化器**
   - 根据配置动态选择合适的序列化器
   - 使用SPI机制加载序列化器

4. **SpiLoader加载器**
   - 实现一个SPI加载器，用于加载SPI扩展点
   - 使用Java的ServiceLoader机制

5. **SPI测试**
   - 编写测试用例验证SPI加载器的功能
   - 确保不同的序列化器能够正确加载和使用
