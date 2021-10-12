package com.nico5310.assessmentMicroservice.controller;

import com.nico5310.assessmentMicroservice.model.Assessments;
import com.nico5310.assessmentMicroservice.service.AgeCalculator;
import com.nico5310.assessmentMicroservice.service.AssessmentService;
import com.nico5310.assessmentMicroservice.service.TriggersCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssessmentController {

    private static final Logger logger = LoggerFactory.getLogger(AssessmentController.class);

    @Autowired
    AssessmentService assessmentService;

    @Autowired
    AgeCalculator ageCalculator;

    @Autowired
    TriggersCalculator triggersCalculator;


    @GetMapping("/assessment/{id}")
    public Assessments getDiabetesLevelRisk(@PathVariable ("id") Integer id) {
        return assessmentService.getDiabetesLevelRisk(id);
    }

    @GetMapping ("/assessment/age/{id}")
    public int getAge(@PathVariable ("id") Integer id) {
        return ageCalculator.getAge(id);
    }

    @GetMapping ("/assessment/triggers/{id}")
    public int getTriggers(@PathVariable ("id") Integer id) {
        return triggersCalculator.calculateTriggersInNotes(id);
    }


}
