package com.example.intechchat.security.config;

import com.example.intechchat.services.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class IntechChatSecurityAdapterConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final MyUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] allowedResources = {"/api/v1/register"};

        http.authorizeRequests().
                antMatchers(allowedResources).
                permitAll().
                anyRequest().
                authenticated().
                and().
                formLogin().
                and().
                httpBasic();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
