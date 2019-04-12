package  com.lshaci.test.service.impl;

import org.springframework.stereotype.Service;

import com.lshaci.test.mapper.UserRoleMapper;
import com.lshaci.test.model.domain.UserRole;
import com.lshaci.test.service.UserRoleService;

import lombok.extern.slf4j.Slf4j;
import top.lshaci.framework.service.BaseServiceImpl;

/**
 * UserRole service implement
 * 
 * @author lshaci
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, UserRoleMapper> implements UserRoleService {


}
