package com.example.securityservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) throws Exception {
        endpointsConfigurer.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
//        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        String finalSecret = new BCryptPasswordEncoder().encode("123456");

        clientDetailsServiceConfigurer.inMemory()
                .withClient("client_1")
                .secret(finalSecret)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("/server")
                .authorities("oauth2")
        .and().withClient("client_2")
        .authorizedGrantTypes("authorization_code", "refresh_token")
//        .authorities("oauth2")
                .scopes("app")
        .redirectUris("www.baidu.com")

        ;
//        "authorization_code", "refresh_token", "implicit", "password", "client_credentials"
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients();
    }
}
