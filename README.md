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


## 今天周五，没有约面试，好好看下dubbo的配置和其他知识
1. dubbo是一个RPC框架（远程调用的），当然你http直接调用接口也能实现，但是http对接口调用和服务提供方进行统一的状态监控，统计使用次数以及关系管理等一系列让用户能简单易用可维护的优点
2. 原来在垃圾箱的项目就是简单的用了一下，zookepper + dubbo配合使用的简单，结果咧，面试的时候也没准备好，直接被面试官干懵比了。
虚心，完整的学习一次，好好准备，咱们下周再战吧。
3. 现在公司做分布式的，如果你没有用过springCloud,那么你就要会使用Dubbo,不然10家有9家是进不去的

## dubbo出现的背景 (官方文档)
>http://dubbo.apache.org/zh-cn/docs/user/preface/background.html

>分布式服务架构

>当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。
>此时，用于提高业务复用及整合的分布式服务框架(RPC)是关键。

### <font color="red">项目中你是如果使用dubbo的</font>
项目中我们使用dubbo是解决远程服务调用的问题，是配合zookeeper作为注册中心来使用的。

### <font color="red">那你能说具体点吗？</font>
我们项目使用的是xml形式对dubbo里的参数进行配置
首先我们看下这个例子; 
**服务提供者xml**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
       http://dubbo.apache.org/schema/dubbo
       http://dubbo.apache.org/schema/dubbo/dubbo.xsd">

    <!--  1.指定当前应用的名字（同样的服务名字相同，不要和别的服务同名）  -->
    <dubbo:application name="user-service-provider"></dubbo:application>

    <!--  2.指定注册中心名称  -->
  </dubbo:registry>-->
    <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"></dubbo:registry>

    <!--  3.指定通讯规则  -->
    <dubbo:protocol name="dubbo" port="20880"></dubbo:protocol>

    <!--    4.暴露服务 ref 指向服务的实现对象-->
    <dubbo:service interface="com.gmall.service.UserService" ref="userServiceImpl"></dubbo:service>
    <!--    5.服务实现-->
    <bean id="userServiceImpl" class="com.gmall.service.impl.UserServiceImpl"></bean>

    <!--   提供者连接注册中心 -->
    <dubbo:monitor protocol="registry"></dubbo:monitor>
</beans>
```
作为服务提供者，xml中最少要配置这些参数,当然还有很多不一一赘述
- dubbo:application 服务名 《服务名称不允许重复的》
- dubbo:registry 指定注册中心地址
- dubbo:protocol 指定通讯协议 默认dubbo <font color="red">后面面试官问了我除了dubbo 还有哪些你知道吗</font>
- dubbo:service  dubbo 暴露的服务
- dubbo:monitor  服务提供者连接注册中心（可以是自动发现注册registry）


**服务消费者xml**
```xml
    <!--  包扫描  -->
    <context:component-scan base-package="com.gmall.service.impl"/>

    <!-- 消费方应用名，用于计算依赖关系，不是匹配条件，不要与提供方一样 -->
    <dubbo:application name="order-service-consumer"/>

    <!-- 注册中心暴露发现服务地址 -->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>

    <!-- 生成远程服务代理，可以和本地bean一样使用service  check启动自检-->
    <dubbo:reference id="userService" interface="com.gmall.service.UserService"
    check="false"  />

    <!--    配置消费者的统一规则：-->
    <dubbo:consumer check="false"></dubbo:consumer>

    <!--  消费者连接监控中心  -->
    <dubbo:monitor protocol="registry"></dubbo:monitor>
    <!--        <dubbo:monitor address="127.0.0.1:7070"></dubbo:monitor>-->
```
作为服务消费者，我们要配置一下参数
- dubbo:application  服务消费方的名称，用于统计调用次数啥的，名字不能服务提供方一样
- dubbo:registry     注册中心暴露服务提供的地址。提供者把服务注册到注册中心，注册中心把地址提供给消费者
- dubbo:reference    注册中心提供的远程服务代理，让消费者可以像本地调用一样使用service
- dubbo:consumer     配置消费者的统一规则
>像配置启动检查
 1. 默认可以不配置，但是如果启动时没有发现可以用的服务提供者就会报错，程序终止
 2. 可以单独配置，也可以用dubbo:consumer配置全局的
 3. 可以直接关闭注册中心启动检查功能
- dubbo:monitor      消费者连接监控中心

#### 上面说的注册中心是zk,监控中心是dubbo提供的一个管理后台

### <font color="red"> 回到之前说的 面试官问了我除了dubbo支持的协议除了dubbo 还有哪些你知道吗？</font>
>http://dubbo.apache.org/zh-cn/docs/user/references/protocol/introduction.html

官方文档有的，但是要仔细看下，我想起来面试官问了个rest你知道吗？？我说我只知道restful... 
现在想想自己还挺呆的。🤓
dubbo一共支持10种协议，中间的不同直接文档看，应该比我说的清楚呢，哈哈
 1. [dubbo:// ](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/dubbo.html)  

 2. [rmi://](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/rmi.html)
 3. [hessian://](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/hessian.html)
 4. [http:// ](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/http.html)
 5. [webservice://](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/webservice.html)
 6. [thrift://](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/thrift.html)
 7. [memcached://]()
 8. [redis://](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/redis.html)
 9. [rest://](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/rest.html)
 10. [grpc://](http://dubbo.apache.org/zh-cn/docs/user/references/protocol/gRPC.html)

### <font color="red">之前面试有被问到，注册中心宕机，客户端还能继续使用吗？</font>
dubbo是服务提供者，zookeeper是一个注册中心。真正的客户端去调用的时候，会去zookeeper注册中心拿服务。
并且客户端第一次拿到zookeeper中的service服务地址后，会把真正的服务提供地址缓存在本地，就算zk注册中心全部宕机了，也可以继续使用。
但是dubbo提供方宕机了，那么就真不能使用了（真的完蛋了）,我当时回答的有问题 😄

后续看完再记录


