package com.nico5310.assessmentMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients("com.nico5310.assessmentMicroservice")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AssessmentMicroserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(AssessmentMicroserviceApplication.class, args);
	}

}
