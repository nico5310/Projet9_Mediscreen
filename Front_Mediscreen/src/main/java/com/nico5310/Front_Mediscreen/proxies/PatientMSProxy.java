package com.nico5310.Front_Mediscreen.proxies;

import com.nico5310.Front_Mediscreen.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "Patient-microservice", url = "${patient.url}")
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
    @GetMapping("/patient/updateForm/{id}")
    void updateForm(@PathVariable("id") Integer id);

    @PostMapping(value = "/patient/update/{id}")
    PatientBean updatePatient(@PathVariable("id") Integer id, @Valid PatientBean patient);

    // DELETE
    @GetMapping("/patient/delete/{id}")
    void deletePatient(@PathVariable ("id") Integer id);

}
