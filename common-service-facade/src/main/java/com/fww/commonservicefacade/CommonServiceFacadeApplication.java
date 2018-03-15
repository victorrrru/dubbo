package com.fww.commonservicefacade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CommonServiceFacadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonServiceFacadeApplication.class, args);
	}
}
