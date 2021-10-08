package com.nico5310.frontMediscreen.controller;

import com.nico5310.frontMediscreen.beans.NoteBean;
import com.nico5310.frontMediscreen.beans.PatientBean;
import com.nico5310.frontMediscreen.proxies.NoteMSProxy;
import com.nico5310.frontMediscreen.proxies.PatientMSProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {NoteController.class})
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @MockBean
    private PatientMSProxy patientMSProxy;

    @Autowired
    private NoteController noteController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteMSProxy noteMsProxy;

    @Test
    @DisplayName(" Test get listNote")
    public void listNoteTest() throws Exception {

        NoteBean noteBean1 = new NoteBean("1", 1, LocalDate.of(2000, 10, 10), "test1");

        when(noteMsProxy.listNote(any(Integer.class))).thenReturn(new ArrayList<NoteBean>());
        when(patientMSProxy.getById(any(Integer.class))).thenReturn(new PatientBean());

        mockMvc.perform(get("/note/list/{id}", 1)).andExpect(status().isOk());
    }


    @Test
    @DisplayName(" Test getAddNote with valid param")
    public void add_Patient_With_Valid_ParamTest() throws Exception {

        NoteBean note1 = new NoteBean();
        when(noteMsProxy.addNote(any(NoteBean.class))).thenReturn(note1);

        mockMvc.perform(post("/note/add/1").sessionAttr("note", note1)
                                           .param("id", "1")
                                           .param("patientId", "1")
                                           .param("date", "2020-10-30")
                                           .param("recommendation", "Test"))
               .andExpect(status().is3xxRedirection())
               .andExpect(model().hasNoErrors())
               .andExpect(view().name("redirect:/patient/list"));
    }

    @Test
    @DisplayName(" Test getAddNote with valid param")
    public void not_Add_Patient_With_Invalid_ParamTest() throws Exception {

        NoteBean note1 = new NoteBean();
        when(noteMsProxy.addNote(any(NoteBean.class))).thenReturn(note1);

        mockMvc.perform(post("/note/add/1").sessionAttr("note", note1)
                                           .param("id", "1")
                                           .param("patientId", "1")
                                           .param("date", "error")
                                           .param("recommendation", "Test"))
               .andExpect(status().isOk())
               .andExpect(model().hasErrors())
               .andExpect(view().name("note/addNote.html"));
    }


    @Test
    @DisplayName(" Test showUpdateNoteForm")
    public void showUpdateNoteFormTest() throws Exception {

        NoteBean noteBean1 = new NoteBean("1", 1, LocalDate.of(2000, 10, 10), "test");
        when(noteMsProxy.getNoteById(anyString())).thenReturn(noteBean1);
        mockMvc.perform(get("/note/showUpdateForm/{id}/{patientId}", "1", 1)).andExpect(status().isOk());

    }




    @Test
    @DisplayName(" Test updateNote with valid param")
    public void update_Note_With_Valid_ParamTest() throws Exception {

        NoteBean note1 = new NoteBean();
        when(noteMsProxy.getNoteById(anyString())).thenReturn(note1);
        when(noteMsProxy.addNote(any(NoteBean.class))).thenReturn(note1);

        mockMvc.perform(post("/note/update/1/1").sessionAttr("note", note1)
                                                .param("id", "1")
                                                .param("patientId", "1")
                                                .param("date", String.valueOf(LocalDate.of(2000, 10, 10)))
                                                .param("recommendation", "test2"))
               .andExpect(status().is3xxRedirection())
               .andExpect(model().hasNoErrors())
               .andExpect(view().name("redirect:/patient/list"));
    }

    @Test
    @DisplayName(" Test updateNote with invalid param")
    public void not_Update_Note_With_Valid_ParamTest() throws Exception {

        NoteBean note1 = new NoteBean();
        when(noteMsProxy.getNoteById(anyString())).thenReturn(note1);
        when(noteMsProxy.addNote(any(NoteBean.class))).thenReturn(note1);

        mockMvc.perform(post("/note/update/1/1").sessionAttr("note", note1)
                                                .param("id", "1")
                                                .param("patientId", "1")
                                                .param("date", "error")
                                                .param("recommendation", "test2"))
               .andExpect(status().isOk())
               .andExpect(model().hasErrors())
               .andExpect(view().name("note/updateNote"));
    }


    @Test
    @DisplayName(" Test delete note")
    public void deleteNoteTest() throws Exception {

        mockMvc.perform(get("/note/delete/1")).andExpect(status().is3xxRedirection());
    }

}
