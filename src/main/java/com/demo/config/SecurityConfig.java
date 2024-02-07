package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity//(debug=true)
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	// filterChain protege las rutas
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http.formLogin().permitAll().and()
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/registro").permitAll())
				.authorizeHttpRequests().anyRequest().authenticated().and().build();
		
	}
	
	
	public PasswordEncoder passwordEncoder(){
	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}


//1:45 clase 5