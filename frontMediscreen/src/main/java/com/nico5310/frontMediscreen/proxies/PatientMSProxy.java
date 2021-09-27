package com.nico5310.frontMediscreen.proxies;

import com.nico5310.frontMediscreen.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "patient-microservice", url = "${patient.url}")
public interface PatientMSProxy {

    //  GET
    @GetMapping(value = "/patient/list")
    List<PatientBean> listPatient();

    @GetMapping (value = "/patient/getById")
    PatientBean getById(@RequestParam Integer id);

    // ADD
//    @GetMapping("/patient/addForm")
//    String addForm(PatientBean patient, Model model);

    @PostMapping(value = "/patient/add")
    PatientBean addPatient( PatientBean patient);

    // Update
    @GetMapping("/patient/showUpdateForm/{id}")
    PatientBean showUpdateForm(@PathVariable("id") Integer id);

    @PostMapping(value = "/patient/update/{id}")
    PatientBean updatePatient(@PathVariable("id") Integer id, @Valid PatientBean patient);

    // DELETE
    @GetMapping("/patient/delete/{id}")
    void deletePatient(@PathVariable ("id") Integer id);

}
