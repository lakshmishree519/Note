package com.keep.notes.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keep.notes.exceptions.NoteException;
import com.keep.notes.model.Note;
import com.keep.notes.model.NoteList;
import com.keep.notes.repository.NoteListRepository;
import com.keep.notes.repository.NoteRepository;
import com.keep.notes.service.NoteService;

@Service
public class NoteServiceImpl  implements NoteService{


    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteListRepository noteListRepository;
    @Override
    public Note deleteNote(Note note, Integer noteId) {
        Optional<Note> opt = noteRepository.findById(noteId);
        if(opt.isEmpty()){
            throw new NoteException("not found");
        }
        noteRepository.deleteById(noteId);
        return opt.get();
    }

    @Override
    public List<Note> getNoteByListId(Integer noteListId) {
        Optional<NoteList> noteList = noteListRepository.findById(noteListId);
        List<Note> list= noteRepository.findByNoteList(noteList.get());
        return list;
    }

    @Override
    public Note saveNote(Note note) {
        noteRepository.save(note);
        return null;
    }

    @Override
    public Note updateChecked(Note note, Integer noteId) {
          Optional<Note> opt = noteRepository.findById(noteId); 
          Note existingNote = opt.get();
          existingNote.setChecked(!existingNote.getChecked());
          noteRepository.save(existingNote);
          return existingNote;
    }
}
