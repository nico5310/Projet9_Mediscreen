package com.nico5310.frontMediscreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients("com.nico5310.frontMediscreen")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FrontMediscreenApplication {

	public static void main(String[] args) {

		SpringApplication.run(FrontMediscreenApplication.class, args);
	}

}
