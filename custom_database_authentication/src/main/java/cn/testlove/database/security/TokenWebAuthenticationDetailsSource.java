package cn.testlove.database.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * 通过这个类,返回自定义实现的认证信息类
 */
//@Component
public class TokenWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {


    /**
     * Called by a class when it wishes a new authentication details instance to be
     * created.
     *
     * @param context the request object, which may be used by the authentication details
     *                object
     * @return a fully-configured authentication details instance
     */
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new TokenWebAuthenticationDetails(context);
    }
}
