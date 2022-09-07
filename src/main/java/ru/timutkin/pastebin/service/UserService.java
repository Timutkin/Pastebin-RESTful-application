package ru.timutkin.pastebin.service;

import ru.timutkin.pastebin.exception.EmailAlreadyExistException;
import ru.timutkin.pastebin.exception.UsernameAlreadyExistException;
import ru.timutkin.pastebin.store.entity.UserEntity;


public interface UserService {

    void registerUser(UserEntity user ) throws EmailAlreadyExistException, UsernameAlreadyExistException;

}
