package ru.timutkin.pastebin.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.timutkin.pastebin.factory.UserFactory;
import ru.timutkin.pastebin.service.UserService;
import ru.timutkin.pastebin.store.dto.RegistrationForm;
import ru.timutkin.pastebin.store.dto.UserDTO;
import ru.timutkin.pastebin.store.entity.UserEntity;
import ru.timutkin.pastebin.store.repository.UserRepository;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    UserService userService;

    UserFactory userFactory;



    @PostMapping
    public ResponseEntity<Long> registrationNewUser(@RequestBody UserDTO userDTO){
        UserEntity user = userFactory.createUserEntity(userDTO);

        userService.registerUser(user);

        return ResponseEntity.ok(user.getId());
    }
}
