package com.dreamdigitizers.datafetchingapis.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {
    @Value("${basicAuthenticationUsername}")
    private String basicAuthenticationUsername;

    @Value("${basicAuthenticationPassword}")
    private String basicAuthenticationPassword;

    @Value("${basicAuthenticationUserRole}")
    private String basicAuthenticationUserRole;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(this.basicAuthenticationUsername)
                .password(this.basicAuthenticationPassword)
                .roles(this.basicAuthenticationUserRole);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().anyRequest().fullyAuthenticated();
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        if ("production".equals(this.activeProfile)) {
            httpSecurity.requiresChannel().anyRequest().requiresSecure();
        }
    }
}
