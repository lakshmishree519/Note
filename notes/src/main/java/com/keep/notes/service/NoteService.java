package com.keep.notes.service;

import java.util.List;

import com.keep.notes.model.Note;
import com.keep.notes.model.NoteList;

public interface NoteService {
      public Note saveNote(Note note);
      public Note updateChecked(Note note,Integer noteId);
      public Note deleteNote(Note note,Integer noteId);
      public List<Note> getNoteByListId(Integer noteListId);
    
}
