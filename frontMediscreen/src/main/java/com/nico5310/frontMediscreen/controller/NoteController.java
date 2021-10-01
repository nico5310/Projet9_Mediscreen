package com.nico5310.frontMediscreen.controller;

import com.nico5310.frontMediscreen.beans.NoteBean;
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

@Controller
public class NoteController {

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteMSProxy noteMSProxy;

    @Autowired
    private PatientMSProxy patientMSProxy;


    @GetMapping(value = "/note/list/{patientId}")
    public String listNote(@PathVariable ("patientId") Integer patientId, Model model) {
        logger.info("Get list notes from Proxy");

        model.addAttribute("note", noteMSProxy.listNote(patientId));
        return "note/listNote";
    }

    ////////   ADD REQUEST SAVE NOTE
    @GetMapping("/note/addForm")
    public String addForm(NoteBean note, Model model) {
        logger.info("Show note add Form");

        model.addAttribute("note", note);
        return "note/addNote"; // template html

    }

    @ApiOperation(value = "Saving new note")
    @PostMapping("/note/add")
    public String addNote( @Valid @ModelAttribute("note") NoteBean note, BindingResult result) {

        if (result.hasErrors()) {
            logger.error("ERROR, Add new note isn't possible");
            return "note/addNote"; // template html
        }else {
            logger.info("SUCCESS, add new note" + note + " is complete");
            noteMSProxy.addNote(note);
            return "redirect:/patient/list"; // controller url
        }
    }

    ///////////    UPDATE REQUEST
    @ApiOperation(value = "Show Update note form")
    @GetMapping("/note/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") String id, NoteBean note, Model model) {

        logger.info("Show Update form page by Id" + id);
        noteMSProxy.showUpdateForm(id);
        model.addAttribute("note", noteMSProxy.getNoteById(id));
        return "note/updateNote"; // template html
    }

    @ApiOperation(value = "Update note")
    @PostMapping("/note/update/{id}")
    public String updateNote(@PathVariable ("id") String id,@Valid NoteBean note, BindingResult result, Model model) {

        model.addAttribute("note", note);
        if (result.hasErrors()) {
            logger.error("ERROR, Update note isn't valid");

            return "note/updateNote"; // template html
        }else {
            logger.info("SUCCESS, Update note is complete");
            noteMSProxy.updateNote(id, note);
            return "redirect:/patient/list"; // template html
        }

    }



}
