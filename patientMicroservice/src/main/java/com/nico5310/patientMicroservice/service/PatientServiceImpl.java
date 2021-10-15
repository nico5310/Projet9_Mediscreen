package com.nico5310.patientMicroservice.service;

import com.nico5310.patientMicroservice.model.Patient;
import com.nico5310.patientMicroservice.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


@Service
public class PatientServiceImpl implements PatientService{

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;


    ///////    GET METHOD
    @Override
    public List<Patient> listPatient() {
        logger.info("Get all patients from DB");
        return patientRepository.findAll();
    }

    @Override
    public Patient getById(int id) {
        logger.info("Get patient by id " + id);
        return patientRepository.getById(id);
    }

    ////////     ADD METHOD

    @Override
    public Patient addPatient(Patient patient) {

        logger.info("Saving new patient in DB");
        return patientRepository.save(patient);
    }

    ///////  UPDATE METHOD

    @Override
    public Patient showUpdateForm(Integer id, Model model) {

        logger.info("Show Update form complete");
        Patient patient = patientRepository.getById(id);
        model.addAttribute("patient", patient);
        return patient;
    }


    @Override
    public Patient updatePatient(Integer id, Patient patient) {

        logger.info("Updating patient is complete");
        return patientRepository.save(patient);
    }

    //////// DELETE METHOD

    @Override
    public void deletePatient(Integer id) {

        logger.info("Delete patient is complete");
        Patient patient = patientRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        patientRepository.delete(patient);
    }

}
