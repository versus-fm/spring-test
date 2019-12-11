package dev.mellberg.spring_test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import dev.mellberg.spring_test.conversion.SessionStringConverter;

@Configuration
public class ApplicationConfiguration {

	@Primary
	@Bean
	public ConversionService conversionService () {
		DefaultConversionService service = new DefaultConversionService();
		service.addConverter(new SessionStringConverter());
		return service;
	}
}