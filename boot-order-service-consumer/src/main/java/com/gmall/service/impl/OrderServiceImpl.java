package com.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gmall.bean.UserAddress;
import com.gmall.service.OrderService;
import com.gmall.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bo bo
 * @Package org.gmall.service.impl
 * @date 2020/6/18 13:49
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description: 1. 将服务提供者注册到注册中心（暴露服务）
 * a.导入dubbo依赖（2.6.2）
 * b.引入zk客户端
 * <p>
 * 2. 让服务消费者去注册中心订阅服务提供者的服务地址
 */
@Service
public class OrderServiceImpl implements OrderService {

//    @Autowired
    @Reference
    UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("===>>>>>>>>   " + userId);
        //查询用户收货地址
        List<UserAddress> userAddressList = userService.getUserAddressList(userId);
        return userAddressList;
    }
}
