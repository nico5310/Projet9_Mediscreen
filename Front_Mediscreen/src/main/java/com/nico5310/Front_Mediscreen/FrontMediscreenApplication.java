package com.nico5310.Front_Mediscreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.util.Locale;

@EnableFeignClients("com.nico5310.Front_Mediscreen")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FrontMediscreenApplication {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		SpringApplication.run(FrontMediscreenApplication.class, args);
	}

}