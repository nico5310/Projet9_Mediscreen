package com.nico5310.Patient.service;

import com.nico5310.Patient.model.Patient;
import com.nico5310.Patient.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        logger.info("Get all patients from DB");
        return patientRepository.findAll();
    }

    @Override
    public Patient getById(int id) {
        logger.info("Get patient by id");
        return patientRepository.getById(id);
    }

    @Override
    public Patient addPatient(Patient patient) {
        logger.info("Saving new patient in DB");
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(int id, Patient patient) {
        logger.info("Updating patient is complete");
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int id) {
        logger.info("Delete patient is complete");
        patientRepository.deleteById(id);
    }


}
