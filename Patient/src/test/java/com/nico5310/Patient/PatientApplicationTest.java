package com.nico5310.Patient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PatientApplication.class)
class PatientApplicationTest {

	@Test
	void contextLoads() {

	}

	@Test
	public void main() {
		PatientApplication.main(new String[] {});
	}

}
