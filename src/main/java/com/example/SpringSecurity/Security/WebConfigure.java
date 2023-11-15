/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Security;

import com.example.SpringSecurity.Security.Jwt.JwtAuthenticationFilter;
import com.example.SpringSecurity.Service.Impl.UserDetailService;
import com.example.SpringSecurity.common.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author ADMIN
 */
@EnableWebSecurity
public class WebConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailService userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) // Cung các userservice cho spring security
                .passwordEncoder(new BCryptPasswordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final String[] managerEndpoints = {
            "/user/{id}",
            "/user/delete/{id}",
            "/user/create",
            "/user/update/{id}",
            "/address/{id}",
            "/address/create",
            "address/update/{id}",
            "/address/delete/{id}"
        };

        final String[] generalEndpoints = {
            "/user",
            "/address",
            "/register",
            "/login"
        };

        http.csrf((csrf) -> csrf.disable());
        http.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class
        );
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(generalEndpoints).permitAll()// Cho phép tất cả mọi người truy cập vào địa chỉ này
                .antMatchers(managerEndpoints).hasAnyAuthority(ERole.ROLE_ADMIN.toString())
                .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập
    }
}
