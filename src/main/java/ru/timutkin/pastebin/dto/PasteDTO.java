package ru.timutkin.pastebin.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasteDTO {

    String data;

    LocalDateTime creationTime;

    LocalDateTime expirationTime;
}
