package com.arvind.crawler.stockanalyzer;

import java.util.Date;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.arvind.crawler.stockanalyzer.response.CowinResolver;

@Configuration
public class ServiceConfig {

	@Bean
	public Function<Date, CowinResolver> cowinResolverFactory() {
		return date -> cowinResolver(date);
	}

	@Bean
	@Scope(value = "prototype")
	public CowinResolver cowinResolver(Date date) {
		return new CowinResolver(date);
	}
}
