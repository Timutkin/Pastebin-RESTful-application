package ru.timutkin.pastebin.exception;

public class PasteNotFoundException extends IllegalArgumentException {
    public PasteNotFoundException(String message) {
        super(message);
    }
}
