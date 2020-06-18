package com.gmall.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author bo bo
 * @Package org.gmall.service
 * @date 2020/6/18 14:57
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:
 */
public class MainConsumerApplication {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
//        OrderService orderService = context.getBean(OrderService.class);
//        orderService.initOrder("1");
        System.err.println("调用完成...");
        System.in.read();
    }

}
