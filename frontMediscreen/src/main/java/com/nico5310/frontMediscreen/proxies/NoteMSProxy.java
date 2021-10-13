package com.nico5310.frontMediscreen.proxies;

import com.nico5310.frontMediscreen.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-microservice", url = "${note.url}")
public interface NoteMSProxy {

    /// GET
    @GetMapping("/note/list/{patientId}")
    List<NoteBean> listNote(@PathVariable("patientId") Integer patientId);

    @GetMapping("/note/getNoteById")
    NoteBean getNoteById(@RequestParam String id);


    /// ADD
    @PostMapping("/note/add")
    NoteBean addNote(@RequestBody NoteBean note);


    /// UPDATE
    @GetMapping("/note/showUpdateForm/{id}/{patientId}")
    NoteBean showUpdateNoteForm(@PathVariable("id") String id, @PathVariable ("patientId") Integer patientId);

    @PostMapping("/note/update/{id}")
    NoteBean updateNote(@PathVariable ("id") String id, @RequestBody NoteBean note);

    /// DELETE
    @GetMapping("/note/delete/{id}")
    void deleteNote(@PathVariable ("id") String id);

    @GetMapping("/note/delete/all/{patientId}")
    void deleteAllNotesByPatientId(@PathVariable ("patientId") Integer patientId);


}