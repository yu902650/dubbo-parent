package com.gmall.service.controller;

import com.gmall.bean.UserAddress;
import com.gmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author bo bo
 * @Package com.gmall.service.controller
 * @date 2020/6/18 22:15
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping("initOrder")
    public List initOrder(@RequestParam("uid") String userId){
        List<UserAddress> userAddresses = orderService.initOrder(userId);
        return userAddresses;
    }


}
