package com.keep.notes.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
   private Integer userId;
   private String name;
   private String email;
   private String password;

   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval=true)
   @JsonManagedReference
   private List<NoteList> notesList;

   public User() {
   }

   public User(Integer userId, String name, String email, String password, List<NoteList> notesList) {
    this.userId = userId;
    this.name = name;
    this.email = email;
    this.password = password;
    this.notesList = notesList;
  }

   public Integer getUserId() {
     return userId;
   }

   public void setUserId(Integer userId) {
     this.userId = userId;
   }

   public String getName() {
     return name;
   }

   public void setName(String name) {
     this.name = name;
   }

   public String getEmail() {
     return email;
   }

   public void setEmail(String email) {
     this.email = email;
   }

   public String getPassword() {
     return password;
   }

   public void setPassword(String password) {
     this.password = password;
   }



   public List<NoteList> getNotesList() {
     return notesList;
   }



   public void setNotesList(List<NoteList> notesList) {
     this.notesList = notesList;
   }



   

  
   
   

   
   
   
}


