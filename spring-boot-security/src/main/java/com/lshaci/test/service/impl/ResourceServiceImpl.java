package  com.lshaci.test.service.impl;

import org.springframework.stereotype.Service;

import com.lshaci.test.mapper.ResourceMapper;
import com.lshaci.test.model.domain.Resource;
import com.lshaci.test.service.ResourceService;

import lombok.extern.slf4j.Slf4j;
import top.lshaci.framework.service.BaseServiceImpl;

/**
 * Resource service implement
 * 
 * @author lshaci
 */
@Service
@Slf4j
public class ResourceServiceImpl extends BaseServiceImpl<Resource, ResourceMapper> implements ResourceService {

    @Override
    public Resource getByUrl(String requestUrl) {
        return null;
    }
}
