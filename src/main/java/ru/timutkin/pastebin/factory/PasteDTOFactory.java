package ru.timutkin.pastebin.factory;

import org.springframework.stereotype.Component;
import ru.timutkin.pastebin.dto.PasteDTO;
import ru.timutkin.pastebin.store.entity.Paste;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PasteDTOFactory {

    public PasteDTO createPasteDTO(Paste paste){
        return PasteDTO.builder()
                .creationTime(paste.getCreationTime())
                .expirationTime(paste.getExpirationTime())
                .data(paste.getData())
                .build();
    }

    public List<PasteDTO> createListPasteDTO(List<Paste> pasteList){
        return pasteList.stream()
                .map(this::createPasteDTO)
                .toList();

    }
}
