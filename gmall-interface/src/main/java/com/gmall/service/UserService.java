package com.gmall.service;

import com.gmall.bean.UserAddress;

import java.util.List;

/**
 * @author bo bo
 * @Package com.gmall.service
 * @date 2020/6/18 13:38
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @Description:
 */
public interface UserService {
    public List<UserAddress> getUserAddressList(String userId);
}
