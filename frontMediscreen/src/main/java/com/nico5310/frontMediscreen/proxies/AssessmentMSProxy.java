package com.nico5310.frontMediscreen.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "assessment-microservice", url = "${assessment.url}")
public interface AssessmentMSProxy {

    @GetMapping("/assessment/{id}")
    String getDiabetesLevelRisk(@PathVariable("id") Integer id);

    @GetMapping ("/assessment/age/{id}")
    int getAge(@PathVariable ("id") Integer id);

    @GetMapping ("/assessment/triggers/{id}")
    int getTriggers(@PathVariable ("id") Integer id);

}
