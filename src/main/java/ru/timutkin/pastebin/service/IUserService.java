package ru.timutkin.pastebin.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.timutkin.pastebin.exception.EmailAlreadyExistException;
import ru.timutkin.pastebin.exception.UsernameAlreadyExistException;
import ru.timutkin.pastebin.store.entity.UserEntity;
import ru.timutkin.pastebin.store.repository.UserRepository;

@Service
@AllArgsConstructor
public class IUserService implements UserService{

    UserRepository userRepository;

    BCryptPasswordEncoder encoder;

    @Override
    public void registerUser(UserEntity user) throws EmailAlreadyExistException, UsernameAlreadyExistException {

        if (userRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyExistException(String.format("User with email {%s} already exists", user.getEmail()));
        }

        if (userRepository.existsByUsername(user.getUsername())){
            throw new UsernameAlreadyExistException(String.format("User with username {%s} already exists", user.getUsername()));
        }

        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
    }
}
