package com.ogunkuade.engineers.exception;

public class EngineerDoesNotExistsException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public EngineerDoesNotExistsException(String message){
        super(message);
    }


}
