package com.example.springdocopenapiwithglobalsecurityscheme;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/**"
                        )
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and().csrf().disable()

        ;
    }
}
