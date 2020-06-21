package com.ebanking.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	private static final String RESOURCE_ID = "resource_id";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/user/**").permitAll();

		http.anonymous().disable().authorizeRequests()
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}
