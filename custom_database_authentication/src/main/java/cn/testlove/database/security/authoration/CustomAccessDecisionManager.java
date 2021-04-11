package cn.testlove.database.security.authoration;

import cn.testlove.database.security.authentication.TokenAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author admin
 *  决定当前请求有没有权限被访问
 */
@Component
@Slf4j
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     *
     * @param authentication 当前登录用户的信息
     * @param object 是FilterInvocation对象,可以获取当前请求
     * @param configAttributes 访问当前请求所需要的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        Collection<? extends GrantedAuthority> authorities = tokenAuthentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority());
            for (ConfigAttribute configAttribute :
                    configAttributes) {
                System.out.println(configAttribute.getAttribute());
                if (configAttribute.getAttribute().equals(authority.getAuthority())) {
                    log.info("通过权限检验");
                    return;
                }
            }
        }
        log.info("没有通过权限检验");
        throw new AccessDeniedException("没有权限");


    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
