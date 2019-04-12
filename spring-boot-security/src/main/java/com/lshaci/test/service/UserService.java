package com.lshaci.test.service;

import com.lshaci.test.mapper.UserMapper;
import com.lshaci.test.model.domain.User;

import top.lshaci.framework.service.BaseService;

/**
 * User service interface
 *
 * @author lshaci
 */
public interface UserService extends BaseService<User, UserMapper> {

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User getByUsername(String username);
}