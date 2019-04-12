package com.lshaci.test.mapper;

import com.lshaci.test.model.domain.Role;
import top.lshaci.framework.mybatis.mapper.TKMapper;

import java.util.List;

public interface RoleMapper extends TKMapper<Role> {

    /**
     * 根据用户id查询角色信息
     *
     * @param userId 用户id
     * @return 角色集合
     */
    List<Role> selectByUserId(Integer userId);
}