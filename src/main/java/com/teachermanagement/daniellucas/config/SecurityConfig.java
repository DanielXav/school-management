package com.teachermanagement.daniellucas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	private static final String[] AUTH_WHITELIST = {
			"/v2/api-docs",
			"/signup",
			"/h2-console/**",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**"
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		// acesso ao Banco de Dados em memória (H2)
		http.cors().and().csrf().disable();
		http.headers().frameOptions().sameOrigin();
		http.authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.mvcMatchers("/coffees/**").authenticated();
	}
}