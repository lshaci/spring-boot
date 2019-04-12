package com.lshaci.test.mapper;

import com.lshaci.test.model.domain.RoleResource;
import com.lshaci.test.model.dto.RoleResourceDto;
import top.lshaci.framework.mybatis.mapper.TKMapper;

import java.util.List;

public interface RoleResourceMapper extends TKMapper<RoleResource> {


    List<RoleResourceDto> selectAllName();
}