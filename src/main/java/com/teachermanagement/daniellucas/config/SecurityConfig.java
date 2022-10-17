package com.teachermanagement.daniellucas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public UserDetailsService userDetailsService() {
		return null;
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	 	http.httpBasic();
        http.cors().and().csrf().disable();
        http.headers().frameOptions().sameOrigin(); 
		
		http.authorizeRequests().anyRequest().permitAll(); // authenticated
	}
}
