package ru.timutkin.pastebin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {PasteNotFoundException.class})
    protected ResponseEntity<Response> handleNotFoundException(Exception exception){
        Response data = new Response();
        data.setInfo(exception.getClass().getSimpleName() + " : " + exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IncorrectTimeException.class})
    protected ResponseEntity<Response> handleIncorrectDataException(Exception exception){
        Response data = new Response();
        data.setInfo(exception.getClass().getSimpleName() + " : " + exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PasteNotActiveException.class})
    protected ResponseEntity<Response> handleNotActiveException(Exception exception){
        Response data = new Response();
        data.setInfo(exception.getClass().getSimpleName() + " : " + exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
    }

}
