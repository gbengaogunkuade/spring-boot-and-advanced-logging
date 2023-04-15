package com.ogunkuade.engineers.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {



    //_____EXCEPTION HANDLER FOR VALIDATION_______
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> methodArgErrorList =  new ArrayList<>();
        for(ObjectError error : methodArgumentNotValidException.getBindingResult().getAllErrors()){
            methodArgErrorList.add(error.getDefaultMessage());
        }
        ValidationResponse validationResponse = new ValidationResponse(new Date(), "VALIDATION ERROR", methodArgErrorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
    }



    //_____EXCEPTION HANDLER FOR EngineerAlreadyExistsException EXCEPTION____
    @ExceptionHandler(EngineerAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEngineerAlreadyExistsException(EngineerAlreadyExistsException engineerAlreadyExistsException){
        ExceptionResponse exceptionResponse =  new ExceptionResponse(new Date(), "ENGINEER ALREADY EXISTS", engineerAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }



    //_____EXCEPTION HANDLER FOR EngineerDoesNotExistsException EXCEPTION____
    @ExceptionHandler(EngineerDoesNotExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEngineerDoesNotExistsException(EngineerDoesNotExistsException engineerDoesNotExistsException){
        ExceptionResponse exceptionResponse =  new ExceptionResponse(new Date(), "ENGINEER DOES NOT EXISTS", engineerDoesNotExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }





}
