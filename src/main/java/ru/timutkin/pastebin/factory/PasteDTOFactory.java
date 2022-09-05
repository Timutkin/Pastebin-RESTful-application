package ru.timutkin.pastebin.factory;

import org.springframework.stereotype.Component;
import ru.timutkin.pastebin.store.dto.PasteDTO;
import ru.timutkin.pastebin.store.entity.PasteEntity;

import java.util.List;

@Component
public class PasteDTOFactory {

    public PasteDTO createPasteDTO(PasteEntity pasteEntity){
        return PasteDTO.builder()
                .creationTime(pasteEntity.getCreationTime())
                .expirationTime(pasteEntity.getExpirationTime())
                .data(pasteEntity.getData())
                .build();
    }

    public List<PasteDTO> createListPasteDTO(List<PasteEntity> pasteEntityList){
        return pasteEntityList.stream()
                .map(this::createPasteDTO)
                .toList();

    }
}
