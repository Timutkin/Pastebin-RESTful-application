package ru.timutkin.pastebin.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.timutkin.pastebin.factory.UserFactory;
import ru.timutkin.pastebin.service.UserService;
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
