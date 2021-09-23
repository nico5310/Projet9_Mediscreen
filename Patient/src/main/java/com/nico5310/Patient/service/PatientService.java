package com.nico5310.Patient.service;

import com.nico5310.Patient.model.Patient;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface PatientService {


    List<Patient> listPatient();

    Patient getById(int id);

    Patient addPatient(Patient patient);

    Patient showUpdateForm(Integer id, Model model);

    Patient updatePatient(Integer id, Patient patient);

    void deletePatient(Integer id);
}
