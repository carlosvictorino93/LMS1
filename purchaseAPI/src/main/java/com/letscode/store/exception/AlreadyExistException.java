package com.letscode.store.exception;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message){
        super(message);
    }
}
