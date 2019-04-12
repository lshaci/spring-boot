package com.lshaci.test.config;

import com.lshaci.test.model.domain.Role;
import com.lshaci.test.model.domain.User;
import com.lshaci.test.model.dto.LoginUser;
import com.lshaci.test.service.RoleService;
import com.lshaci.test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户的用户名: {}", username);
        User user = userService.getByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> roles = roleService.getByUserId(user.getId());

        LoginUser loginUser = new LoginUser(user, roles);
        return loginUser;
    }


}
