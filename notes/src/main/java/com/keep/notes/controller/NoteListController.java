package com.keep.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keep.notes.exceptions.NoteListException;
import com.keep.notes.model.NoteList;
import com.keep.notes.service.NoteListService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/keepNotes")
@CrossOrigin(origins = "*")
public class NoteListController {
    
   
    @Autowired
    private NoteListService noteListService;

    @PostMapping("/saveList")
    public ResponseEntity<?> saveList(@RequestBody NoteList noteList){
        System.out.print(noteList);
        return ResponseEntity.status(200).body(noteListService.saveNoteList(noteList));
    }
     
    @PostMapping("/saveNote")
     public ResponseEntity<?> saveNote(@RequestBody NoteList noteList){
        return ResponseEntity.status(200).body(noteListService.saveNoteList(noteList));
    }

    @PutMapping("/updated/archived/{id}")
    public ResponseEntity<?> updateArchieve(@RequestBody boolean archieved,@PathVariable Integer id){
        return ResponseEntity.status(200).body(noteListService.updateArchieved(archieved, id));
    }
     
    @PutMapping("updated/{id}")
    public ResponseEntity<?> updatedNoteList(@PathVariable Integer id, @RequestBody NoteList updateList) {
        try{
       return ResponseEntity.status(200).body(noteListService.updateNoteList(updateList, id));
        }catch(NoteListException e){
        return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getAllNoteList")
    public ResponseEntity<?> getListNotes(Authentication authentication){

        String email = authentication.getName();

        List<NoteList> list= noteListService.getAllNoteLists();
        System.out.println(list);
        return ResponseEntity.status(200).body(list);
    }
    @GetMapping("/getAllNoteListUser/{userId}")
    public ResponseEntity<?> getListNotesByUser(@PathVariable Integer userId){

        List<NoteList> list= noteListService.getNoteListsByUserId(userId);

        return ResponseEntity.status(200).body(list);
    }
}
