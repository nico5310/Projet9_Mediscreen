package com.nico5310.patient.controller;

import com.nico5310.patient.model.Patient;
import com.nico5310.patient.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

        logger.info("Get list patient from DB");
        return patientService.listPatient();
    }

    @ApiOperation(value = "Get patient by id")
    @GetMapping("/patient/getById")
    public Patient getById(@RequestParam Integer id) {

        logger.info("Get patient by Id from DB");
        return patientService.getById(id);
    }

    @ApiOperation(value = "Saving new patient")
    @PostMapping("/patient/add")
    public Patient addPatient(@RequestBody Patient patient) {

        logger.info("SUCCESS, add new patient" + patient + " is complete to DB");
        return patientService.addPatient(patient);
    }

    ///////////    UPDATE REQUEST
    @ApiOperation(value = "Show Update patient form")
    @GetMapping("/patient/showUpdateForm/{id}")
    public void showUpdateForm(@PathVariable("id") Integer id,Model model) {

        logger.info("Show Update form page is charged");
        Patient patient = patientService.getById(id);
        model.addAttribute("patient", patient);
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

        logger.info("SUCCESS, patient is correctly delete to DB");
        patientService.deletePatient(id);
    }

}