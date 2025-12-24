package com.keep.notes.exceptions;

public class PasswordIncorrect extends RuntimeException{
    public PasswordIncorrect(){}
    public PasswordIncorrect(String msg){
        super(msg);
    }

}
