package  com.lshaci.test.service.impl;

import com.lshaci.test.model.dto.RoleResourceDto;
import org.springframework.stereotype.Service;

import com.lshaci.test.mapper.RoleResourceMapper;
import com.lshaci.test.model.domain.RoleResource;
import com.lshaci.test.service.RoleResourceService;

import lombok.extern.slf4j.Slf4j;
import top.lshaci.framework.service.BaseServiceImpl;

import java.util.List;

/**
 * RoleResource service implement
 * 
 * @author lshaci
 */
@Service
@Slf4j
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource, RoleResourceMapper> implements RoleResourceService {

    @Override
    public List<RoleResourceDto> getAll() {
        return mapper.selectAllName();
    }
}
