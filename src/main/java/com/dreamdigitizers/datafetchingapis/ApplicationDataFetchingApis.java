package com.dreamdigitizers.datafetchingapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class ApplicationDataFetchingApis extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(ApplicationDataFetchingApis.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationDataFetchingApis.class, args);
	}
}