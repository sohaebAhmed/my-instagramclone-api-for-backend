package com.instagram.myClone.config;

import org.hibernate.internal.SessionCreationOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {
	
	@Bean 
	public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/signup").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterAfter(null, null)
		.addFilterBefore(null, null)
		.csrf().disable()
		.fromLogin().and().httpBasic();
		
		return http.build(); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
