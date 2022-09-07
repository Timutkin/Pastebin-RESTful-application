package ru.timutkin.pastebin.exception;

public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
