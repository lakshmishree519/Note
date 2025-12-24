package com.keep.notes.service;

import java.time.LocalDateTime;
import java.util.List;

import com.keep.notes.model.NoteList;

public interface NoteListService {
    public NoteList saveNoteList(NoteList noteList);
    public NoteList saveNoteSingeleNote(NoteList noteList);
    public NoteList updateNoteList(NoteList updatedNoteList,Integer noteListId);
    public NoteList updateArchieved(boolean updateArchieved,Integer noteid);
    public NoteList updateRemainder(LocalDateTime updateRemainder,Integer noteid);
    public NoteList updateColor(String color,Integer noteid);
    public NoteList updateTitle(String title,Integer noteid);
    public NoteList getNoteListById(Integer id);
    public NoteList deleteNoteList(Integer noteid);
    public List<NoteList> getAllNoteLists();
    public List<NoteList> getNoteListsByUserId(Integer userId);
}
