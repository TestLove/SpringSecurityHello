package cn.testlove.database.security.authoration;

import cn.testlove.database.mapper.RoleMapper;
import cn.testlove.database.util.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author admin
 * 自定义此方法以确定一个请求需要哪些角色
 */
@Component
@Slf4j
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    private WebApplicationContext wac;

    RoleMapper roleMapper;

    /**
     * Accesses the {@code ConfigAttribute}s that apply to a given secure object.
     *
     * @param object the object being secured
     * @return the attributes that apply to the passed in secured object. Should return an
     * empty collection if there are no applicable attributes.
     * @throws IllegalArgumentException if the passed object is not of a type supported by
     *                                  the <code>SecurityMetadataSource</code> implementation
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        roleMapper= (RoleMapper) SpringBeanUtils.getBean("roleMapper");
        System.out.println("roleMapper"+roleMapper);
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        String[] data = new String[0];
        List<String> allAuthorities = roleMapper.selectAllAuthorities();
        List<String> strings = new ArrayList<>();
        for (String authority : allAuthorities) {
            if(antPathMatcher.match(authority,requestUrl)){
             strings = roleMapper.selectRoleNameByAccess(authority);
             data = new String[strings.size()];
             strings.toArray(data);
            }
        }

        if(data.length == 0){
            log.info("未在数据库中查到访问相应url所对应的角色");
            return SecurityConfig.createList("No Auth");
        }
        log.info("在数据库中查到访问相应url所对应的角色: "+ Arrays.toString(data));
        return SecurityConfig.createList(data);
    }

    /**
     * If available, returns all of the {@code ConfigAttribute}s defined by the
     * implementing class.
     * <p>
     * This is used by the {@link AbstractSecurityInterceptor} to perform startup time
     * validation of each {@code ConfigAttribute} configured against it.
     *
     * @return the {@code ConfigAttribute}s or {@code null} if unsupported
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * Indicates whether the {@code SecurityMetadataSource} implementation is able to
     * provide {@code ConfigAttribute}s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
