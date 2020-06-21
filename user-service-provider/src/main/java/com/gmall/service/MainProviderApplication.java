package com.gmall.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author bo bo
 * @Package com.gmall.service
 * @date 2020/6/18 14:16
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:
 */
public class MainProviderApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
        context.start();
        //阻塞
        System.in.read();
    }

}
