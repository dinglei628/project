package com.zb.server.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean
    @Primary
    @ConfigurationProperties(value = "spring.datasource")
    public DataSource createDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(createDataSource());
    }
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(createDataSource());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
        //开启授权码模式配置
/*        clients.inMemory().withClient("client")
                .secret("secret")
                .authorizedGrantTypes("authorization_code")
                .scopes("app")
                .redirectUris("http://www.baidu.com/");*/
    }
}
