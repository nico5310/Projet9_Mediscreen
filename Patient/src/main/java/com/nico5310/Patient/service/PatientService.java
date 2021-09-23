package com.nico5310.Patient.service;

import com.nico5310.Patient.model.Patient;
import org.springframework.ui.Model;

import java.util.List;

public interface PatientService {


    List<Patient> listPatient();

    Patient getById(int id);

//    void addForm(Patient patient, Model model);

    Patient addPatient(Patient patient);

    void updateForm(Integer id, Model model);

    Patient updatePatient(Integer id, Patient patient);

    void deletePatient(Integer id);
}
