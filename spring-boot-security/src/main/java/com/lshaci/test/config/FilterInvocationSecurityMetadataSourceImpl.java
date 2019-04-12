package com.lshaci.test.config;

import com.lshaci.test.model.dto.RoleResourceDto;
import com.lshaci.test.service.RoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Component
//接收用户请求的地址，返回访问该地址需要的所有权限
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private RoleResourceService roleResourceService;

    // 资源权限集合
    private Map<String, Collection<ConfigAttribute>> resourceRoleMap = new TreeMap<>();

    @PostConstruct
    public void loadResourceDefine(){
        //取得资源与角色列表
        List<RoleResourceDto> roleResources = roleResourceService.getAll();
        roleResources.stream()
                .collect(Collectors.groupingBy(RoleResourceDto::getResourceUrl))
                .forEach((k, v) -> {
                    Collection<ConfigAttribute> attributes = v.stream()
                            .map(RoleResourceDto::getRoleName)
                            .map(SecurityConfig::new)
                            .collect(Collectors.toList());
                    resourceRoleMap.put(k, attributes);
                });
    }

    //接收用户请求的地址，返回访问该地址需要的所有权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //得到用户的请求地址,控制台输出一下
        FilterInvocation filterInvocation = (FilterInvocation) o;
        String requestUrl = filterInvocation.getRequestUrl();
        System.out.println("用户请求的地址是：" + requestUrl);

        //如果登录页面就不需要权限
        if ("/login.html".equals(requestUrl)) {
            return null;
        }

        for (String resourceUrl: resourceRoleMap.keySet()) {
            if (antPathMatcher.match(resourceUrl, requestUrl)) {
                return resourceRoleMap.get(resourceUrl);
            }
        }

        //如果没有匹配的url则说明大家都可以访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}