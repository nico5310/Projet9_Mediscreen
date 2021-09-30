package com.nico5310.noteMicroservice.controller;

import com.nico5310.noteMicroservice.model.Note;
import com.nico5310.noteMicroservice.service.NoteService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    ///////////   GET REQUEST //////////////
    @ApiOperation(value = "Get all notes")
    @GetMapping("/notes/list")
    public List<Note> listNote(@RequestParam Integer patientId) {

        logger.info("Get list notes from DB for :" + patientId);
        return noteService.findAllNotesByPatientId(patientId);
    }

    @ApiOperation(value = "Get note by id")
    @GetMapping("/notes/getNoteById/{id}")
    public Note getNoteById(@RequestParam String id) {

        logger.info("Get note by Id from DB");
        return noteService.getNoteById(id);
    }


    //////////  ADD REQUEST   ///////////////
    @ApiOperation(value = "Saving new note")
    @PostMapping("/notes/add")
    public Note addNote(@RequestBody Note note) {

        logger.info("SUCCESS, add new note" + note + " is complete to DB");
        return noteService.addNote(note);
    }

    /////////  UPDATE REQUEST  ///////////////
    @ApiOperation(value = "Update note")
    @PostMapping("/notes/update/{id}")
    public Note updatePatient(@PathVariable ("id") String id, @RequestBody @Valid Note note) {

        logger.info("SUCCESS, Update note is complete");
        return noteService.updateNote(id, note);
    }

}
