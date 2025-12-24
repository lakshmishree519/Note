package com.keep.notes.model;



import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private Boolean checked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_list_id")
    @JsonIgnore
    private NoteList noteList;

    
    public Note() {
    }


    public Note(Integer id, String content,Boolean checked, NoteList noteList) {
        this.id = id;
        this.content = content;
        this.checked=checked;
        this.noteList = noteList;
    }

 public Boolean getChecked(){
    return checked;
 }

 public void setChecked(Boolean checked){
    this.checked=checked;
 }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoteList getNoteList() {
        return noteList;
    }

    public void setNoteList(NoteList noteList) {
        this.noteList = noteList;
    }

    

}

