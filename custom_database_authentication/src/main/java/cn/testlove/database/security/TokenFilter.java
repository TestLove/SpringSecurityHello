package cn.testlove.database.security;

import cn.testlove.database.entity.TokenModel;
import cn.testlove.database.security.authentication.TokenAuthentication;
import cn.testlove.database.util.JwtUtils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author admin
 */
public class TokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenOptional = Optional.ofNullable(request.getHeader("token")) ;
        SecurityContext context = SecurityContextHolder.getContext();
        TokenModel tokenModel = new TokenModel(tokenOptional.get());
        JwtUtils.resolveToken(tokenModel);
        if (tokenModel.isTokenExpired()) {
            System.out.println("token 已经过期或者无效");
            return;
        }
        TokenAuthentication auth = new TokenAuthentication(null);
        auth.setTokenModel(tokenModel);
        context.setAuthentication(auth);
        if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
            // do nothing
        }

        filterChain.doFilter(request, response);

    }
}
