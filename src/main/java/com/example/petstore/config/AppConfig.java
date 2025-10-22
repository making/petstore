package com.example.petstore.config;

import java.time.Clock;
import java.time.InstantSource;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration(proxyBeanMethods = false)
class AppConfig {

	@Bean
	Clock jdbcClock(JdbcClient jdbcClient) {
		InstantSource instantSource = () -> jdbcClient
			.sql("SELECT COALESCE((SELECT date_time FROM system_date), NOW())")
			.query(OffsetDateTime.class)
			.single()
			.toInstant();
		return instantSource.withZone(ZoneId.systemDefault());
	}

	@Bean
	CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeHeaders(true);
		loggingFilter.setIncludeClientInfo(true);
		return loggingFilter;
	}

}
