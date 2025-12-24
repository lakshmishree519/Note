package com.keep.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keep.notes.model.Note;
import com.keep.notes.service.NoteService;

@RestController
@RequestMapping("/api/keepNotes")
@CrossOrigin(origins = "*")
public class NoteController {
    
    @Autowired
    private NoteService noteService;
    @PutMapping("/updateCheck/{noteId}")
    public ResponseEntity<?> updatedChecked(@RequestBody Note note,@PathVariable Integer noteId){
        return ResponseEntity.status(200).body(noteService.updateChecked(note, noteId));
    }
}
