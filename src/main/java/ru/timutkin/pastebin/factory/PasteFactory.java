package ru.timutkin.pastebin.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.timutkin.pastebin.hash.HashGenerator;
import ru.timutkin.pastebin.store.entity.PasteEntity;
import ru.timutkin.pastebin.store.enumeration.PasteAccessStatus;
import ru.timutkin.pastebin.store.enumeration.PasteStatus;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class PasteFactory {

    HashGenerator hashGenerator;

    public PasteEntity makeDefault(String data, LocalDateTime expirationTime, PasteAccessStatus accessStatus){
        return PasteEntity.builder()
                .data(data)
                .expirationTime(expirationTime)
                .creationTime(LocalDateTime.now())
                .accessStatus(accessStatus)
                .hash(hashGenerator.generateHash())
                .status(PasteStatus.ACTIVE)
                .build();
    }

}
