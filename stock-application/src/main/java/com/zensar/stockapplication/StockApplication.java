package com.zensar.stockapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.zensar.stockapplication.entity.Stock;

@SpringBootApplication
public class StockApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return super.configure(builder);
	}

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
		Stock s=new Stock();
		
	}
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

}
