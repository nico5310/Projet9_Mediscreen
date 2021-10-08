package com.nico5310.assessmentMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class AssessmentMicroserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(AssessmentMicroserviceApplication.class, args);
	}

}
