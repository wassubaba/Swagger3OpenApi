package com.openapi.definition.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	
	private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/h2-console/**",
            "/console/**",
            "/account/**"
    };
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails normalUser = User
				.withUsername("wassubaba")
				.password(passwordEncoder().encode("wassubaba"))
				.roles("NORMAL")
				.build();
		
		UserDetails adminUser = User
				.withUsername("waseem")
				.password(passwordEncoder().encode("waseem"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(normalUser, adminUser);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		return http
		        .authorizeHttpRequests().requestMatchers(AUTH_WHITE_LIST).permitAll()
		        .and()
		        .authorizeHttpRequests().anyRequest().authenticated()
		        .and()
		        .httpBasic()
		        .and()
		        .csrf().disable()
		        .headers().frameOptions().disable()
		        .and()
		        .build();
		
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
