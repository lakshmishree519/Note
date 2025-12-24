package com.keep.notes.exceptions;

public class NoteListException extends RuntimeException{
    public NoteListException(){}
    public NoteListException(String msg){
        super(msg);
    }
}
