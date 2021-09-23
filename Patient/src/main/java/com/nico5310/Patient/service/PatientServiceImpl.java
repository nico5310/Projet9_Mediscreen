package com.nico5310.Patient.service;

import com.nico5310.Patient.model.Patient;
import com.nico5310.Patient.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

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
        logger.info("Get patient by id");
        return patientRepository.getById(id);
    }

    ////////     ADD METHOD
//        @Override
//    public String addForm(Patient patient, Model model) {
//        model.addAttribute("addPatient", patient);// template html
//        return "addPatient"; // template html
//    }

    @Override
    public Patient addPatient(Patient patient) {

        logger.info("Saving new patient in DB");
        return patientRepository.save(patient);
    }

    ///////  UPDATE METHOD

    @Override
    public void updateForm(Integer id, Model model) {

        logger.info("Show Update form complete");
        Patient patient = patientRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        model.addAttribute("patient", patient);
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
        Patient patient =patientRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        patientRepository.delete(patient);
    }

}
