package ru.timutkin.pastebin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.timutkin.pastebin.store.dto.PasteDTO;
import ru.timutkin.pastebin.exception.PasteNotActiveException;
import ru.timutkin.pastebin.exception.PasteNotFoundException;
import ru.timutkin.pastebin.factory.PasteDTOFactory;
import ru.timutkin.pastebin.factory.PasteFactory;
import ru.timutkin.pastebin.store.entity.PasteEntity;
import ru.timutkin.pastebin.store.enumeration.PasteAccessStatus;
import ru.timutkin.pastebin.store.enumeration.PasteStatus;
import ru.timutkin.pastebin.store.repository.PasteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/pastebin")
public class PasteController {

    PasteRepository pasteRepository;

    PasteDTOFactory pasteDTOFactory;

    PasteFactory pasteFactory;

    private static final String REF = "http://localhost:2022/api/v1/pastebin/";

    @GetMapping("/{hash}")
    public ResponseEntity<PasteDTO> getPaste(@PathVariable String hash){
        Optional<PasteEntity> mayBePaste = pasteRepository.findPasteByHash(hash);

        if (mayBePaste.isEmpty()){
            throw new PasteNotFoundException(String.format("Paste with hash = %s not found", hash));
        }

        PasteEntity pasteEntity = mayBePaste.get();

        if (pasteEntity.getStatus().equals(PasteStatus.NOT_ACTIVE)){
            throw new PasteNotActiveException("The period of access to pasta is over");
        }

        return ResponseEntity.ok(pasteDTOFactory.createPasteDTO(pasteEntity));
    }

    @GetMapping("/")
    public ResponseEntity<List<PasteDTO>> getLastPaste(){
        List<PasteEntity> pasteEntities = pasteRepository.findLastTenPaste();

        return ResponseEntity.ok(pasteDTOFactory.createListPasteDTO(pasteEntities));
    }

    @PostMapping("/")
    public ResponseEntity<String> createPaste(@RequestParam(name = "data") String data,
                                              @RequestParam(name = "expirationTime") String expirationTime,
                                              @RequestParam(name = "access")PasteAccessStatus accessStatus){
            LocalDateTime time = LocalDateTime.parse(expirationTime);

            if (time.isBefore(LocalDateTime.now())){

            }

            PasteEntity pasteEntity = pasteFactory.makeDefault(data, time, accessStatus);

            pasteRepository.save(pasteEntity);

            return ResponseEntity.ok(REF+ pasteEntity.getHash());
    }

}
