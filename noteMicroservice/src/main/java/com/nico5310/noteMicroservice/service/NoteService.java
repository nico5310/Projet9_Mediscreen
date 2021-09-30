package com.nico5310.noteMicroservice.service;


import com.nico5310.noteMicroservice.model.Note;

import java.util.List;

public interface NoteService  {


    List<Note> findAllNotesByPatientId(Integer patientId);

    Note getNoteById(String id);


    Note addNote(Note note);

    Note updateNote(String id, Note note);

    void deleteNoteById(String id);
}
