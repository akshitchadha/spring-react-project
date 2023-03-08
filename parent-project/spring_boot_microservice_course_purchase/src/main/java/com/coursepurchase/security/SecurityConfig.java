package com.coursepurchase.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${app.course.purchase.username}")
    private String inMemoryUserName;

    @Value("${app.course.purchase.password}")
    private String inMemoryPassword;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser(inMemoryUserName).password(passwordEncoder.encode(inMemoryPassword)
        ).roles("USer");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();
        //http.authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
    }
}
