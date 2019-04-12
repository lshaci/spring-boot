package com.lshaci.test.service;

import com.lshaci.test.mapper.ResourceMapper;
import com.lshaci.test.model.domain.Resource;

import top.lshaci.framework.service.BaseService;

/**
 * Resource service interface
 *
 * @author lshaci
 */
public interface ResourceService extends BaseService<Resource, ResourceMapper> {

    /**
     * 根据请求地址获取资源
     *
     * @param requestUrl 请求地址
     * @return 资源信息
     */
    Resource getByUrl(String requestUrl);
}