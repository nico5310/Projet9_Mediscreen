package com.nico5310.frontMediscreen.proxies;

import com.nico5310.frontMediscreen.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "note-microservice", url = "${note.url}")
public interface NoteMSProxy {

    @GetMapping("/note/list/{patientId}")
    String listNote(@PathVariable("patientId") Integer patientId);

    @GetMapping("/note/getNoteById/{id}")
    NoteBean getNoteById(@RequestParam String id);

    @PostMapping("/note/add")
    NoteBean addNote(@RequestBody NoteBean note);

    @GetMapping("/note/showUpdateForm/{id}")
    NoteBean showUpdateForm(@PathVariable("id") String id);

    @PostMapping("/note/update/{id}")
    NoteBean updateNote(@PathVariable("id") String id, @RequestBody @Valid NoteBean note);

    @GetMapping("/note/delete/{id}")
    void deleteNote(@PathVariable ("id") String id);

}