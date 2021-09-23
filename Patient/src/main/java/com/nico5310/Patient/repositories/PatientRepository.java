package com.nico5310.Patient.repositories;

import com.nico5310.Patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {


}
