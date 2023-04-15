package com.ogunkuade.engineers.exception;

public class EngineerAlreadyExistsException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public EngineerAlreadyExistsException(String message){
        super(message);
    }


}
