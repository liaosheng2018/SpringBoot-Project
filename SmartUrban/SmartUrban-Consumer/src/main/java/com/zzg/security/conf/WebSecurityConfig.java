package com.zzg.security.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.zzg.security.UserAuthenticationProvider;
import com.zzg.security.filter.JwtAuthenticationTokenFilter;
import com.zzg.security.handler.UserAccessDeniedHandler;
import com.zzg.security.handler.UserLoginFailureHandler;
import com.zzg.security.handler.UserLoginSuccessHandler;
import com.zzg.security.handler.UserLogoutSuccessHandler;
import com.zzg.security.handler.UserNotLoginHandler;

/**
 * 系统安全核心配置
 *
 * @author zzg
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    /**
     * 无权限处理类
     */
    @Autowired
    private UserAccessDeniedHandler userAccessDeniedHandler;

    /**
     * 用户未登录处理类
     */
    @Autowired
    private UserNotLoginHandler userNotLoginHandler;

    /**
     * 用户登录成功处理类
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

    /**
     * 用户登录失败处理类
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

    /**
     * 用户登出成功处理类
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

    /**
     * 用户登录验证
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * jwt 拦截器
     */
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 用户登录验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    /**
     * 安全权限配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 去掉 CSRF
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭token
                .and()
                .httpBasic().authenticationEntryPoint(userNotLoginHandler)
                .and()
                .authorizeRequests()

//                .anyRequest()
//                .access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证

                .and()
                .formLogin()  //开启登录
                .successHandler(userLoginSuccessHandler) // 登录成功
                .failureHandler(userLoginFailureHandler) // 登录失败
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .permitAll();



        http.exceptionHandling().accessDeniedHandler(userAccessDeniedHandler); // 无权访问 JSON 格式的数据
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter
    }
}

