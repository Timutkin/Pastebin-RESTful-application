package ru.timutkin.pastebin.hash;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class HashGenerator {

    BCryptPasswordEncoder encoder;

    public String generateHash(){
        var  time = LocalDateTime.now().toString();
        return encoder.encode(time).replaceAll("/","");
    }


}
