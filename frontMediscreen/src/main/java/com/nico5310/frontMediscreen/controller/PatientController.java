package com.nico5310.frontMediscreen.controller;

import com.nico5310.frontMediscreen.beans.PatientBean;
import com.nico5310.frontMediscreen.proxies.NoteMSProxy;
import com.nico5310.frontMediscreen.proxies.PatientMSProxy;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientMSProxy patientMSProxy;

    @Autowired
    private NoteMSProxy noteMSProxy;


    @GetMapping (value = "patient/list")
    public String listPatient(Model model) {

        logger.info("Get list patients from Proxy");
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("patients", patientMSProxy.listPatient());
        return "patient/listPatient"; // template html
    }

    ////////   ADD REQUEST SAVE PATIENT

    @GetMapping("/patient/addForm")
    public String addForm(PatientBean patient, Model model) {

        logger.info("Show add patient form age is charged from proxy");
        model.addAttribute("patient", patient);
        return "patient/addPatient"; // template html

    }


    @ApiOperation(value = "Saving new patient")
    @PostMapping("/patient/add")
    public String addPatient( @Valid @ModelAttribute("patient") PatientBean patient, BindingResult result) {

        if (result.hasErrors()) {
            logger.error("ERROR, Add new patient isn't possible");
            return "patient/addPatient"; // template html
        }else {
            logger.info("SUCCESS, add new patient" + patient + " is complete to DB from proxy");
            patientMSProxy.addPatient(patient);
            return "redirect:/patient/list"; // controller url
        }
    }

    ///////////    UPDATE REQUEST
    @ApiOperation(value = "Show Update patient form")
    @GetMapping("/patient/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, PatientBean patient, Model model) {

        logger.info("Show Update form page by Id is charged from proxy");
        patientMSProxy.showUpdateForm(id);
        model.addAttribute("patient", patientMSProxy.getById(id));
        return "patient/updatePatient"; // template html
    }

    @ApiOperation(value = "Update patient")
    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable ("id") Integer id,@Valid PatientBean patient, BindingResult result, Model model) {

        model.addAttribute("patient", patient);
        if (result.hasErrors()) {
            logger.error("ERROR, Update user isn't valid");
            patient.setId(id);
            return "patient/updatePatient"; // template html
        }else {
            logger.info("SUCCESS, Update " + patient + " is complete from proxy");
            patientMSProxy.updatePatient(id, patient);
            return "redirect:/patient/list"; // template html
        }

    }

    ///////   DELETE REQUEST
    @ApiOperation(value = "Delete patient")
    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {

        logger.info("SUCCESS, patient and all her notes are correctly delete to DB from proxy");
        patientMSProxy.deletePatient(id);
        noteMSProxy.deleteAllNotesByPatientId(id);
        return "redirect:/patient/list "; // controller url
    }


}
