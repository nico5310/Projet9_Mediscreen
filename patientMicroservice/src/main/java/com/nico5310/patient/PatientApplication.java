package com.nico5310.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

@EnableSwagger2
@SpringBootApplication
public class PatientApplication {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		SpringApplication.run(PatientApplication.class, args);
	}


}
