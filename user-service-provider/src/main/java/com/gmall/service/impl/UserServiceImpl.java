package com.gmall.service.impl;

import com.gmall.bean.UserAddress;
import com.gmall.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author bo bo
 * @Package com.gmall.service.impl
 * @date 2020/6/18 13:39
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress address1=
                new UserAddress(1,
                        "上海市闵行区",
                        "1",
                        "于先生",
                        "17621397415",
                        "1");
        UserAddress address2=
                new UserAddress(2,
                        "上海市闵行区",
                        "1",
                        "单女士",
                        "17621397666",
                        "2");

        return Arrays.asList(address1,address2);
    }
}
