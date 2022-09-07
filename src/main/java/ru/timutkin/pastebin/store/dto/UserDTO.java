package ru.timutkin.pastebin.store.dto;


import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    String username;

    String firstName;

    String lastName;

    String email;

    String password;
}
