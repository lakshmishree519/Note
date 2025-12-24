package com.keep.notes.exceptions;

public class NoteException extends RuntimeException{
    public NoteException(){}
    public NoteException(String msg){
        super(msg);
    }
}
