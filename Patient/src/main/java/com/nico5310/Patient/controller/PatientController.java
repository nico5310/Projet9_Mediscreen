package com.nico5310.Patient.controller;

import com.nico5310.Patient.model.Patient;
import com.nico5310.Patient.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    ////////  GET REQUEST
    @ApiOperation(value = "Get all patients")
    @RequestMapping("/patient/list")
    public List<Patient> listPatient() {
        logger.info("Get list patient");
        return patientService.listPatient();
    }

    @ApiOperation(value = "Get patient by id")
    @GetMapping("/patient/getById")
    public Patient getById(@RequestParam Integer id) {
        logger.info("Get patient by Id");
        return patientService.getById(id);
    }

    /////////   ADD REQUEST
//    @ApiOperation(value = "Show Add form")
//    @GetMapping("/patient/addForm")
//    public String addForm(Patient patient, Model model) {
//        logger.info("Show patient add Form");
//        patientService.addForm(patient, model);
//        return "addPatient"; // template html
//
//    }
    @ApiOperation(value = "Saving new patient")
    @PostMapping("/patient/add")
    public Patient addPatient(@RequestBody Patient patient) {

        logger.info("SUCCESS, add new Patient is complete");
        return patientService.addPatient(patient);
    }

    ///////////    UPDATE REQUEST
    @ApiOperation(value = "Show Update patient form")
    @GetMapping("/patient/updateForm/{id}")
    public void updateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Show Update form");
        patientService.updateForm(id, model);
    }

    @ApiOperation(value = "Update patient")
    @PostMapping("/patient/update/{id}")
    public Patient updatePatient(@PathVariable ("id") Integer id, @RequestBody @Valid Patient patient) {

        logger.info("SUCCESS, Update patient is complete");
        return patientService.updatePatient(id, patient);
    }

    ///////   DELETE REQUEST
    @ApiOperation(value = "Delete patient")
    @GetMapping("/patient/delete/{id}")
    public void deletePatient(@PathVariable ("id") Integer id) {

        logger.info("SUCCESS, Patient is correctly delete");
        patientService.deletePatient(id);
    }

}
