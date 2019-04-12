package  com.lshaci.test.service.impl;

import org.springframework.stereotype.Service;

import com.lshaci.test.mapper.RoleMapper;
import com.lshaci.test.model.domain.Role;
import com.lshaci.test.service.RoleService;

import lombok.extern.slf4j.Slf4j;
import top.lshaci.framework.service.BaseServiceImpl;

import java.util.List;

/**
 * Role service implement
 *
 * @author lshaci
 */
@Service
@Slf4j
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleMapper> implements RoleService {


    @Override
    public List<Role> getByUserId(Integer userId) {
        return mapper.selectByUserId(userId);
    }
}
