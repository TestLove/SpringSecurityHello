package cn.testlove.database.config;

import cn.testlove.database.security.authentication.TokenAuthenticationProvider;
import cn.testlove.database.security.TokenFilter;
import cn.testlove.database.security.authoration.CustomAccessDecisionManager;
import cn.testlove.database.security.authoration.CustomFilterInvocationSecurityMetadataSource;
import cn.testlove.database.service.impl.LogInServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
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

        ApplicationContext sharedObject = http.getSharedObject(ApplicationContext.class);
        http.apply(new UrlAuthorizationConfigurer<>(sharedObject))
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(customAccessSecurityMetadataSource());
                        object.setAccessDecisionManager(customAccessDecisionManager());
                        return object;
                    }
                })
                .and()
                .csrf().disable();

        http.addFilterBefore(tokenFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/guest/login");
    }

    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

//    @Bean
    public CustomAccessDecisionManager customAccessDecisionManager() {
        return new CustomAccessDecisionManager();
    }
//    @Bean
    public CustomFilterInvocationSecurityMetadataSource customAccessSecurityMetadataSource(){
        return new CustomFilterInvocationSecurityMetadataSource();
    }
}
