package  com.lshaci.test.service.impl;

import org.springframework.stereotype.Service;

import com.lshaci.test.mapper.UserMapper;
import com.lshaci.test.model.domain.User;
import com.lshaci.test.service.UserService;

import lombok.extern.slf4j.Slf4j;
import top.lshaci.framework.service.BaseServiceImpl;

/**
 * User service implement
 * 
 * @author lshaci
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {


    @Override
    public User getByUsername(String username) {
        return mapper.selectByUsername(username);
    }
}
