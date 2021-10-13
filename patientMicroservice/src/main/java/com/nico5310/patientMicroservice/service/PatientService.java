package com.nico5310.patient.service;

import com.nico5310.patient.model.Patient;
import org.springframework.ui.Model;

import java.util.List;

public interface PatientService {


    List<Patient> listPatient();

    Patient getById(int id);

    Patient addPatient(Patient patient);

    Patient showUpdateForm(Integer id, Model model);

    Patient updatePatient(Integer id, Patient patient);

    void deletePatient(Integer id);
}
