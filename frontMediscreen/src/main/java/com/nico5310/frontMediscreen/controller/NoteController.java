package com.nico5310.frontMediscreen.controller;

import com.nico5310.frontMediscreen.beans.NoteBean;
import com.nico5310.frontMediscreen.beans.PatientBean;
import com.nico5310.frontMediscreen.proxies.AssessmentMSProxy;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class NoteController {

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteMSProxy noteMSProxy;

    @Autowired
    private PatientMSProxy patientMSProxy;

    @Autowired
    private AssessmentMSProxy assessmentMSProxy;


    @GetMapping(value = "/note/list/{id}")
    public String listNote(@PathVariable ("id") Integer id, Model model) {

        logger.info("Get all notes of patient by id : " + id + " from proxy");
        PatientBean     patient    = patientMSProxy.getById(id);
        int age = assessmentMSProxy.getAge(id);
        int triggers = assessmentMSProxy.getTriggers(id);
        String assessment = assessmentMSProxy.getDiabetesLevelRisk(id);
        List<NoteBean> noteBeanList = noteMSProxy.listNote(id);

        model.addAttribute("patientName", patient.getFirstName() + " " + patient.getLastName());
        model.addAttribute("patientAge", age);
        model.addAttribute("patientGenre", patient.getGenre());
        model.addAttribute("triggers", triggers );
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute ("assessment", assessment);
        model.addAttribute("patient", patient);
        model.addAttribute("notes", noteBeanList);
        return "note/listNote.html";
    }

    ////////   ADD REQUEST SAVE NOTE
    @GetMapping("/note/addForm/{patientId}")
    public String addForm(@PathVariable ("patientId") Integer patientId, NoteBean note, Model model) {

        logger.info("Show note add Form is charged from proxy");
        PatientBean patient = patientMSProxy.getById(patientId);
        note.setPatientId(patientId);
        note.setDate(LocalDate.now());

        model.addAttribute("patientName", patient.getFirstName() + " " + patient.getLastName());
        model.addAttribute("note", note);
        return "note/addNote.html";

    }

    @ApiOperation(value = "Saving new note")
    @PostMapping("/note/add/{patientId}")
    public String addNote(@PathVariable ("patientId") Integer patientId, @Valid @ModelAttribute("note") NoteBean note, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR, Add new note isn't possible from proxy");
            return "note/addNote"; // template html
        }else {
            logger.info("SUCCESS, add new note is complete to DB from proxy");
            model.addAttribute("note", note);
            note.setDate(LocalDate.now());
            note.setRecommendation(note.getRecommendation());
            noteMSProxy.addNote(note);
            return "redirect:/note/list/"+ patientId;
        }
    }

    ///////////    UPDATE REQUEST
    @ApiOperation(value = "Show Update note form")
    @GetMapping("/note/showUpdateForm/{id}/{patientId}")
    public String showUpdateNoteForm(@PathVariable ("id") String id,@PathVariable ("patientId") Integer patientId, NoteBean note, Model model) {

        logger.info("Show Update form page by Id " + id + " from proxy");
        note.setPatientId(patientId);
        noteMSProxy.showUpdateNoteForm(id, patientId);
        model.addAttribute("note", noteMSProxy.getNoteById(id));
        return "note/updateNote"; // template html
    }

    @ApiOperation(value = "Update note")
    @PostMapping("/note/update/{id}/{patientId}")
    public String updateNote(@PathVariable ("id") String id,@PathVariable ("patientId") Integer patientId,@Valid @ModelAttribute("note") NoteBean note , BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR, Update note isn't valid");
            return "note/updateNote"; // template html
        }else {
            logger.info("SUCCESS, Update note is complete to proxy");
            note.setDate(LocalDate.now());
            noteMSProxy.updateNote(id, note);
            return "redirect:/note/list/" + patientId; // template html
        }
    }
    ///////   DELETE REQUEST
    @ApiOperation(value = "Delete note")
    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable String id) {

        logger.info("SUCCESS, this note is correctly delete to DB from proxy");
        noteMSProxy.deleteNote(id);
        return "redirect:/patient/list"; // controller url
    }

}
