package com.nico5310.Patient.service;

import com.nico5310.Patient.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();

    Patient getById(int id);

    Patient addPatient(Patient patient);

    Patient updatePatient(int id, Patient patient);

    void deletePatient(int id);
}
