package com.nico5310.Front_Mediscreen.controller;

import com.nico5310.Front_Mediscreen.beans.PatientBean;
import com.nico5310.Front_Mediscreen.proxies.PatientMSProxy;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientMSProxy patientMSProxy;

    @GetMapping (value = "patient/list")
    public String listPatient(Model model) {
        logger.info("Get list patient from Proxy");
        model.addAttribute("patients", patientMSProxy.listPatient());
        return "patient/listPatient"; // template html
    }


//    @GetMapping("/patient/getById")
//    public PatientBean getById(@RequestParam Integer id) {
//        logger.info("Get patient by Id");
//        return patientMSProxy.getById(id);
//    }

    /////////   ADD REQUEST FORMULAIRE

    @GetMapping("/patient/addForm")
    public String addForm(PatientBean patient, Model model) {
        logger.info("Show patient add Form");
        model.addAttribute("patient", patient);
        return "patient/addPatient"; // template html

    }

    ////////   ADD REQUEST SAVE PATIENT
    @ApiOperation(value = "Saving new patient")
    @PostMapping("/patient/add")
    public String addPatient( @Valid @ModelAttribute("patient") PatientBean patient, BindingResult result) {

        if (result.hasErrors()) {
            logger.error("ERROR, Add new Patient isn't possible");
            return "patient/addPatient"; // template html
        }else {
            logger.info("SUCCESS, add new Patient is complete");
            patientMSProxy.addPatient(patient);
            return "redirect:/patient/list"; // controller url
        }
    }

    ///////////    UPDATE REQUEST
    @ApiOperation(value = "Show Update patient form")
    @GetMapping("/patient/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, PatientBean patient, Model model) {
        logger.info("Show Update form page by Id" + id);
        patientMSProxy.showUpdateForm(id);
        model.addAttribute("patient", patient);
        return "patient/updatePatient"; // template html
    }

    @ApiOperation(value = "Update patient")
    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable ("id") Integer id,@Valid PatientBean patient, BindingResult result, Model model) {
        model.addAttribute("patient", patient);
        if (result.hasErrors()) {
            logger.error("ERROR, Update user ins't valid");
            patient.setId(id);
            return "patient/updatePatient"; // template html
        }else {
            logger.info("SUCCESS, Update patient is complete");
            patientMSProxy.updatePatient(id, patient);
            return "redirect:/patient/list"; // template html
        }

    }

    ///////   DELETE REQUEST
    @ApiOperation(value = "Delete patient")
    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {
        logger.info("SUCCESS, Patient is correctly delete");
        patientMSProxy.deletePatient(id);
        return "redirect:/patient/list "; // controller url
    }


}
