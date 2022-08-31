package ru.timutkin.pastebin.exception;

public class PasteNotActiveException extends RuntimeException{
    public PasteNotActiveException(String message) {
        super(message);
    }
}
