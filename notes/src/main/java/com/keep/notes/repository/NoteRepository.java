package com.keep.notes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keep.notes.model.Note;
import com.keep.notes.model.NoteList;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer>{

    List<Note> findByNoteList(NoteList noteList);
    
}
