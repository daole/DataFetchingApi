package com.dreamdigitizers.datafetchingapis.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {
    private static final String API_USER_NAME = "androiddatafetchingapisclient";
    private static final String API_USER_PASSWORD = "androiddatafetchingapisclient";
    private static final String API_USER_ROLE = "USER";

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(ConfigurationSecurity.API_USER_NAME)
                .password(ConfigurationSecurity.API_USER_PASSWORD)
                .roles(ConfigurationSecurity.API_USER_ROLE);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().anyRequest().fullyAuthenticated();
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
    }
}
