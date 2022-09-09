package ru.timutkin.pastebin.store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.timutkin.pastebin.store.entity.UserEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationForm {
    String username;

    String firstName;

    String lastName;

    String email;

    String password;

}
