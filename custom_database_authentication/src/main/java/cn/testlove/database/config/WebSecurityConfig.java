package cn.testlove.database.config;

import cn.testlove.database.security.authentication.TokenAuthenticationProvider;
import cn.testlove.database.security.TokenFilter;
import cn.testlove.database.service.impl.LogInServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author admin
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(getUserDetailsService()).passwordEncoder(passwordEncoder());
auth.authenticationProvider(new TokenAuthenticationProvider());

    }
    @Bean
    UserDetailsService getUserDetailsService() {
        return new LogInServiceImpl();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                // OPTIONS请求全部放行
//                .antMatchers(HttpMethod.POST, "/**").permitAll()
//                //登录和注册的接口放行，其他接口全部接受验证
//                .antMatchers(HttpMethod.POST).authenticated()
//                .antMatchers(HttpMethod.PUT).authenticated()
//                .antMatchers(HttpMethod.DELETE).authenticated()
//                .antMatchers(HttpMethod.GET).authenticated();
http.authorizeRequests().anyRequest().permitAll();

//        http.formLogin().authenticationDetailsSource(new TokenWebAuthenticationDetailsSource()).loginProcessingUrl("/guest/login");
        http.addFilterBefore(tokenFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/guest/login");
    }
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
