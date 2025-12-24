package com.keep.notes.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keep.notes.exceptions.NoteListException;
import com.keep.notes.model.Note;
import com.keep.notes.model.NoteList;
import com.keep.notes.repository.NoteListRepository;
import com.keep.notes.service.NoteListService;

@Service
public class NoteListServiceImpl implements NoteListService{

    @Autowired
    private NoteListRepository noteListRepo;
    @Override
    public NoteList deleteNoteList(Integer noteid) {
        Optional<NoteList> opt = noteListRepo.findById(noteid);
        if(opt.isEmpty()){
            throw new NoteListException("Note List Not found");
        }
   
        noteListRepo.deleteById(noteid);
        
        return opt.get();
    }

    @Override
    public List<NoteList> getAllNoteLists() {
        List<NoteList> list = noteListRepo.findAll();
        System.out.println(list);
        return list;
    }

    @Override
    public NoteList saveNoteList(NoteList noteList) {
         System.out.println(noteList);
        if(noteList.getNoteLists()!=null){
            for(Note note:noteList.getNoteLists()){
                note.setNoteList(noteList);
            }
        }
        
        NoteList saveNoteList = noteListRepo.save(noteList);
        System.out.println(saveNoteList);
        return saveNoteList;
    }

    @Override
    public NoteList getNoteListById(Integer id) {
     Optional<NoteList> opt = noteListRepo.findById(id);
        if(opt.isEmpty()){
            throw new NoteListException("Note List Not found");
        }
        return opt.get();
    }

 public List<NoteList> getNoteListsByUserId(Integer userId){
    List<NoteList> list = noteListRepo.findByUserUserId(userId);
        return list;
 }

 @Override
 public NoteList updateNoteList(NoteList updatedNoteList,Integer noteListId){
    Optional<NoteList> opt = noteListRepo.findById(noteListId);
    if(opt.isEmpty()){
        throw new NoteListException("NoteList Not Found to update");
    }
     NoteList noteList = opt.get();
     noteList.setArchieved(updatedNoteList.getArchieved());
     noteList.setBackgroundColor(updatedNoteList.getBackgroundColor());
     noteList.setTitle(updatedNoteList.getTitle());
     noteList.setIsList(updatedNoteList.getIsList());
      System.out.println(updatedNoteList.getNoteLists());
     if (updatedNoteList.getNoteLists() != null) {

        // Clear existing notes if you want full replacement behavior
        noteList.getNoteLists().clear();

        for (Note incomingNote : updatedNoteList.getNoteLists()) {

            Note note = new Note();

            // If ID exists → UPDATE
            if (incomingNote.getId() != null) {
                note.setId(incomingNote.getId());
            }

            // Set fields
            note.setContent(incomingNote.getContent());
            note.setChecked(incomingNote.getChecked());

            // ⭐ VERY IMPORTANT: Set parent
            note.setNoteList(noteList);
            System.out.print(note);

            // Add to parent collection
            noteList.getNoteLists().add(note);
        }}
        System.out.println(noteList);
        noteListRepo.save(noteList);
      return noteList;
 }

    
    @Override
    public NoteList updateArchieved(boolean updateArchieved, Integer noteid) {
              Optional<NoteList> opt = noteListRepo.findById(noteid);
              NoteList noteList = opt.get();
              noteList.setArchieved(updateArchieved);
        return noteList;
    }

    @Override
    public NoteList updateColor(String color, Integer noteid) {
    Optional<NoteList> opt = noteListRepo.findById(noteid);
              NoteList noteList = opt.get();
              noteList.setBackgroundColor(color);

        return null;
    }


    @Override
    public NoteList updateRemainder(LocalDateTime updateRemainder, Integer noteid) {
        Optional<NoteList> opt = noteListRepo.findById(noteid);
        NoteList noteList = opt.get();
        noteList.setReminderAt(updateRemainder);
        return null;
    }

    @Override
    public NoteList updateTitle(String title, Integer noteid) {
        Optional<NoteList> opt = noteListRepo.findById(noteid);
        NoteList noteList = opt.get();
        return null;
    }



    @Override
    public NoteList saveNoteSingeleNote(NoteList noteList) {
        System.out.println("noteList");
        return noteListRepo.save(noteList);
    }
    

}
