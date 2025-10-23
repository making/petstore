package com.example.petstore.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.formLogin(formLogin -> formLogin.loginPage("/account/signon?form")
				.loginProcessingUrl("/account/signon")
				.failureUrl("/account/signon?form&error=true")
				.usernameParameter("j_username")
				.passwordParameter("j_password"))
			.logout(logout -> logout.logoutUrl("/account/signoff").logoutSuccessUrl("/"))
			.authorizeHttpRequests(authz -> authz
			// @formatter:off
				.requestMatchers("/order/**").authenticated()
				.anyRequest().permitAll()
			// @formatter:on
			)
			.build();
	}

	@SuppressWarnings("deprecation")
	@Bean
	PasswordEncoder passwordEncoder() {
		return new DelegatingPasswordEncoder("bcrypt",
				Map.of("bcrypt", new BCryptPasswordEncoder(), "noop", NoOpPasswordEncoder.getInstance()));
	}

}
