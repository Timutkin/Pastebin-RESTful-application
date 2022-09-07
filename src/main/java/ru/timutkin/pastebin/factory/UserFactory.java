package ru.timutkin.pastebin.factory;

import org.springframework.stereotype.Component;
import ru.timutkin.pastebin.store.dto.UserDTO;
import ru.timutkin.pastebin.store.entity.UserEntity;

@Component
public class UserFactory {


    public UserEntity createUserEntity(UserDTO userDTO){
        return UserEntity.builder()
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
