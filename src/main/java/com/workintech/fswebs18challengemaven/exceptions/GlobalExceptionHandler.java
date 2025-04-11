package com.workintech.fswebs18challengemaven.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardException.class)
    public ResponseEntity<CardErrorResponse> handleException(CardException cardException){
        log.error("card exception occured! Exception details: ",cardException);
        CardErrorResponse cardErrorResponse=new CardErrorResponse(cardException.getMessage());
        return new ResponseEntity<>(cardErrorResponse,cardException.getHttpStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CardErrorResponse> handleException(Exception exception){
        log.error("Exception occured! Exception details: ",exception);
        CardErrorResponse cardErrorResponse=new CardErrorResponse( exception.getLocalizedMessage());
        return new ResponseEntity<>(cardErrorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
