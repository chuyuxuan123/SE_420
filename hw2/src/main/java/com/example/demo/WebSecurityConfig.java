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
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("Alice").password("123456").roles("USER").build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("USER")

                .and().formLogin()
                .and().httpBasic();

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .formLogin();
//    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .ldapAuthentication()
//                .userDnPatterns("uid={0},ou=people")
//                .groupSearchBase("ou=groups")
//                .contextSource()
//                    .url("ldap://localhost:8389/dc=springframework,dc=org")
//                    .and()
//                .passwordCompare()
//                    .passwordEncoder(new LdapShaPasswordEncoder())
//                    .passwordAttribute("userPassword");
//    }
}
