package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();//spring security 5.2必须要求passwordencoder
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());//这里添加了一个用户，用户名为Alice，密码123456
        manager.createUser(User.withUsername("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()//没登录的用户可以看到首页提示
                .antMatchers("/hello").permitAll()//供测试
                .antMatchers("/main").hasRole("USER")//只有登录后的用户才能进入wordladder页面
//                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/main", true)//登录成功跳转到/main页面
                .and().httpBasic();

//        //仅测试时使用，访问所有(包括用户定义的api)endpoint不需要权限
//        http.requestMatcher(EndpointRequest.to("/actuator")).authorizeRequests()
//                .anyRequest().permitAll();

    }


}
