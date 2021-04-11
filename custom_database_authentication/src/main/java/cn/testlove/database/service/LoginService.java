package cn.testlove.database.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author admin
 */
public interface LoginService extends UserDetailsService {
    Boolean login(String username, String password);
}
