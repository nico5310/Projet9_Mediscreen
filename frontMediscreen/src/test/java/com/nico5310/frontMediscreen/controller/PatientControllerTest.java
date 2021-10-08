package com.nico5310.frontMediscreen.controller;

import com.nico5310.frontMediscreen.beans.PatientBean;
import com.nico5310.frontMediscreen.proxies.PatientMSProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ImportAutoConfiguration(FeignAutoConfiguration.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private PatientController patientController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientMSProxy patientMSProxy;

    @Test
    @DisplayName(" Test get listPatient")
    public void getListPatientTest() throws Exception {

        mockMvc.perform(get("/patient/list")).andExpect(status().isOk());

    }

    @Test
    @DisplayName(" Test get AddForm")
    public void getAddFormTest() throws Exception {

        mockMvc.perform(get("/patient/addForm")).andExpect(status().isOk());

    }

    @Test
    @DisplayName(" Test getAddPatient with valid param")
    public void add_Patient_With_Valid_ParamTest() throws Exception {

        PatientBean patient1 = new PatientBean();
        when(patientMSProxy.addPatient(any(PatientBean.class))).thenReturn(patient1);

        mockMvc.perform(post("/patient/add/").sessionAttr("patient", patient1)
                                             .param("family", "Doe1")
                                             .param("given", "John1")
                                             .param("dob", String.valueOf(LocalDate.of(2000, 10, 10)))
                                             .param("sex", "M")
                                             .param("address", "1 address")
                                             .param("phone", "100-200-4000"))
               .andExpect(status().is3xxRedirection())
               .andExpect(model().hasNoErrors())
               .andExpect(view().name("redirect:/patient/list"));
    }

    @Test
    @DisplayName(" Test getAddPatient with invalid param")
    public void not_Add_Patient_With_Invalid_ParamTest() throws Exception {

        PatientBean patient1 = new PatientBean();
        when(patientMSProxy.addPatient(any(PatientBean.class))).thenReturn(patient1);

        mockMvc.perform(post("/patient/add/").sessionAttr("patient", patient1)
                                             .param("family", "Doe1")
                                             .param("given", "John1")
                                             .param("dob", "error")
                                             .param("sex", "M")
                                             .param("address", "1 address")
                                             .param("phone", "100-200-4000"))
               .andExpect(status().isOk())
               .andExpect(model().hasErrors())
               .andExpect(view().name("patient/addPatient"));
    }

    @Test
    @DisplayName(" Test get UpdateForm")
    public void getUpdateFormTest() throws Exception {

        PatientBean patient1 = new PatientBean(1, "Doe1", "John1", LocalDate.of(2000, 10, 10), 'M', "1 address", "100-200-4000");
        when(patientMSProxy.getById(anyInt())).thenReturn(patient1);
        mockMvc.perform(get("/patient/showUpdateForm/{id}", 1)).andExpect(status().isOk());

    }

    @Test
    @DisplayName(" Test updatePatient with valid param")
    public void update_Patient_With_Valid_ParamTest() throws Exception {

        PatientBean patient1 = new PatientBean();
        when(patientMSProxy.getById(anyInt())).thenReturn(patient1);
        when(patientMSProxy.addPatient(any(PatientBean.class))).thenReturn(patient1);

        mockMvc.perform(post("/patient/update/1").sessionAttr("patient", patient1)
                                                 .param("family", "Doe1")
                                                 .param("given", "John1")
                                                 .param("dob", String.valueOf(LocalDate.of(2000, 10, 10)))
                                                 .param("sex", "F")
                                                 .param("address", "1 address")
                                                 .param("phone", "100-200-4000"))
               .andExpect(status().is3xxRedirection())
               .andExpect(model().hasNoErrors())
               .andExpect(view().name("redirect:/patient/list"));
    }

    @Test
    @DisplayName(" Test updatePatient with invalid param")
    public void not_Update_Patient_With_Invalid_ParamTest() throws Exception {

        PatientBean patient1 = new PatientBean();
        when(patientMSProxy.getById(anyInt())).thenReturn(patient1);
        when(patientMSProxy.addPatient(any(PatientBean.class))).thenReturn(patient1);

        mockMvc.perform(post("/patient/update/1").sessionAttr("patient", patient1)
                                                 .param("family", "Doe1")
                                                 .param("given", "John1")
                                                 .param("dob", "ERROR")
                                                 .param("sex", "F")
                                                 .param("address", "1 address")
                                                 .param("phone", "100-200-4000"))
               .andExpect(status().isOk())
               .andExpect(model().hasErrors())
               .andExpect(view().name("patient/updatePatient"));
    }

    @Test
    @DisplayName(" Test delete patient")
    public void deletePatientTest() throws Exception {

        mockMvc.perform(get("/patient/delete/1")).andExpect(status().is3xxRedirection());
    }

}
