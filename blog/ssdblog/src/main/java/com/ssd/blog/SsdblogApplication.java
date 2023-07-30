package com.ssd.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class SsdblogApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(SsdblogApplication.class, args);
	}
	
    @Bean
     ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    
}
