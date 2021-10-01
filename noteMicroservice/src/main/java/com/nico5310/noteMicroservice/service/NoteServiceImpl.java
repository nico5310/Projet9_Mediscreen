package com.nico5310.noteMicroservice.service;

import com.nico5310.noteMicroservice.model.Note;
import com.nico5310.noteMicroservice.repositories.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public List<Note> findAllNotesByPatientId(Integer patientId) {

        logger.info("Get all notes of patient by id : " + patientId);
        return noteRepository.findByPatientId(patientId);
    }


    @Override
    public Note getNoteById(String id) {

        logger.info("Get note by id :" + id);
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No found note for this id :" + id));
    }

    @Override
    public Note addNote(Note note) {

        logger.info("Saving new note to patient");
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(String id, Note note) {

        logger.info("Updating note is complete");
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(String id) {

        logger.info("Delete note is complete");
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:" + id));
        noteRepository.deleteById(id);
    }

}
