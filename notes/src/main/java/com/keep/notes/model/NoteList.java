package com.keep.notes.model;



import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class NoteList {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Boolean isList;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Boolean archieved;
    private LocalDateTime reminderAt;
    private String backgroundColor;

    @OneToMany(mappedBy="noteList",cascade=CascadeType.ALL,orphanRemoval=false)
    private List<Note> noteLists;

    @ManyToOne
    @JsonBackReference
    private User user;

    @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
    }
    public NoteList() {
    }

    
    public NoteList(Integer id, String title, Boolean isList, LocalDateTime createdTime, LocalDateTime updatedTime,
            Boolean archieved, LocalDateTime reminderAt, String backgroundColor, List<Note> noteLists, User user) {
        this.id = id;
        this.title = title;
        this.isList = isList;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.archieved = archieved;
        this.reminderAt = reminderAt;
        this.backgroundColor = backgroundColor;
        this.noteLists = noteLists;
        this.user = user;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Boolean getIsList() {
        return isList;
    }
    public void setIsList(Boolean isList) {
        this.isList = isList;
    }
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
    public Boolean getArchieved() {
        return archieved;
    }
    public void setArchieved(Boolean archieved) {
        this.archieved = archieved;
    }
    public LocalDateTime getReminderAt() {
        return reminderAt;
    }
    public void setReminderAt(LocalDateTime reminderAt) {
        this.reminderAt = reminderAt;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public List<Note> getNoteLists() {
        return noteLists;
    }
    public void setNoteLists(List<Note> noteLists) {
        this.noteLists = noteLists;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    
     




}

