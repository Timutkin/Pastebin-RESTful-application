package ru.timutkin.pastebin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PasteNotFoundException.class, PasteNotActiveException.class})
    protected ResponseEntity<Response> handleException(Exception exception){
        Response data = new Response();
        data.setInfo(exception.getClass().getSimpleName() + " : " + exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
