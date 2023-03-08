package com.example.spring_boot_microservice1_course.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration

public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//    private CustomAuthenticationProvider  customAuthenticationProvider;
//
//    public  MySecurityConfig (CustomAuthenticationProvider customAuthenticationProvider)
//    {
//            this.customAuthenticationProvider=customAuthenticationProvider;
//    }

    @Value("${app.course.creation.username}")
    private String inMemoryUserName;

    @Value("${app.course.creation.password}")
    private String inMemoryPassword;




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

     auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser(inMemoryUserName)
             .password(passwordEncoder().encode(inMemoryPassword)).roles("User");
    }





    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic();
    http.csrf().disable();
    http.authorizeRequests().anyRequest().permitAll();
   http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);
    }


    @Bean
    public  BCryptPasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }
}
