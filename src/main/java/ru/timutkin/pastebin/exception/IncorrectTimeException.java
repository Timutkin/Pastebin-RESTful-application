package ru.timutkin.pastebin.exception;

public class IncorrectTimeException extends RuntimeException{
    public IncorrectTimeException(String message) {
        super(message);
    }
}
