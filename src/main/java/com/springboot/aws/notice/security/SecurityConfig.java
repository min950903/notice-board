package com.springboot.aws.notice.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.springboot.aws.notice.security.service.CustomOauth2UserService;
import com.springboot.aws.notice.web.role.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig 
                extends WebSecurityConfigurerAdapter {
    
    private final CustomOauth2UserService customOauth2UserService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/notice/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            .and()
               .oauth2Login()
                   .defaultSuccessUrl("/")
                   .userInfoEndpoint()
                       .userService(customOauth2UserService);
    }
}
