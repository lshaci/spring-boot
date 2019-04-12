package com.lshaci.test.service;

import com.lshaci.test.mapper.RoleMapper;
import com.lshaci.test.model.domain.Role;

import top.lshaci.framework.service.BaseService;

import java.util.List;

/**
 * Role service interface
 *
 * @author lshaci
 */
public interface RoleService extends BaseService<Role, RoleMapper> {

    /**
     * 根据用户id查询角色信息
     *
     * @param userId 用户id
     * @return 角色集合
     */
    List<Role> getByUserId(Integer userId);
}