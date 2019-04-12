package com.lshaci.test.service;

import com.lshaci.test.mapper.RoleResourceMapper;
import com.lshaci.test.model.domain.RoleResource;
import com.lshaci.test.model.dto.RoleResourceDto;
import top.lshaci.framework.service.BaseService;

import java.util.List;

/**
 * RoleResource service interface
 *
 * @author lshaci
 */
public interface RoleResourceService extends BaseService<RoleResource, RoleResourceMapper> {

    List<RoleResourceDto> getAll();
}