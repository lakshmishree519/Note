package com.keep.notes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keep.notes.model.NoteList;

@Repository
public interface NoteListRepository extends JpaRepository<NoteList,Integer>{

    List<NoteList> findByUserUserId(Integer userId);
    
}
