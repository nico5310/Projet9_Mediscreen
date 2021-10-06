package com.nico5310.noteMicroservice.service;


import com.nico5310.noteMicroservice.model.Note;
import org.springframework.ui.Model;

import java.util.List;

public interface NoteService  {


    List<Note> findByPatientId(Integer patientId);

    Note getNoteById(String id);


    Note addNote(Note note);

    Note showUpdateNoteForm(String id, Model model);

    Note updateNote(String id, Note note);


    void deleteNoteById(String id);
}
