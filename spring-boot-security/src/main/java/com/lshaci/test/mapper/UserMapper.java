package com.lshaci.test.mapper;

import com.lshaci.test.model.domain.User;
import top.lshaci.framework.mybatis.mapper.TKMapper;

public interface UserMapper extends TKMapper<User> {

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User selectByUsername(String username);
}