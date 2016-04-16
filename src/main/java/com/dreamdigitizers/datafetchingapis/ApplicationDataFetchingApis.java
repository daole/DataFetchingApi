package com.dreamdigitizers.datafetchingapis;

import com.dreamdigitizers.datafetchingapis.controllers.services.classes.ServiceZing;
import com.dreamdigitizers.datafetchingapis.controllers.services.interfaces.IServiceZing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationDataFetchingApis {
	@Bean
	public IServiceZing zingService() {
		return new ServiceZing();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationDataFetchingApis.class, args);
	}
}
