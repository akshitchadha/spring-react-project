package com.course.api.gateway.security;

import com.course.api.gateway.model.Role;
import com.course.api.gateway.security.jwt.JwtAuthorisationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private  PasswordEncoder passwordEncoder;
   private  CustomUserDetailsService customUserDetailsService;
   private  JwtAuthorisationFilter JwtAuthorisationFilter;

    public SecurityConfig(PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService, JwtAuthorisationFilter jwtAuthorisationFilter) {
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        JwtAuthorisationFilter = jwtAuthorisationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        /*
        * below statement  allow  access to all links under /api/authentication/ without authentication but
        * all other links can only be accessed by authenticated users
        */

        http.authorizeRequests().antMatchers("/api/authentication/**").permitAll()
                .antMatchers(HttpMethod.GET,"/gateway/course/getcourses").permitAll()
                .antMatchers(HttpMethod.DELETE,"gateway/course/deletecourse/{id}").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST,"gateway/course/createcourse").hasRole(Role.ADMIN.name())
                .anyRequest().authenticated();
        http.addFilterBefore(JwtAuthorisationFilter, UsernamePasswordAuthenticationFilter.class);

    }



    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
