package com.nico5310.Patient.controller;

import com.nico5310.Patient.model.Patient;
import com.nico5310.Patient.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@Validated
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @ApiOperation(value = "Get all patients")
    @GetMapping("/patient/list")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @ApiOperation(value = "Get patient by id")
    @GetMapping("/patient/getById")
    public Patient getById(@RequestParam Integer id) {
        return patientService.getById(id);
    }

    @ApiOperation(value = "Saving new patient")
    @PostMapping("/patient/add")
    public Patient addPatient(@RequestBody @Valid Patient patient) {
        return patientService.addPatient(patient);

    }

    @ApiOperation(value = "Update patient")
    @PutMapping("/patient/update")
    public Patient updatePatient(@RequestParam Integer id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    @ApiOperation(value = "Delete patient")
    @DeleteMapping("/patient/delete/{id}")
    public void deleteById(@PathVariable int id) {
        patientService.deletePatient(id);
    }

}
