### dubbo 配置的覆盖策略

虚拟机启动的配置参数 > dubbo.xml  > dubbo.properties

### 配置启动检查
1. 默认可以不配置，但是如果启动时没有发现可以用的服务提供者就会报错，程序终止
2. 可以单独配置，也可以用dubbo:consumer配置全局的
3. 可以直接关闭注册中心启动检查功能
```xml
<!-- 生成远程服务代理，可以和本地bean一样使用service  check启动自检-->
    <dubbo:reference id="userService" interface="com.gmall.service.UserService"
    check="false"  />
  
<!--    配置消费者的统一规则：-->
<dubbo:consumer check="false"></dubbo:consumer>

<!-- 关闭注册中心启动时检查 -->
<dubbo:registry check="flase"></dubbo:registry>

```

