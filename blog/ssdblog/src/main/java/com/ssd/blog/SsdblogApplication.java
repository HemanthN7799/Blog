package com.ssd.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SsdblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsdblogApplication.class, args);
	}
	
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
